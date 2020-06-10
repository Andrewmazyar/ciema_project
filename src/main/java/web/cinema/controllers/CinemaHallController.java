package web.cinema.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.model.CinemaHall;
import web.cinema.model.dto.CinemaHallRequestDto;
import web.cinema.model.dto.CinemaHallResponseDto;
import web.cinema.service.CinemaHallService;

@RestController
@RequestMapping("/cinemahalls")
public class CinemaHallController {

    private final CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @RequestMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll()
                .stream()
                .map(this::convertCinemaHallToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void add(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        cinemaHallService.add(cinemaHall);
    }

    private CinemaHallResponseDto convertCinemaHallToDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto dto = new CinemaHallResponseDto();
        dto.setCinemaHallCapacity(cinemaHall.getCapacity());
        dto.setCinemaHallDescription(cinemaHall.getDescription());
        dto.setCinemaHallId(cinemaHall.getCinemaHallId());
        return dto;
    }
}
