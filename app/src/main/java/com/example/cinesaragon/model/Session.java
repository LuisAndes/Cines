package com.example.cinesaragon.model;

public class Session {

    final String movie;
    final String cinema;
    final String time;
    final int remainingSeats;

    private Session(String movie, String cinema, String time, int remainingSeats) {
        this.movie = movie;
        this.cinema = cinema;
        this.time = time;
        this.remainingSeats = remainingSeats;
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
