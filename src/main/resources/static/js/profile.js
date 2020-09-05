const currentPasswordInput = $("#currentPassword");
const newPasswordInput = $("#newPassword");
const confirmPasswordInput = $("#confirmPassword");
const currentPasswordFeedback = $("#currentPasswordFeedback");
const newPasswordFeedback = $("#newPasswordFeedback");
const confirmPasswordFeedback = $("#confirmPasswordFeedback");
const passwordUpdatedAlert = $("#passwordUpdated");

const firstNameInput = $("#firstName");
const lastNameInput = $("#lastName");
const dobInput = $("#dob");
const bioInput = $("#bio");
const firstNameFeedback = $("#firstNameFeedback");
const lastNameFeedback = $("#lastNameFeedback");
const dobFeedback = $("#dobFeedback");
const bioFeedback = $("#bioFeedback");
const personalInfoUpdatedAlert = $("#personalInfoUpdated");
const personalInfoUpdateErrorAlert = $("#personalInfoUpdateError");

function cleanUpdatePasswordForm() {
    cleanElement(currentPasswordInput, currentPasswordFeedback);
    cleanElement(newPasswordInput, newPasswordFeedback);
    cleanElement(confirmPasswordInput, confirmPasswordFeedback);
    currentPasswordInput.val("");
    newPasswordInput.val("");
    confirmPasswordInput.val("");
}

function updatePassword() {
    const currentPasswordIsValid = checkCurrentPassword(currentPasswordInput, currentPasswordFeedback);
    const newPasswordIsValid = checkPassword(newPasswordInput, newPasswordFeedback);
    const confirmedPasswordIsValid = checkConfirmedPassword(confirmPasswordInput, confirmPasswordFeedback,
        newPasswordInput.val());
    if (currentPasswordIsValid && newPasswordIsValid && confirmedPasswordIsValid) {
        const currentPassword = currentPasswordInput.val();
        const newPassword = newPasswordInput.val();
        $.ajax({
            type: "POST",
            url: "/updatePassword",
            data: JSON.stringify({
                currentPassword: currentPassword,
                newPassword: newPassword
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            complete: function (jqXHR) {
                if (jqXHR.status === 201) {
                    cleanUpdatePasswordForm();
                    passwordUpdatedAlert.show();
                    setTimeout(function () {
                        passwordUpdatedAlert.hide();
                    }, 3000);
                }
                if (jqXHR.status === 401) {
                    cleanElement(currentPasswordInput, currentPasswordFeedback);
                    markElementInvalid(currentPasswordInput, currentPasswordFeedback, "Invalid password.");
                }
            }
        });
    }
}

function cancelUpdatePassword() {
    cleanUpdatePasswordForm();
}

function loadPersonalInfo() {
    $.getJSON(
        "/personalInfo",
        function (person) {
            if (person.firstName !== null) {
                firstNameInput.val(person.firstName);
            }
            if (person.lastName !== null) {
                lastNameInput.val(person.lastName);
            }
            if (person.dob !== null) {
                dobInput.val(person.dob);
            }
            if (person.bio !== null) {
                bioInput.val(person.bio);
            }
        }
    );
}

function updatePersonalInfo() {
    const firstNameIsValid = checkFirstName(firstNameInput, firstNameFeedback);
    const lastNameIsValid = checkLastName(lastNameInput, lastNameFeedback);
    if (firstNameIsValid && lastNameIsValid) {
        const firstName = firstNameInput.val();
        const lastName = lastNameInput.val();
        const dob = dobInput.val();
        const bio = bioInput.val();
        $.ajax({
            type: "POST",
            url: "/updatePersonalInfo",
            data: JSON.stringify({
                firstName: firstName,
                lastName: lastName,
                dob: dob,
                bio: bio
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            complete: function (jqXHR) {
                switch (jqXHR.status) {
                    case 201:
                        personalInfoUpdatedAlert.show();
                        setTimeout(function () {
                            personalInfoUpdatedAlert.hide();
                        }, 3000);
                        break;
                    case 400:
                        const errors = JSON.parse(jqXHR.responseText);
                        if (errors.firstName !== undefined) {
                            markElementInvalid(firstNameInput, firstNameFeedback, errors.firstName);
                        }
                        if (errors.lastName !== undefined) {
                            markElementInvalid(lastNameInput, lastNameFeedback, errors.lastName);
                        }
                        if (errors.dob !== undefined) {
                            markElementInvalid(dobInput, dobFeedback, errors.dob);
                        }
                        if (errors.bio !== undefined) {
                            markElementInvalid(bioInput, bioFeedback, errors.bio);
                        }
                        break;
                    default:
                        personalInfoUpdateErrorAlert.show();
                        setTimeout(function () {
                            personalInfoUpdateErrorAlert.hide();
                        }, 7000);
                }
            }
        });
    }
}

function formatFirstName(firstName) {
    let formattedFirstName = "";
    let temp = firstName.replace(/[^A-Za-z]/g, "");
    if (temp.length > 0) {
        formattedFirstName += temp.charAt(0).toUpperCase();
        if (temp.length > 1)
            formattedFirstName += temp.substr(1).toLowerCase();
    }
    return formattedFirstName;
}

function formatLastName(lastName) {
    let formattedLastName = "";
    let temp = lastName.replace(/[^A-Za-z]/g, "");
    if (temp.length > 0) {
        formattedLastName += temp.charAt(0).toUpperCase();
        if (temp.length > 1)
            formattedLastName += temp.substr(1).toLowerCase();
    }
    return formattedLastName;
}

currentPasswordInput.blur(function () {
    checkCurrentPassword(currentPasswordInput, currentPasswordFeedback);
});

newPasswordInput.blur(function () {
    checkPassword(newPasswordInput, newPasswordFeedback);
});

confirmPasswordInput.blur(function () {
    checkConfirmedPassword(confirmPasswordInput, confirmPasswordFeedback, newPasswordInput.val());
});

firstNameInput.blur(function () {
    checkFirstName(firstNameInput, firstNameFeedback);
});

lastNameInput.blur(function () {
    checkLastName(lastNameInput, lastNameFeedback);
});

firstNameInput.change(function () {
    firstNameInput.val(formatFirstName(firstNameInput.val()));
});

lastNameInput.change(function () {
    lastNameInput.val(formatLastName(lastNameInput.val()));
});

window.onload = function () {
    loadPersonalInfo();
}
