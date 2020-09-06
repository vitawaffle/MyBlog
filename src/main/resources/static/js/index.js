const postsArea = $("#postsArea");
const paginationIndexes = $("#paginationIndexes");
const editPostModal = $("#editPostModal");
const titleInput = $("#titleInput");
const contentTextarea = $("#contentTextarea");
const titleFeedback = $("#titleFeedback");
const contentFeedback = $("#contentFeedback");
const savePostError = $("#savePostError");
const idInput = $("#idInput");

const defaultPageSize = 9;

let page = null;
let size = null;

function getPosts(pageNumber = null, pageSize = null) {
    page = pageNumber;
    size = pageSize;

    $.get("/username", function (username) {
        let url = "/posts";
        if (page !== null && size !== null)
            url += `?page=${page}&size=${size}`;
        else if (page !== null)
            url += `?page=${page}`;
        else if (size !== null)
            url += `?size=${size}`;

        $.getJSON(url, function (page) {
            postsArea.html("");
            page.content.forEach(post => {
                postsArea.append(`
                <div class="col mb-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${post.title}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${post.user.username}</h6>
                            <p class="card-text">
                                ${post.content}
                            </p>
                            <p class="text-muted">${post.creation}</p>
                            ${username === post.user.username ? `
                            <a class="btn btn-primary" onclick="showPostEditModal(${post.id})">Edit</a>
                            <a class="btn btn-outline-secondary" onclick="deletePost(${post.id})">Delete</a>
                            ` : ""}
                        </div>
                    </div>
                </div>
                `);
            });

            paginationIndexes.html("");
            for (let i = 0; i < page.totalPages; i++) {
                paginationIndexes.append(`
                <li>
                    <a class="page-link${i === page.number ? ' active' : ''}" style="cursor: pointer;"
                        onclick="getPosts(${i}, ${defaultPageSize});">${i + 1}</a>
                </li>
            `);
            }
        });
    });
}

function showPostEditModal(id = null) {
    editPostModal.show();
    if (id !== null) {
        $.getJSON(
            `/posts/${id}`,
            function (post) {
                idInput.val(post.id);
                titleInput.val(post.title);
                contentTextarea.val(post.content);
            }
        )
    }
}

function hidePostEditModal() {
    cleanPostEditForm();
    editPostModal.hide();
}

function cleanPostEditForm() {
    cleanElement(titleInput, titleFeedback);
    cleanElement(contentTextarea, contentFeedback);
    idInput.val("");
    titleInput.val("");
    contentTextarea.val("");
}

function savePost() {
    const titleIsValid = checkTitle(titleInput, titleFeedback);
    const contentIsValid = checkContent(contentTextarea, contentFeedback);
    if (titleIsValid && contentIsValid) {
        const title = titleInput.val();
        const content = contentTextarea.val();
        const id = idInput.val() !== "" ? idInput.val() : null;
        $.ajax({
            type: "POST",
            url: "/posts",
            data: JSON.stringify({
                id: id,
                title: title,
                content: content
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            complete: function (jqXHR) {
                switch (jqXHR.status) {
                    case 201:
                        hidePostEditModal();
                        getPosts();
                        break;
                    case 400:
                        const errors = JSON.parse(jqXHR.responseText);
                        if (errors.title !== undefined)
                            markElementInvalid(titleInput, titleFeedback, errors.title);
                        if (errors.content !== undefined)
                            markElementInvalid(contentTextarea, contentFeedback, errors.content);
                        break;
                    default:
                        savePostError.show();
                        setTimeout(function () {
                            savePostError.hide();
                        }, 7000);
                }
            }
        });
    }
}

function deletePost(id) {
    $.ajax({
        url: `/posts/${id}`,
        type: "DELETE",
        complete: function () {
            getPosts(page, size);
        }
    });
}

titleInput.blur(function () {
    checkTitle(titleInput, titleFeedback);
});

contentTextarea.blur(function () {
    checkContent(contentTextarea, contentFeedback);
});

window.onload = function () {
    getPosts();
}
