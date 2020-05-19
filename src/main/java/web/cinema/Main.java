package web.cinema;

import web.cinema.lib.Injector;
import web.cinema.model.Movie;
import web.cinema.service.MovieService;

public class Main {
    private static Injector INJECTOR = Injector.getInstance("web.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Ford v Ferrari");
        movie.setDescription("In 1963, Ford Motor Company Vice President Lee Iacocca "
                + "proposes to Henry Ford II to purchase the cash-strapped Ferrari as a ");

        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
