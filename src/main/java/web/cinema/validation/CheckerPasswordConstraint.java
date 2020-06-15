package web.cinema.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CheckerPasswordValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckerPasswordConstraint {
    String message() default "'password' and 'repeat password' don't match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
