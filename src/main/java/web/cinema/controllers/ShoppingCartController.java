package web.cinema.controllers;

import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.model.ShoppingCart;
import web.cinema.model.Ticket;
import web.cinema.model.dto.ShoppingCartRequestDto;
import web.cinema.model.dto.ShoppingCartResponseDto;
import web.cinema.model.dto.TicketDto;
import web.cinema.service.MovieSessionService;
import web.cinema.service.ShoppingCartService;
import web.cinema.service.UserService;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService movieSessionService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
    }

    @PostMapping("/addmoviesession")
    public void addMovieSession(@RequestBody ShoppingCartRequestDto scrd) {
        shoppingCartService.addSession(movieSessionService.getById(scrd.getMovieSessionId()),
                userService.getById(scrd.getUserId()));
    }

    @GetMapping("/byuser")
    public ShoppingCartResponseDto get(@RequestParam Long userId) {
        return convertShoppingCartToDto(shoppingCartService
                .getByUser(userService.getById(userId)));
    }

    private ShoppingCartResponseDto convertShoppingCartToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        dto.setShoppingCartTickets(shoppingCart.getTickets()
                .stream()
                .map(this::convertTicketToDto)
                .collect(Collectors.toList()));
        dto.setUserId(shoppingCart.getUser().getId());
        return dto;
    }

    private TicketDto convertTicketToDto(Ticket ticket) {
        TicketDto dto = new TicketDto();
        dto.setMovieTitle(ticket.getMovieSession().getMovie().getTitle());
        dto.setUserEmail(ticket.getUser().getEmail());
        dto.setMovieSessionId(ticket.getMovieSession().getMovieSessionId());
        return dto;
    }
}
