package web.cinema.model.dto;

public class CinemaHallResponseDto {
    private Long cinemaHallId;
    private Integer cinemaHallCapacity;
    private String cinemaHallDescription;

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public Integer getCinemaHallCapacity() {
        return cinemaHallCapacity;
    }

    public void setCinemaHallCapacity(Integer cinemaHallCapacity) {
        this.cinemaHallCapacity = cinemaHallCapacity;
    }

    public String getCinemaHallDescription() {
        return cinemaHallDescription;
    }

    public void setCinemaHallDescription(String cinemaHallDescription) {
        this.cinemaHallDescription = cinemaHallDescription;
    }
}
