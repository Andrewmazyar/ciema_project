package web.cinema.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.model.MovieSession;
import web.cinema.model.dto.MovieSessionRequestDto;
import web.cinema.model.dto.MovieSessionResponseDto;
import web.cinema.service.CinemaHallService;
import web.cinema.service.MovieService;
import web.cinema.service.MovieSessionService;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {

    private final MovieSessionService movieSessionService;
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieService movieService,
                                  CinemaHallService cinemaHallService) {
        this.movieSessionService = movieSessionService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping
    public void add(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(
                movieService.getById(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(
                cinemaHallService.getById(movieSessionRequestDto.getCinemaHallId()));
        movieSession.setShowTime(
                LocalDateTime.parse(movieSessionRequestDto.getMovieSessionTime(),
                        formatter));
        movieSessionService.add(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> get(@RequestParam Long movieId,
                                  @RequestParam String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return movieSessionService.findAvailableSessions(movieId,
                LocalDate.parse(date, formatter))
                .stream()
                .map(this::convertMovieSessionToDto)
                .collect(Collectors.toList());
    }

    private MovieSessionResponseDto convertMovieSessionToDto(MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setCinemaHallId(movieSession.getCinemaHall().getCinemaHallId());
        dto.setTitle(movieSession.getMovie().getTitle());
        dto.setShowTime(movieSession.getShowTime().toString());
        return dto;
    }

}
