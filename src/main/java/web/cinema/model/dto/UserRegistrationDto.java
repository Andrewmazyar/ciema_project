package web.cinema.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import web.cinema.validation.EmailConstraint;
import web.cinema.validation.PasswordConstraint;

public class UserRegistrationDto {
    @NotNull
    @Size(min = 6)
    @EmailConstraint
    private String email;
    @PasswordConstraint
    private String password;
    @PasswordConstraint
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
