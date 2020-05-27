package web.cinema.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web.cinema.dao.ShoppingCartDao;
import web.cinema.exception.DataProcessingException;
import web.cinema.lib.Dao;
import web.cinema.model.ShoppingCart;
import web.cinema.model.User;
import web.cinema.util.HibernateUtil;

@Dao
public class ShoppingCardDaoImpl implements ShoppingCartDao {
    private static final Logger LOGGER = Logger.getLogger(ShoppingCardDaoImpl.class);

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ShoppingCart c "
                    + "left join fetch c.tickets Ticket "
                    + "where c.user = :user", ShoppingCart.class);
            query.setParameter("user", user);
            ShoppingCart shoppingCart = (ShoppingCart) query.uniqueResult();
            transaction.commit();
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cant get shopping cart by user", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
