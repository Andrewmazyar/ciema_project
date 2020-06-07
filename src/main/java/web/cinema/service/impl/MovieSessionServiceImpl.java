package web.cinema.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import web.cinema.dao.MovieSessionDao;
import web.cinema.model.MovieSession;
import web.cinema.service.MovieSessionService;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }
}
