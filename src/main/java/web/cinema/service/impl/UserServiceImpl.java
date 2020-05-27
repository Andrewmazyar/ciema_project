package web.cinema.service.impl;

import java.util.List;
import java.util.Optional;
import web.cinema.dao.UserDao;
import web.cinema.lib.Inject;
import web.cinema.lib.Service;
import web.cinema.model.User;
import web.cinema.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }
}
