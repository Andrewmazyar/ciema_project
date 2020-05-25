package web.cinema.security;

import web.cinema.exception.AuthenticationException;
import web.cinema.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;

    public User register(String email, String password);
}
