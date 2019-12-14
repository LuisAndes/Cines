package com.example.cinesaragon.model.params;

public class GetSessionParams {

    private final String movieId;
    private final String cinemaId;
    private final String time;
    private final String screen;

    public GetSessionParams(String movieId, String cinemaId, String time, String screen) {
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.time = time;
        this.screen = screen;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public String getTime() {
        return time;
    }

    public String getScreen() {
        return screen;
    }
}
