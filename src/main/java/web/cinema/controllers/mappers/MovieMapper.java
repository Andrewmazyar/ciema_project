package web.cinema.controllers.mappers;

import org.springframework.stereotype.Component;
import web.cinema.model.Movie;
import web.cinema.model.dto.MovieRequestDto;
import web.cinema.model.dto.MovieResponseDto;
import web.cinema.service.MovieService;

@Component
public class MovieMapper {

    private final MovieService movieService;

    public MovieMapper(MovieService movieService) {
        this.movieService = movieService;
    }

    public MovieResponseDto convertToMovieDto(Movie movie) {
        MovieResponseDto dto = new MovieResponseDto();
        dto.setMovieDescription(movie.getDescription());
        dto.setMovieId(movie.getMovieId());
        dto.setMovieTitle(movie.getTitle());
        return dto;
    }

    public Movie convertToMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setDescription(movieRequestDto.getMovieDescription());
        movie.setTitle(movieRequestDto.getMovieTitle());
        return movieService.add(movie);
    }
}
