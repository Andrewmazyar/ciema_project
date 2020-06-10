package web.cinema.model.dto;

import java.util.List;

public class OrderResponseDto {
    private List<TicketDto> orderTickets;
    private String orderDate;
    private Long userId;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TicketDto> getOrderTickets() {
        return orderTickets;
    }

    public void setOrderTickets(List<TicketDto> orderTickets) {
        this.orderTickets = orderTickets;
    }
}
