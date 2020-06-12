package web.cinema.controllers.mappers;

import org.springframework.stereotype.Component;
import web.cinema.model.Ticket;
import web.cinema.model.dto.TicketDto;

@Component
public class TicketMapper {

    public TicketDto convertTicketToDto(Ticket ticket) {
        TicketDto dto = new TicketDto();
        dto.setMovieTitle(ticket.getMovieSession().getMovie().getTitle());
        dto.setUserEmail(ticket.getUser().getEmail());
        dto.setMovieSessionId(ticket.getMovieSession().getMovieSessionId());
        return dto;
    }
}
