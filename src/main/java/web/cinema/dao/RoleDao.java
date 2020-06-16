package web.cinema.dao;

import web.cinema.model.Role;

public interface RoleDao {

    Role add(Role role);

    Role getRoleByName(String roleName);
}
