package web.cinema.controllers;

import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.cinema.model.Role;
import web.cinema.model.User;
import web.cinema.service.RoleService;
import web.cinema.service.UserService;

public class InjectDataRolesAndUser {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public InjectDataRolesAndUser(UserService userService,
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
        User admin = new User();
        admin.setEmail("hector@gmail.com");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN"),
                roleService.getRoleByName("USER")));
        userService.add(admin);

    }
}
