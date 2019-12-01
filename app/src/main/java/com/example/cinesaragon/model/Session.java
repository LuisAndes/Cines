package com.example.cinesaragon.model;

public class Session {

    final String id;
    final String movie;
    final String time;
    final int remainingSeats;

    private Session(String id, String movie, String time, int remainingSeats) {
        this.id = id;
        this.movie = movie;
        this.time = time;
        this.remainingSeats = remainingSeats;
    }

    public String getId() {
        return id;
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
