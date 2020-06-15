package web.cinema.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.controllers.mappers.OrderMapper;
import web.cinema.model.User;
import web.cinema.model.dto.OrderResponseDto;
import web.cinema.service.OrderService;
import web.cinema.service.ShoppingCartService;
import web.cinema.service.UserService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           UserService userService,
                           ShoppingCartService shoppingCartService,
                           OrderMapper orderMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void complete(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());
        orderService.completeOrder(shoppingCartService
                .getByUser(user)
                .getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> get(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return orderService.getOrderHistory(userService.findByEmail(userDetails.getUsername()))
                .stream()
                .map(orderMapper::convertOrderToDto)
                .collect(Collectors.toList());
    }
}
