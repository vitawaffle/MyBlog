const currentPasswordInput = $("#currentPassword");
const newPasswordInput = $("#newPassword");
const confirmPasswordInput = $("#confirmPassword");
const currentPasswordFeedback = $("#currentPasswordFeedback");
const newPasswordFeedback = $("#newPasswordFeedback");
const confirmPasswordFeedback = $("#confirmPasswordFeedback");
const passwordUpdatedAlert = $("#passwordUpdated");

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
                    cleanElement(currentPasswordInput, currentPasswordFeedback);
                    cleanElement(newPasswordInput, newPasswordFeedback);
                    cleanElement(confirmPasswordInput, confirmPasswordFeedback);
                    currentPasswordInput.val("");
                    newPasswordInput.val("");
                    confirmPasswordInput.val("");
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

currentPasswordInput.blur(function () {
    checkCurrentPassword(currentPasswordInput, currentPasswordFeedback);
});

newPasswordInput.blur(function () {
    checkPassword(newPasswordInput, newPasswordFeedback);
});

confirmPasswordInput.blur(function () {
    checkConfirmedPassword(confirmPasswordInput, confirmPasswordFeedback, newPasswordInput.val());
});
