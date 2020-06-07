package web.cinema.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import web.cinema.dao.TicketDao;
import web.cinema.exception.DataProcessingException;
import web.cinema.model.Ticket;

@Repository
public class TicketDaoImpl implements TicketDao {
    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);
    private final SessionFactory sessionFactory;

    public TicketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            LOGGER.info("ticket was succeed added to the db");
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t insert ticket entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
