package Controller;

import Entity.Movie;
import Entity.Show;
import Enum.City;
import Entity.Theater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class TheaterController{
    Map<City, List<Theater>> cityVsTheater;
    List<Theater> theaters;

    public TheaterController(){
        cityVsTheater = new HashMap<City, List<Theater>>();
        theaters = new ArrayList<>();
    }
    public void addTheater(City city,Theater theater){
        theaters.add(theater);
        List<Theater> theaterList = cityVsTheater.getOrDefault(city,new ArrayList<>());
        theaterList.add(theater);
        cityVsTheater.put(city,theaterList);
    }
    public Map<Theater,List<Show>> getAllShows(City city, String movieName){
        Map<Theater,List<Show>> availableShows = new HashMap<>();
        List<Theater> availableTheaters = cityVsTheater.get(city);
        for(Theater theater : availableTheaters){
            List<Show> matchingShows = new ArrayList<>();
            List<Show> shows = theater.getShows();
                for(Show show : shows){
                    if(show.getMovie().getMovieName().equalsIgnoreCase(movieName))
                        matchingShows.add(show);
                }
                if(!matchingShows.isEmpty())
                    availableShows.put(theater,matchingShows);
        }
        return availableShows;
    }
}