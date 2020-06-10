package web.cinema.service;

import java.util.List;
import web.cinema.model.User;

public interface UserService {
    User add(User user);

    List<User> getAll();

    User findByEmail(String email);

    public User getById(Long id);
}
