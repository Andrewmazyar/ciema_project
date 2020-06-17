package web.cinema.security.impl;

import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.cinema.exception.AuthenticationException;
import web.cinema.model.Role;
import web.cinema.model.User;
import web.cinema.security.AuthenticationService;
import web.cinema.service.RoleService;
import web.cinema.service.ShoppingCartService;
import web.cinema.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     PasswordEncoder passwordEncoder,
                                     RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDb = userService.findByEmail(email);
        if (passwordEncoder.matches(password, userFromDb.getPassword())) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect password or email");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        Role role = roleService.getRoleByName("USER");
        user.setRoles(Set.of(role));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
