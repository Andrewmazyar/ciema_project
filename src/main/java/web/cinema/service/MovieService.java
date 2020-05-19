package web.cinema.service;

import java.util.List;
import web.cinema.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}

