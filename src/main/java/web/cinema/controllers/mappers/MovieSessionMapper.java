package web.cinema.controllers.mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;
import web.cinema.model.MovieSession;
import web.cinema.model.dto.MovieSessionRequestDto;
import web.cinema.model.dto.MovieSessionResponseDto;
import web.cinema.service.CinemaHallService;
import web.cinema.service.MovieService;
import web.cinema.service.MovieSessionService;

@Component
public class MovieSessionMapper {

    private final MovieSessionService movieSessionService;
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    public MovieSessionMapper(MovieSessionService movieSessionService,
                              CinemaHallService cinemaHallService, MovieService movieService) {
        this.movieSessionService = movieSessionService;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    public MovieSessionResponseDto convertMovieSessionToDto(MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setCinemaHallId(movieSession.getCinemaHall().getCinemaHallId());
        dto.setTitle(movieSession.getMovie().getTitle());
        dto.setShowTime(movieSession.getShowTime().toString());
        return dto;
    }

    public MovieSession convertToMovieSession(MovieSessionRequestDto movieSessionRequestDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(
                movieService.getById(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(
                cinemaHallService.getById(movieSessionRequestDto.getCinemaHallId()));
        movieSession.setShowTime(
                LocalDateTime.parse(movieSessionRequestDto.getMovieSessionTime(),
                        formatter));
        return movieSessionService.add(movieSession);
    }
}
