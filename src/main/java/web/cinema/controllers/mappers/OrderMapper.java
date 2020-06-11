package web.cinema.controllers.mappers;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import web.cinema.model.Order;
import web.cinema.model.dto.OrderResponseDto;

@Component
public class OrderMapper {

    private final TicketMapper ticketMapper;

    public OrderMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public OrderResponseDto convertOrderToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderDate(String.valueOf(order.getOrderDate()));
        dto.setOrderTickets(order.getTickets()
                .stream()
                .map(ticketMapper::convertTicketToDto)
                .collect(Collectors.toList()));
        dto.setUserId(order.getUser().getId());
        return dto;
    }
}
