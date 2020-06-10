package web.cinema.service;

import java.time.LocalDate;
import java.util.List;
import web.cinema.model.MovieSession;

public interface MovieSessionService {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    public MovieSession getById(Long id);
}
