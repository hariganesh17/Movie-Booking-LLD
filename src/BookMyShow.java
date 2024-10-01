import Controller.MovieController;
import Controller.TheaterController;
import Entity.*;
import Enum.City;
import Enum.SeatCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookMyShow {

    MovieController movieController;
    TheaterController theatreController;

    BookMyShow() {
        movieController = new MovieController();
        theatreController = new TheaterController();
    }

    private void initialize(){
        createMovie();
        createTheater();
    }

    private void createTheater(){

        Movie movie1 = movieController.getMovieByName("movie1");
        Theater pvr = new Theater();
        pvr.setTheatreId(1);
        pvr.setCity(City.Chennai);
        pvr.setScreen(createScreen());

        List<Show> showList = new ArrayList<>();
        Show morShow = createShows(1,pvr.getScreen().getFirst(),movie1,8);
        Show eveShow = createShows(2,pvr.getScreen().getLast(),movie1,16);
        showList.add(morShow);
        showList.add(eveShow);
        pvr.setShows(showList);


        theatreController.addTheater(pvr, City.Chennai);
        theatreController.addTheater(pvr, City.bangalore);

    }
    private  void createMovie(){
        Movie movie1 = new Movie();
        movie1.setMovieId(1);
        movie1.setMovieName("Movie1");
        movie1.setMovieDuration(1234);

        Movie movie2 = new Movie();
        movie2.setMovieId(2);
        movie2.setMovieName("Movie2");
        movie2.setMovieDuration(1234);

        Movie movie3 = new Movie();
        movie3.setMovieId(3);
        movie3.setMovieName("Movie3");
        movie3.setMovieDuration(1234);

        movieController.addMovies(City.Chennai,movie1);
        movieController.addMovies(City.bangalore,movie2);
        movieController.addMovies(City.Chennai,movie3);
    }

    private List<Screen> createScreen(){
        List<Screen> screenList = new ArrayList<>();
        Screen screen1 = new Screen();
        screen1.setScreenId(1);
        screen1.setSeats(createSeats());
        screenList.add(screen1);

        return screenList;
    }

    private List<Seat> createSeats(){

        List<Seat> seatList = new ArrayList<>();
        for(int i=1;i<=50;i++){
            Seat seat = new Seat();
            seat.setSeatId(i);
            seat.setSeatCategory(SeatCategory.SILVER);
            seatList.add(seat);
        }
        for(int i=51;i<=100;i++){
            Seat seat = new Seat();
            seat.setSeatId(i);
            seat.setSeatCategory(SeatCategory.GOLD);
            seatList.add(seat);
        }
        return seatList;
    }

    private Show createShows(int showId,Screen screen, Movie movie, int showStartTime){
        Show show = new Show();
        show.setShowId(showId);
        show.setMovie(movie);
        show.setShowStartTime(showStartTime);
        show.setScreen(screen);

        return show;
    }

    private  void createBooking(City city, String movieName){
        List<Movie> movies = movieController.getAllMoviesByCity(city);
        Movie interestedMovie = null;

        for(Movie movie : movies){
            if(movie.getMovieName().equalsIgnoreCase(movieName))
                interestedMovie = movie;
        }

        Map<Theater,List<Show>> theaterListMap = theatreController.getAllShows(interestedMovie,city);
        Map.Entry<Theater,List<Show>> entry = theaterListMap.entrySet().iterator().next();
        List<Show> runningShows = entry.getValue();
        Show interestedShow = runningShows.get(0);

        int seatNum = 15;
        List<Integer> bookedSeats = interestedShow.getBookedSeatIds();
        if(!bookedSeats.contains(seatNum)){
            bookedSeats.add(seatNum);
        }else{
            System.out.println("Seat has already booked");
        }

        System.out.println("BOOKING SUCCESSFUL");


    }
    public static void main(String[] args){
        BookMyShow bookMyShow = new BookMyShow();
        bookMyShow.initialize();

        bookMyShow.createBooking(City.Chennai,"movie1");
    }


}
