package web.cinema.security.impl;

import org.springframework.stereotype.Service;
import web.cinema.exception.AuthenticationException;
import web.cinema.model.User;
import web.cinema.security.AuthenticationService;
import web.cinema.service.ShoppingCartService;
import web.cinema.service.UserService;
import web.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;

    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
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
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
