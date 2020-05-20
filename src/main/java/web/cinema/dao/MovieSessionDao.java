package web.cinema.dao;

import java.time.LocalDateTime;
import java.util.List;
import web.cinema.model.MovieSession;

public interface MovieSessionDao {

    MovieSession add(MovieSession movieSession);

    List<MovieSession> getAll();

    List<MovieSession> findAvailableSessions(Long movieId, LocalDateTime date);
}
