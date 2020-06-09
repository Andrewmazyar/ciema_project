package web.cinema.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import web.cinema.dao.MovieDao;
import web.cinema.model.Movie;
import web.cinema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao moviedao;

    public MovieServiceImpl(MovieDao moviedao) {
        this.moviedao = moviedao;
    }

    @Override
    public Movie add(Movie movie) {
        return moviedao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return moviedao.getAll();
    }
}
