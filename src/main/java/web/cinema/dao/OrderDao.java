package web.cinema.dao;

import java.util.List;
import web.cinema.model.Order;
import web.cinema.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrderHistory(User user);
}
