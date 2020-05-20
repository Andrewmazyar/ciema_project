package web.cinema.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import web.cinema.dao.MovieSessionDao;
import web.cinema.lib.Inject;
import web.cinema.lib.Service;
import web.cinema.model.MovieSession;
import web.cinema.service.MovieSessionService;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDateTime date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }
}
