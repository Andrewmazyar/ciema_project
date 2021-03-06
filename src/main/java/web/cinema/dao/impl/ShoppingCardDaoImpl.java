package web.cinema.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.cinema.dao.ShoppingCartDao;
import web.cinema.exception.DataProcessingException;
import web.cinema.model.ShoppingCart;
import web.cinema.model.User;

@Repository
public class ShoppingCardDaoImpl implements ShoppingCartDao {
    private static final Logger LOGGER = Logger.getLogger(ShoppingCardDaoImpl.class);
    private final SessionFactory sessionFactory;

    public ShoppingCardDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            LOGGER.info("shopping cart was succeed added to the db");
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t insert shopping cart entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from ShoppingCart c "
                    + "left join fetch c.tickets Ticket "
                    + "where c.user = :user", ShoppingCart.class);
            query.setParameter("user", user);
            ShoppingCart shoppingCart = (ShoppingCart) query.uniqueResult();
            return shoppingCart;
        } catch (Exception e) {
            throw new DataProcessingException("Cant get shopping cart by user", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cant update shopping cart by user", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
