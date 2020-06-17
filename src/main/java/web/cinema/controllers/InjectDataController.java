package web.cinema.controllers;

import java.util.Set;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import web.cinema.model.Role;
import web.cinema.model.User;
import web.cinema.service.RoleService;
import web.cinema.service.UserService;

@Controller
public class InjectDataController {
    private static final Logger LOGGER = Logger.getLogger(InjectDataController.class);

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public InjectDataController(UserService userService,
                                RoleService roleService,
                                PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void init() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName((Role.RoleName.USER));
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("hector@gmail.com");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.add(admin);
        LOGGER.info("ADMIN was successfully added to the db");
    }
}
