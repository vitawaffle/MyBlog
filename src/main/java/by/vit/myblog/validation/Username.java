package by.vit.myblog.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Username {

    String message() default "Username must contain only letters, numbers, underscore, start with a letter and be"
            + " from 4 to 32 characters long.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
