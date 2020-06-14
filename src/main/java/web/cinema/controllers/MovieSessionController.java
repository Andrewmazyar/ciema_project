package web.cinema.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.controllers.mappers.MovieSessionMapper;
import web.cinema.model.dto.MovieSessionRequestDto;
import web.cinema.model.dto.MovieSessionResponseDto;
import web.cinema.service.MovieSessionService;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {

    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public void add(@RequestBody @Valid MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionMapper.convertToMovieSession(movieSessionRequestDto);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> get(@RequestParam Long movieId,
                                  @RequestParam String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return movieSessionService.findAvailableSessions(movieId,
                LocalDate.parse(date, formatter))
                .stream()
                .map(movieSessionMapper::convertMovieSessionToDto)
                .collect(Collectors.toList());
    }
}
