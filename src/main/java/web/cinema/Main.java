package web.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import web.cinema.exception.AuthenticationException;
import web.cinema.lib.Injector;
import web.cinema.model.CinemaHall;
import web.cinema.model.Movie;
import web.cinema.model.MovieSession;
import web.cinema.model.ShoppingCart;
import web.cinema.model.User;
import web.cinema.security.AuthenticationService;
import web.cinema.service.CinemaHallService;
import web.cinema.service.MovieService;
import web.cinema.service.MovieSessionService;
import web.cinema.service.OrderService;
import web.cinema.service.ShoppingCartService;
import web.cinema.service.UserService;

public class Main {
    private static Injector INJECTOR = Injector.getInstance("web.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Ford v Ferrari");
        movie.setDescription("In 1963, Ford Motor Company Vice President Lee Iacocca ");
        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription("In 1963, Ford Motor Company");
        cinemaHall.setCapacity(12);
        CinemaHallService cinemaHallService
                = (CinemaHallService) INJECTOR.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        movieSession.setShowTime(LocalDateTime.parse("2014-04-08 12:30", formatter));
        MovieSessionService movieSessionService
                = (MovieSessionService) INJECTOR.getInstance((MovieSessionService.class));
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(movie.getMovieId(),
                LocalDate.parse("2014-04-08")).forEach(System.out::println);

        User hector = new User();
        hector.setEmail("hector@gmail.com");
        hector.setPassword("very_secret_password");
        AuthenticationService authenticationService
                = (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);
        authenticationService.register(hector.getEmail(), hector.getPassword());
        UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
        userService.findByEmail("hector@gmail.com");
        try {
            authenticationService.login("hector@gmail.com","very_secret_password");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        ShoppingCartService shoppingCartService
                = (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
        ShoppingCart cart = shoppingCartService
                .getByUser(userService.findByEmail("hector@gmail.com"));
        System.out.println("CartID: " + cart.getId());
        shoppingCartService.addSession(movieSession, userService.findByEmail("hector@gmail.com"));

        OrderService orderService = (OrderService) INJECTOR.getInstance(OrderService.class);
        orderService.completeOrder(cart.getTickets(), userService.findByEmail("hector@gmail.com"));
        orderService.getOrderHistory(userService.findByEmail("hector@gmail.com"));
    }
}
