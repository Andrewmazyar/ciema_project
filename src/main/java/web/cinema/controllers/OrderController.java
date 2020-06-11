package web.cinema.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.controllers.mappers.OrderMapper;
import web.cinema.model.User;
import web.cinema.model.dto.OrderRequestDto;
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
    public void complete(@RequestBody OrderRequestDto orderRequestDto) {
        User user = userService.getById(orderRequestDto.getUserId());
        orderService.completeOrder(shoppingCartService
                .getByUser(user)
                .getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> get(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.getById(userId))
                .stream()
                .map(orderMapper::convertOrderToDto)
                .collect(Collectors.toList());
    }
}
