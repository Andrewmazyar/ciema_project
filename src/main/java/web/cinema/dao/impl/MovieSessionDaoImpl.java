package web.cinema.dao.impl;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.cinema.dao.MovieSessionDao;
import web.cinema.exception.DataProcessingException;
import web.cinema.model.MovieSession;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger LOGGER = Logger.getLogger(MovieSessionDaoImpl.class);
    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Long movieSessionId = (Long) session.save(movieSession);
            movieSession.setMovieSessionId(movieSessionId);
            transaction.commit();
            LOGGER.info("movie session was succeed added to the db");
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t insert Movie Session entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<MovieSession> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(MovieSession.class);
            criteriaQuery.from(MovieSession.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get All movie sessions", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
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
            throw new DataProcessingException("Cant get movie session by the date or movie id", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MovieSession getById(Long movieSessionId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MovieSession.class, movieSessionId);
        } catch (Exception e) {
            throw new DataProcessingException("Cant get movie session by id", e);
        }
    }
}
