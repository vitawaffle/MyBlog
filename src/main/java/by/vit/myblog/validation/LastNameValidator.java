package by.vit.myblog.validation;

import lombok.val;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LastNameValidator implements ConstraintValidator<LastName, String> {

    @Override
    public boolean isValid(final String lastName, final ConstraintValidatorContext context) {
        val lastNameRegex = "^[A-Z][a-z]{1,49}$";
        return lastName == null || lastName.matches(lastNameRegex);
    }

}
