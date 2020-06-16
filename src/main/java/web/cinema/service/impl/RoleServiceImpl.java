package web.cinema.service.impl;

import org.springframework.stereotype.Service;
import web.cinema.dao.RoleDao;
import web.cinema.model.Role;
import web.cinema.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }
}
