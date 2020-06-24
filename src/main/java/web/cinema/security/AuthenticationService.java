package web.cinema.security;

import web.cinema.model.User;

public interface AuthenticationService {
    public User register(String email, String password);
}
