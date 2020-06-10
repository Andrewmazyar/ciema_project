package web.cinema.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.model.Movie;
import web.cinema.model.dto.MovieRequestDto;
import web.cinema.model.dto.MovieResponseDto;
import web.cinema.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(this::convertToMovieDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void add(@RequestBody MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setDescription(movieRequestDto.getMovieDescription());
        movie.setTitle(movieRequestDto.getMovieTitle());
        movieService.add(movie);
    }

    private MovieResponseDto convertToMovieDto(Movie movie) {
        MovieResponseDto dto = new MovieResponseDto();
        dto.setMovieDescription(movie.getDescription());
        dto.setMovieId(movie.getMovieId());
        dto.setMovieTitle(movie.getTitle());
        return dto;
    }
}
