package web.cinema.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.controllers.mappers.ShoppingCartMapper;
import web.cinema.model.dto.ShoppingCartRequestDto;
import web.cinema.model.dto.ShoppingCartResponseDto;
import web.cinema.service.MovieSessionService;
import web.cinema.service.ShoppingCartService;
import web.cinema.service.UserService;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService movieSessionService;
    private final UserService userService;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  UserService userService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/add-movie-session")
    public void addMovieSession(@RequestBody ShoppingCartRequestDto scrd,
                                Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        shoppingCartService.addSession(movieSessionService.getById(scrd.getMovieSessionId()),
                userService.findByEmail(userDetails.getUsername()));
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto get(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return shoppingCartMapper.convertShoppingCartToDto(shoppingCartService
                .getByUser(userService.findByEmail(userDetails.getUsername())));
    }
}
