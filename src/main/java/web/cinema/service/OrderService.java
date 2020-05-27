package web.cinema.service;

import java.util.List;
import web.cinema.model.Orders;
import web.cinema.model.Ticket;
import web.cinema.model.User;

public interface OrderService {
    Orders completeOrder(List<Ticket> tickets, User user);

    List<Orders> getOrderHistory(User user);
}
