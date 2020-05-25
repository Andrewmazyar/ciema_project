package web.cinema.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import web.cinema.dao.CinemaHallDao;
import web.cinema.exception.DataProcessingException;
import web.cinema.lib.Dao;
import web.cinema.model.CinemaHall;
import web.cinema.util.HibernateUtil;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    private static final Logger LOGGER = Logger.getLogger(CinemaHallDaoImpl.class);

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long cinemaHallId = (Long) session.save(cinemaHall);
            cinemaHall.setCinemaHallId(cinemaHallId);
            transaction.commit();
            LOGGER.info("cinema hall was succeed add to the db");
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t insert cinema hall entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<CinemaHall> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get All cinema hall ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
