package by.vit.myblog.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LastNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LastName {

    String message() default "Last name contains invalid characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
