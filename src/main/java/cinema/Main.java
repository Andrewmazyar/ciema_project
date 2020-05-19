package cinema;

import cinema.lib.Injector;
import cinema.model.Movie;
import cinema.service.MovieService;

public class Main {
    private static Injector INJECTOR = Injector.getInstance("home/andriy/IdeaProjects/ciema_project/src/main/java/cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Ford v Ferrari");
        movie.setDescription("In 1963, Ford Motor Company Vice President Lee Iacocca "
                + "proposes to Henry Ford II to purchase the cash-strapped Ferrari as a "
                + "means to boost their car sales by participating in the 24 Hours of Le Mans."
                + " Enzo Ferrari however, uses Ford's offer to secure a more lucrative deal with "
                + "Fiat that allows him to retain complete ownership of Scuderia Ferrari. "
                + "In rejecting the proposed deal with Ford, Ferrari also intentionally insults "
                + "both Ford Motors and Henry Ford II. In response, a furious Ford orders his racing"
                + " division to build a car to defeat Ferrari at Le Mans. For this task, Iacocca hires "
                + "Shelby American owner Carroll Shelby, a racing driver who won Le Mans in 1959 but was"
                + " forced to retire due to a heart condition. In turn, Shelby enlists the help of Ken Miles,"
                + " a hot-tempered British racer and struggling mechanic.");
        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
