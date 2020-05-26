package web.cinema.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import web.cinema.dao.TicketDao;
import web.cinema.exception.DataProcessingException;
import web.cinema.lib.Dao;
import web.cinema.model.Ticket;
import web.cinema.util.HibernateUtil;

@Dao
public class TicketDaoImpl implements TicketDao {
    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);

    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            session.flush();
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
