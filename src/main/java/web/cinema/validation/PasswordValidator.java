package web.cinema.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import web.cinema.model.dto.UserRegistrationDto;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint,
        UserRegistrationDto> {

    @Override
    public boolean isValid(UserRegistrationDto dto,
                           ConstraintValidatorContext constraintValidatorContext) {
        String password = dto.getPassword();
        String repeatPassword = dto.getRepeatPassword();
        return password != null && password.equals(repeatPassword);
    }
}
