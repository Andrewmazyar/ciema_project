package web.cinema.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import web.cinema.dao.OrderDao;
import web.cinema.lib.Inject;
import web.cinema.lib.Service;
import web.cinema.model.Order;
import web.cinema.model.Ticket;
import web.cinema.model.User;
import web.cinema.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTickets(tickets);
        order.setUser(user);
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
