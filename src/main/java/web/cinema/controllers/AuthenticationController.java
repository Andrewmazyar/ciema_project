package web.cinema.controllers;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.model.dto.UserRegistrationDto;
import web.cinema.security.AuthenticationService;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        authenticationService.register(userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword());
    }
}
