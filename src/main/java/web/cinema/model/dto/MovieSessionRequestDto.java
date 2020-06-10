package web.cinema.model.dto;

public class MovieSessionRequestDto {
    private Long movieSessionId;
    private Long cinemaHallId;
    private String movieSessionTime;

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
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
