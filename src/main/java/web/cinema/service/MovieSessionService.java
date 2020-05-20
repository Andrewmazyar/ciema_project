package web.cinema.service;

import java.time.LocalDateTime;
import java.util.List;
import web.cinema.model.MovieSession;

public interface MovieSessionService {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDateTime date);
}
