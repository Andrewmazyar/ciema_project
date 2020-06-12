package web.cinema.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import web.cinema.dao.UserDao;
import web.cinema.model.User;
import web.cinema.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

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

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }
}
