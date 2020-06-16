package web.cinema.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.cinema.dao.RoleDao;
import web.cinema.exception.DataProcessingException;
import web.cinema.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role add(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            LOGGER.info("role was successfully added to the db");
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t insert Role entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Role where roleName = :roleName");
            query.setParameter("roleName", roleName);
            return (Role) query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Cant get user by email", e);
        }
    }
}
