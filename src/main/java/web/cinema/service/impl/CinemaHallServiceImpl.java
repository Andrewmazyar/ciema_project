package web.cinema.service.impl;

import java.util.List;
import web.cinema.dao.CinemaHallDao;
import web.cinema.lib.Inject;
import web.cinema.lib.Service;
import web.cinema.model.CinemaHall;
import web.cinema.service.CinemaHallService;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
