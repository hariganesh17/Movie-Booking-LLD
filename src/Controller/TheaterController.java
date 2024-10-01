package Controller;

import Entity.Movie;
import Entity.Show;
import Entity.Theater;
import Enum.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheaterController {

    Map<City, List<Theater>> cityVsTheater;
    List<Theater> theaters;

    public TheaterController(){
        cityVsTheater = new HashMap<>();
        theaters = new ArrayList<>();
    }

    public void addTheater(Theater theater, City city){
        theaters.add(theater);

        List<Theater> theaterList = cityVsTheater.getOrDefault(city,new ArrayList<>());
        theaterList.add(theater);
        cityVsTheater.put(city,theaterList);
    }

    public Map<Theater,List<Show>> getAllShows(Movie movie, City city){
        Map<Theater, List<Show>> availableShows = new HashMap<>();

        List<Theater> availableTheaters = cityVsTheater.get(city);

        for(Theater t : availableTheaters){
            List<Show> matchingShows = new ArrayList<>();
            List<Show> shows = t.getShows();
            for(Show s : shows){
                if(s.getMovie().getMovieId() == movie.getMovieId())
                    matchingShows.add(s);
            }
            if(!matchingShows.isEmpty())
                    availableShows.put(t,matchingShows);
        }

        return availableShows;
    }
}
