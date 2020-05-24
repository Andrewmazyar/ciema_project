package web.cinema.dao;

import java.util.List;
import web.cinema.model.User;

public interface UserDao {

    User add(User user);

    List<User> getAll();

    User findByEmail(String email);
}
