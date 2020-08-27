const usernameInput = $("#username");
const passwordInput = $("#password");
const confirmPasswordInput = $("#confirmPassword");
const usernameFeedback = $("#usernameFeedback");
const passwordFeedback = $("#passwordFeedback");
const confirmPasswordFeedback = $("#confirmPasswordFeedback");

const usernameRegex = /^[a-zA-Z][a-zA-Z0-9_]{4,32}$/;
const passwordRegex = /^[a-zA-Z0-9]{8,64}$/;
const passwordUppercaseRegex = /[A-Z]/;
const passwordLowercaseRegex = /[a-z]/;
const passwordNumbersRegex = /[0-9]/;

const successMessage = "Looks good!";

let passwordIsValid = false;

function markElementInvalid(element, elementFeedback, errorMessage) {
    element.addClass("is-invalid");
    elementFeedback.addClass("invalid-feedback");
    elementFeedback.html(errorMessage);
}

function markElementValid(element, elementFeedback, successMessage) {
    element.addClass("is-valid");
    elementFeedback.addClass("valid-feedback");
    elementFeedback.html(successMessage);
}

function cleanElement(element, elementFeedback) {
    element.removeClass("is-valid is-invalid");
    elementFeedback.removeClass("valid-feedback invalid-feedback");
    elementFeedback.html("");
}

function isBlank(str) {
    return str === undefined || str === null || str === "";
}

function checkUsername() {
    const username = usernameInput.val();
    cleanElement(usernameInput, usernameFeedback);
    if (isBlank(username)) {
        markElementInvalid(usernameInput, usernameFeedback, "Please enter your username.");
        return false;
    }
    if (!username.match(usernameRegex)) {
        markElementInvalid(usernameInput, usernameFeedback, "Username must contain only letters, numbers, "
            + " underscore, start with a letter and be from 4 to 32 characters long.");
        return false;
    }
    markElementValid(usernameInput, usernameFeedback, successMessage);
    return true;
}

function checkPassword() {
    const password = passwordInput.val();
    cleanElement(passwordInput, passwordFeedback);
    if (isBlank(password)) {
        markElementInvalid(passwordInput, passwordFeedback, "Please pick a password.");
        passwordIsValid = false;
        return false;
    }
    if (
        !password.match(passwordRegex) ||
        !password.match(passwordUppercaseRegex) ||
        !password.match(passwordLowercaseRegex) ||
        !password.match(passwordNumbersRegex)
    ) {
        markElementInvalid(passwordInput, passwordFeedback, "Password must contain upper and lower case"
            + " letters, numbers and be between 8 and 64 characters long.");
        passwordIsValid = false;
        return false;
    }
    markElementValid(passwordInput, passwordFeedback, successMessage);
    passwordIsValid = true;
    return true;
}

function checkConfirmedPassword() {
    cleanElement(confirmPasswordInput, confirmPasswordFeedback);
    if (!passwordIsValid) {
        return false;
    }
    const confirmedPassword = confirmPasswordInput.val();
    cleanElement(confirmPasswordInput, confirmPasswordFeedback);
    if (isBlank(confirmedPassword)) {
        markElementInvalid(confirmPasswordInput, confirmPasswordFeedback, "Please confirm password.");
        return false;
    }
    if (confirmedPassword !== passwordInput.val()) {
        markElementInvalid(confirmPasswordInput, confirmPasswordFeedback, "Password mismatch.");
        return false;
    }
    markElementValid(confirmPasswordInput, confirmPasswordFeedback, successMessage);
    return true;
}

function signIn() {
    let usernameIsValid = checkUsername();
    let passwordIsValid = checkPassword();
    let confirmedPasswordIsValid = checkConfirmedPassword();
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
            success: function (data) {
                window.location.href = "/login";
            },
            failure: function (data) {
                let errors = JSON.parse(data);
                if (errors.username !== undefined) {
                    markElementInvalid(usernameInput, usernameFeedback, errors.username);
                }
                if (errors.password !== undefined) {
                    markElementInvalid(passwordInput, passwordFeedback, erros.password);
                }
            }
        });
    }
}

usernameInput.blur(function () {
    checkUsername();
});

passwordInput.blur(function () {
    checkPassword();
});

confirmPasswordInput.blur(function () {
    checkConfirmedPassword();
});
