package web.cinema.service;

import web.cinema.model.Role;

public interface RoleService {

    Role add(Role role);

    Role getRoleByName(String roleName);
}
