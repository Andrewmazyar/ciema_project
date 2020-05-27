package web.cinema.dao;

import java.util.List;
import web.cinema.model.Orders;
import web.cinema.model.User;

public interface OrderDao {
    Orders add(Orders order);

    List<Orders> getOrderHistory(User user);
}
