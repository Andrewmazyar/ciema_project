package web.cinema.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long id;
    private List<TicketDto> shoppingCartTickets;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TicketDto> getShoppingCartTickets() {
        return shoppingCartTickets;
    }

    public void setShoppingCartTickets(List<TicketDto> shoppingCartTickets) {
        this.shoppingCartTickets = shoppingCartTickets;
    }
}
