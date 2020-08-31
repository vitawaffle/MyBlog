const usernameRegex = /^[a-zA-Z][a-zA-Z0-9_]{4,32}$/;
const passwordRegex = /^[a-zA-Z0-9]{8,64}$/;
const passwordUppercaseRegex = /[A-Z]/;
const passwordLowercaseRegex = /[a-z]/;
const passwordNumbersRegex = /[0-9]/;
const firstNameRegex = /^[a-zA-Z]{1,50}$/;
const lastNameRegex = /^[a-zA-Z-]{1,50}$/;

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

function checkUsername(usernameInput, usernameFeedback) {
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

function checkPassword(passwordInput, passwordFeedback) {
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

function checkConfirmedPassword(confirmPasswordInput, confirmPasswordFeedback, password) {
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
    if (confirmedPassword !== password) {
        markElementInvalid(confirmPasswordInput, confirmPasswordFeedback, "Password mismatch.");
        return false;
    }
    markElementValid(confirmPasswordInput, confirmPasswordFeedback, successMessage);
    return true;
}

function checkCurrentPassword(currentPasswordInput, currentPasswordFeedback) {
    const currentPassword = currentPasswordInput.val();
    cleanElement(currentPasswordInput, currentPasswordFeedback);
    if (isBlank(currentPassword)) {
        markElementInvalid(currentPasswordInput, currentPasswordFeedback, "Please enter your current"
            + " password.");
        return false;
    }
    return true;
}

function checkFirstName(firstNameInput, firstNameFeedback) {
    const firstName = firstNameInput.val();
    cleanElement(firstNameInput, firstNameFeedback);
    if (!isBlank(firstName) && !firstName.match(firstNameRegex)) {
        markElementInvalid(firstNameInput, firstNameFeedback, "First name contains invalid characters.");
        return false;
    }
    return true;
}

function checkLastName(lastNameInput, lastNameFeedback) {
    const lastName = lastNameInput.val();
    cleanElement(lastNameInput, lastNameFeedback);
    if (!isBlank(lastName) && !lastName.match(lastNameRegex)) {
        markElementInvalid(lastNameInput, lastNameFeedback, "Last name contains invalid characters.");
        return false;
    }
    return true;
}
