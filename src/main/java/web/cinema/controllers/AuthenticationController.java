package web.cinema.controllers;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.exception.AuthenticationException;
import web.cinema.model.dto.UserRegistrationDto;
import web.cinema.security.AuthenticationService;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRegistrationDto userRegistrationDto)
            throws AuthenticationException {
        if (userRegistrationDto.getPassword().equals(userRegistrationDto.getRepeatPassword())) {
            authenticationService.register(userRegistrationDto.getEmail(),
                    userRegistrationDto.getPassword());
        } else {
            throw new AuthenticationException("password is not valid");
        }
    }
}
