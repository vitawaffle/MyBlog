package by.vit.myblog.validation;

import lombok.val;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username,String> {

    @Override
    public boolean isValid(final String username, final ConstraintValidatorContext context) {
        val usernameRegex = "^[a-zA-Z][a-zA-Z0-9_]{4,32}$";
        return username == null || username.matches(usernameRegex);
    }

}
