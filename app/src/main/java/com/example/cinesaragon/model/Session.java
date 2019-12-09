package com.example.cinesaragon.model;

public class Session {

    final String movie;
    final String cinema;
    final String time;
    final int remainingSeats;
    final int screen;

    private Session() {
        this.movie = "";
        this.cinema = "";
        this.time = "";
        this.remainingSeats = 0;
        this.screen = 0;
    }

    private Session(String movie, String cinema, String time, int remainingSeats, int screen) {
        this.movie = movie;
        this.cinema = cinema;
        this.time = time;
        this.remainingSeats = remainingSeats;
        this.screen = screen;
    }

    public int getScreen() {
        return screen;
    }

    public String getCinema() {
        return cinema;
    }

    public String getMovie() {
        return movie;
    }

    public String getTime() {
        return time;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }
}
