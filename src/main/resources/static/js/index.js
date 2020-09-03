const postsArea = $("#postsArea");
const paginationIndexes = $("#paginationIndexes");

const pageSize = 9;

function getPosts(page = null, size = null) {
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
                        onclick="getPosts(${i}, ${pageSize});">${i + 1}</a>
                </li>
            `);
        }
    });
}

window.onload = function () {
    getPosts();
}
