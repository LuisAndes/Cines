package com.example.cinesaragon.model;

import java.util.Objects;

public class Session {

    private final String movie;
    private final String cinema;
    private final String time;
    private final int remainingSeats;
    private final int screen;

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

    public int getIntegerTime() {
        try {
            return Integer.valueOf(time.replace(":", ""));
        } catch (Exception x) {
            return 0;
        }
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return Objects.equals(movie, session.movie) &&
                Objects.equals(cinema, session.cinema) &&
                Objects.equals(time, session.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, cinema, time);
    }
}
