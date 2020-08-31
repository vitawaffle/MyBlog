const usernameInput = $("#username");
const passwordInput = $("#password");
const confirmPasswordInput = $("#confirmPassword");
const usernameFeedback = $("#usernameFeedback");
const passwordFeedback = $("#passwordFeedback");
const confirmPasswordFeedback = $("#confirmPasswordFeedback");

function signIn() {
    const usernameIsValid = checkUsername(usernameInput, usernameFeedback);
    const passwordIsValid = checkPassword(passwordInput, passwordFeedback);
    const confirmedPasswordIsValid = checkConfirmedPassword(confirmPasswordInput, confirmPasswordFeedback,
        passwordInput.val());
    if (usernameIsValid && passwordIsValid && confirmedPasswordIsValid) {
        const username = usernameInput.val();
        const password = passwordInput.val();
        $.ajax({
            type: "POST",
            url: "/signIn",
            data: JSON.stringify({
                username: username,
                password: password
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            complete: function (jqXHR) {
                if (jqXHR.status === 201) {
                    window.location.href = "/login";
                }
                if (jqXHR.status === 400) {
                    const errors = JSON.parse(jqXHR.responseText);
                    if (errors.username !== undefined) {
                        markElementInvalid(usernameInput, usernameFeedback, errors.username);
                    }
                    if (errors.password !== undefined) {
                        markElementInvalid(passwordInput, passwordFeedback, errors.password);
                    }
                }
            }
        });
    }
}

usernameInput.blur(function () {
    checkUsername(usernameInput, usernameFeedback);
});

passwordInput.blur(function () {
    checkPassword(passwordInput, passwordFeedback);
});

confirmPasswordInput.blur(function () {
    checkConfirmedPassword(confirmPasswordInput, confirmPasswordFeedback, passwordInput.val());
});
