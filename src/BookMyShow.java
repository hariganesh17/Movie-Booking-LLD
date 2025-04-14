import Controller.MovieController;
import Controller.TheaterController;
import Entity.*;
import Enum.City;
import Enum.SeatCategory;

import java.util.ArrayList;
import java.util.List;

public class BookMyShow {
    private final MovieController movieController;
    private final TheaterController theaterController;

    public BookMyShow() {
        BookingManager bookingManager = BookingManager.getInstance();
        this.movieController = bookingManager.getMovieController();
        this.theaterController = bookingManager.getTheaterController();
    }

    public void initialize() {
        createMovies();
        createTheaters();
    }

    private void createMovies() {
        Movie movie1 = new Movie(1, "Joker", 180);
        Movie movie2 = new Movie(2, "Avenger", 120);
        Movie movie3 = new Movie(3, "Hulk", 120);

        movieController.addMovies(City.Chennai, movie1);
        movieController.addMovies(City.Chennai, movie2);
        movieController.addMovies(City.bangalore, movie3);
    }

    private void createTheaters() {
        Movie movie1 = movieController.getMovieByName("Joker");
        Theater pvr = new Theater();
        pvr.setTheatreId(1);
        pvr.setCity(City.Chennai);
        pvr.setScreen(createScreens());

        List<Show> showList = new ArrayList<>();
        showList.add(createShow(1, movie1, pvr.getScreen().get(0), 9));
        showList.add(createShow(2, movie1, pvr.getScreen().get(0), 12));

        pvr.setShows(showList);

        theaterController.addTheater(City.Chennai, pvr);
        theaterController.addTheater(City.bangalore, pvr);
    }

    private List<Screen> createScreens() {
        List<Screen> screens = new ArrayList<>();
        Screen screen = new Screen();
        screen.setScreenId(1);
        screen.setSeats(createSeats());
        screens.add(screen);
        return screens;
    }

    private List<Seat> createSeats() {
        List<Seat> seatList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            seatList.add(new Seat(i, SeatCategory.SILVER));
        }
        for (int i = 51; i <= 100; i++) {
            seatList.add(new Seat(i, SeatCategory.GOLD));
        }
        for (int i = 101; i <= 150; i++) {
            seatList.add(new Seat(i, SeatCategory.PLATINUM));
        }
        return seatList;
    }

    private Show createShow(int showId, Movie movie, Screen screen, int showStartTime) {
        Show show = new Show();
        show.setShowId(showId);
        show.setMovie(movie);
        show.setScreen(screen);
        show.setShowStartTime(showStartTime);
        return show;
    }
}