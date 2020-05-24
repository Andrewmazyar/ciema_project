package web.cinema.security.impl;

import web.cinema.exception.AuthenticationException;
import web.cinema.lib.Inject;
import web.cinema.lib.Service;
import web.cinema.model.User;
import web.cinema.security.AuthenticationService;
import web.cinema.service.UserService;
import web.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    UserService userService;

    @Override
    public User email(String email, String password) throws AuthenticationException {
        User userFromDb = userService.findByEmail(email);
        if (HashUtil.hashPassword(password, userFromDb.getSalt())
                .equals(userFromDb.getPassword())) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect password or email");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        byte[] salt = HashUtil.getSalt();
        user.setEmail(email);
        user.setSalt(salt);
        user.setPassword(HashUtil.hashPassword(password, salt));
        userService.add(user);
        return user;
    }
}
