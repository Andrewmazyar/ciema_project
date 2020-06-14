package web.cinema.controllers;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.controllers.mappers.MovieMapper;
import web.cinema.model.dto.MovieRequestDto;
import web.cinema.model.dto.MovieResponseDto;
import web.cinema.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService,
                           MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @RequestMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(movieMapper::convertToMovieDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void add(@RequestBody @Valid MovieRequestDto movieRequestDto) {
        movieMapper.convertToMovie(movieRequestDto);
    }
}
