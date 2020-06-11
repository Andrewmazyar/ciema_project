package web.cinema.controllers.mappers;

import org.springframework.stereotype.Component;
import web.cinema.model.MovieSession;
import web.cinema.model.dto.MovieSessionResponseDto;

@Component
public class MovieSessionMapper {

    public MovieSessionResponseDto convertMovieSessionToDto(MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setCinemaHallId(movieSession.getCinemaHall().getCinemaHallId());
        dto.setTitle(movieSession.getMovie().getTitle());
        dto.setShowTime(movieSession.getShowTime().toString());
        return dto;
    }
}
