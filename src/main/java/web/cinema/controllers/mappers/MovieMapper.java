package web.cinema.controllers.mappers;

import org.springframework.stereotype.Component;
import web.cinema.model.Movie;
import web.cinema.model.dto.MovieResponseDto;

@Component
public class MovieMapper {

    public MovieResponseDto convertToMovieDto(Movie movie) {
        MovieResponseDto dto = new MovieResponseDto();
        dto.setMovieDescription(movie.getDescription());
        dto.setMovieId(movie.getMovieId());
        dto.setMovieTitle(movie.getTitle());
        return dto;
    }
}
