package web.cinema.dao.impl;

import java.time.LocalDate;
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
    private static final Logger LOGGER = Logger.getLogger(MovieSessionDaoImpl.class);

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
        } catch (Exception e) {
            throw new RuntimeException("Can`t get All movie sessions", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session
                    .createQuery("from MovieSession where movie.movieId = :id "
                            + "AND showTime > :time");
            query.setParameter("id", movieId);
            query.setParameter("time", date.atStartOfDay());
            List list = query.list();
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cant get movie session by the date or movie id", e);
        }
    }
}
