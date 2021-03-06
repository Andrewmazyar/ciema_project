package web.cinema.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import web.cinema.dao.ShoppingCartDao;
import web.cinema.dao.TicketDao;
import web.cinema.model.MovieSession;
import web.cinema.model.ShoppingCart;
import web.cinema.model.Ticket;
import web.cinema.model.User;
import web.cinema.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;

    private final TicketDao ticketDao;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setMovieSession(movieSession);
        ticket.setUser(user);
        ShoppingCart shoppingCart = shoppingCartDao.getByUser(user);
        List<Ticket> tickets = shoppingCart.getTickets();
        Ticket ticket1 = ticketDao.add(ticket);
        tickets.add(ticket1);
        shoppingCart.setTickets(tickets);
        shoppingCartDao.update(shoppingCart);;
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }
}
