package web.cinema.controllers.mappers;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import web.cinema.model.ShoppingCart;
import web.cinema.model.dto.ShoppingCartResponseDto;

@Component
public class ShoppingCartMapper {

    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto convertShoppingCartToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        dto.setShoppingCartTickets(shoppingCart.getTickets()
                .stream()
                .map(ticketMapper::convertTicketToDto)
                .collect(Collectors.toList()));
        dto.setUserId(shoppingCart.getUser().getId());
        return dto;
    }
}
