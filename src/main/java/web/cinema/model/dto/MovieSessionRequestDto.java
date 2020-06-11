package web.cinema.model.dto;

public class MovieSessionRequestDto {
    private Long movieId;
    private Long cinemaHallId;
    private String movieSessionTime;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public String getMovieSessionTime() {
        return movieSessionTime;
    }

    public void setMovieSessionTime(String movieSessionTime) {
        this.movieSessionTime = movieSessionTime;
    }
}
