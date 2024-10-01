package Controller;

import Entity.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  Enum.City;

public class MovieController {
    Map<City, List<Movie>> cityVsMovie;
    List<Movie> allMovies;

    public MovieController(){
        cityVsMovie = new HashMap<>();
        allMovies = new ArrayList<>();
    }

    public void addMovies(City city, Movie movie){
        allMovies.add(movie);
        List<Movie> movieList = cityVsMovie.getOrDefault(city,new ArrayList<>());
        movieList.add(movie);
        cityVsMovie.put(city,movieList);
    }

    public List<Movie> getAllMoviesByCity(City city){
        return cityVsMovie.get(city);
    }

    public Movie getMovieByName(String movieName){
        for(Movie m : allMovies){
            if(m.getMovieName().equalsIgnoreCase(movieName))
                return m;
        }
        return null;
    }
}
