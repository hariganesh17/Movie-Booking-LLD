package Controller;

import Entity.Movie;
import Enum.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController{
    List<Movie> allMovies;
    Map<City,List<Movie>> cityVsMovie;
    Map<String,Movie> movieNameMap;

    public MovieController(){
        allMovies = new ArrayList<>();
        cityVsMovie = new HashMap<City, List<Movie>>();
        movieNameMap = new HashMap<>();
    }

    public void addMovies(City city, Movie movie){
        allMovies.add(movie);
        List<Movie> movieList = cityVsMovie.getOrDefault(city,new ArrayList<>());
        movieList.add(movie);
        cityVsMovie.put(city,movieList);
        movieNameMap.put(movie.getMovieName().toLowerCase(),movie);
    }

    public List<Movie> getAllMoviesByCity(City city){
        return cityVsMovie.getOrDefault(city,new ArrayList<>());
    }

    public Movie getMovieByName(String movieName){
        return movieNameMap.get(movieName.toLowerCase());
    }
}