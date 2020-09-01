package by.vit.myblog.validation;

import lombok.val;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FirstNameValidator implements ConstraintValidator<FirstName, String> {

    @Override
    public boolean isValid(final String firstName, final ConstraintValidatorContext context) {
        val firstNameRegex = "^[A-Z][a-z]{1,49}$";
        return firstName == null || firstName.matches(firstNameRegex);
    }

}
