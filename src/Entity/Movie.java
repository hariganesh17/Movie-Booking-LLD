package Entity;

public  class Movie {

    int movieId;
    String movieName;
    int movieDurationInMinutes;

    public Movie(int movieId, String movieName, int movieDurationInMinutes) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDurationInMinutes = movieDurationInMinutes;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getMovieDuration() {
        return movieDurationInMinutes;
    }
}