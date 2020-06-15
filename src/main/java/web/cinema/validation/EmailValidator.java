package web.cinema.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        String regex = ".+@.+\\..+";
        return email != null && email.matches(regex) && email.length() > 8 && email.length() < 25;
    }
}
