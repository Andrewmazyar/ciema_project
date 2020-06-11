package web.cinema.controllers.mappers;

import org.springframework.stereotype.Component;
import web.cinema.model.CinemaHall;
import web.cinema.model.dto.CinemaHallRequestDto;
import web.cinema.model.dto.CinemaHallResponseDto;
import web.cinema.service.CinemaHallService;

@Component
public class CinemaHallMapper {

    private final CinemaHallService cinemaHallService;

    public CinemaHallMapper(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    public CinemaHallResponseDto convertCinemaHallToDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto dto = new CinemaHallResponseDto();
        dto.setCinemaHallCapacity(cinemaHall.getCapacity());
        dto.setCinemaHallDescription(cinemaHall.getDescription());
        dto.setCinemaHallId(cinemaHall.getCinemaHallId());
        return dto;
    }

    public CinemaHall convertToCinemaHall(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        return cinemaHallService.add(cinemaHall);
    }
}
