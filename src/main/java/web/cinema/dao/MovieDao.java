package web.cinema.dao;

import java.util.List;
import web.cinema.model.Movie;

public interface MovieDao {

    Movie add(Movie movie);

    List<Movie> getAll();
}
