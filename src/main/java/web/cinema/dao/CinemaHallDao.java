package web.cinema.dao;

import java.util.List;
import web.cinema.model.CinemaHall;

public interface CinemaHallDao {

    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    public CinemaHall getById(Long id);
}
