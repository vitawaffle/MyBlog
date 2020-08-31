package by.vit.myblog.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "Password must contain upper and lower case letters, numbers and be between 8 and 64"
            + " characters long.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
