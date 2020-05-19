package web.cinema.service.impl;

import java.util.List;
import web.cinema.dao.MovieDao;
import web.cinema.lib.Inject;
import web.cinema.lib.Service;
import web.cinema.model.Movie;
import web.cinema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao moviedao;

    @Override
    public Movie add(Movie movie) {
        return moviedao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return moviedao.getAll();
    }
}
