package web.cinema.service;

import web.cinema.model.MovieSession;
import web.cinema.model.ShoppingCart;
import web.cinema.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);
}
