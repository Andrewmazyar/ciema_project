package web.cinema.controllers.mappers;

import org.springframework.stereotype.Component;
import web.cinema.model.CinemaHall;
import web.cinema.model.dto.CinemaHallResponseDto;

@Component
public class CinemaHallMapper {

    public CinemaHallResponseDto convertCinemaHallToDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto dto = new CinemaHallResponseDto();
        dto.setCinemaHallCapacity(cinemaHall.getCapacity());
        dto.setCinemaHallDescription(cinemaHall.getDescription());
        dto.setCinemaHallId(cinemaHall.getCinemaHallId());
        return dto;
    }
}
