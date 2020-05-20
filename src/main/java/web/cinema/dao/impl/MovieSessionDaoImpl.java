package web.cinema.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web.cinema.dao.MovieSessionDao;
import web.cinema.lib.Dao;
import web.cinema.model.MovieSession;
import web.cinema.util.HibernateUtil;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger LOGGER = Logger.getLogger(MovieDaoImpl.class);

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long movieSessionId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setMovieSessionId(movieSessionId);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can`t insert Movie Session entity", e);
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<MovieSession> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(MovieSession.class);
            criteriaQuery.from(MovieSession.class);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDateTime date) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session
                    .createQuery("from MovieSession where showTime = :time AND movie.id = :id");
            query.setParameter("time", date);
            query.setParameter("id", movieId);
            List list = query.list();
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cant insert movie session entity", e);
        }
    }
}
