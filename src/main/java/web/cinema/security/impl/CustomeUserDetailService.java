package web.cinema.security.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.cinema.model.Role;
import web.cinema.model.User;
import web.cinema.service.UserService;

@Service
public class CustomeUserDetailService implements UserDetailsService {
    private final UserService userService;

    public CustomeUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;
        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(email);
            userBuilder.password(user.getPassword());
            userBuilder.roles(user.getRoles()
                    .stream()
                    .map(Role::getRoleName)
                    .map(Enum::name)
                    .toArray(String[]::new));
        } else {
            throw new UsernameNotFoundException("No such User");
        }
        return userBuilder.build();
    }
}
