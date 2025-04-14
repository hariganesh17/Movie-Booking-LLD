import Controller.MovieController;
import Controller.TheaterController;
import Entity.*;
import Enum.City;

import java.util.List;
import java.util.Map;

public class BookingManager {
    private static BookingManager instance;
    private final MovieController movieController;
    private final TheaterController theaterController;

    // Singleton Pattern: Implemented Singleton for Booking Manager to ensure a single instance handles all booking requests,

    private BookingManager() {
        this.movieController = new MovieController();
        this.theaterController = new TheaterController();
    }

    public static BookingManager getInstance() {
        if (instance == null) {
            synchronized (BookingManager.class) {
                if (instance == null) {
                    instance = new BookingManager();
                }
            }
        }
        return instance;
    }

    public MovieController getMovieController() {
        return movieController;
    }

    public TheaterController getTheaterController() {
        return theaterController;
    }
    // Booking logic
    public  void createBooking(City city, String movieName, int seatNum) {
        Map<Theater, List<Show>> theaterListMap = theaterController.getAllShows(city, movieName);
        if (theaterListMap.isEmpty()) {
            System.out.println("No shows available for this movie in the selected city.");
            return;
        }

        // -- For simplicity, picking the first theater and its first show.

        Map.Entry<Theater, List<Show>> entry = theaterListMap.entrySet().iterator().next();
        List<Show> runningShow = entry.getValue();
        Show interestedShow = runningShow.get(0); // hardcoded

        List<Integer> bookedSeats = interestedShow.getBookedSeatIds();

        //seat duplication check.
        //first check
        if (!bookedSeats.contains(seatNum)) {
            //second check
            synchronized (bookedSeats){
                if (!bookedSeats.contains(seatNum)){
                    bookedSeats.add(seatNum);
                    System.out.println("Booking Successful for seat number: " + seatNum);
                }else {
                    System.out.println("Seat Already Booked: " + seatNum);
                }
            }
        } else {
            System.out.println("Seat Already Booked: " + seatNum);
        }
    }
}