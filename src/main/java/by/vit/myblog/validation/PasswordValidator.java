package by.vit.myblog.validation;

import lombok.val;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        val passwordRegex = "^[a-zA-Z0-9]{8,64}$";
        val passwordUppercaseRegex = ".*[A-Z].*";
        val passwordLowercaseRegex = ".*[a-z].*";
        val passwordNumbersRegex = ".*[0-9].*";
        return password == null || (
                password.matches(passwordRegex) &&
                password.matches(passwordUppercaseRegex) &&
                password.matches(passwordLowercaseRegex) &&
                password.matches(passwordNumbersRegex));
    }

}
