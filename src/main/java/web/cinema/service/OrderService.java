package web.cinema.service;

import java.util.List;
import web.cinema.model.Order;
import web.cinema.model.Ticket;
import web.cinema.model.User;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
