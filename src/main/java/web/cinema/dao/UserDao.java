package web.cinema.dao;

import java.util.List;
import java.util.Optional;
import web.cinema.model.User;

public interface UserDao {

    User add(User user);

    List<User> getAll();

    Optional<User> findByEmail(String email);
}
