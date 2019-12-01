package com.example.cinesaragon.model;

public class Ticket {

    final String movie;
    final String session;
    final String cinema;

    private Ticket(String movie,
                   String session,
                   String cinema) {
        this.movie = movie;
        this.session = session;
        this.cinema = cinema;
    }

    public String getSession() {
        return session;
    }

    public String getCinema() {
        return cinema;
    }

    public class Builder {
        String movie = "";
        String session = "";
        String cinema = "";

        public Builder() {
        }

        public Builder seeMovie(String movie) {
            this.movie = movie;
            return this;
        }

        public Builder at(String session) {
            this.session = session;
            return this;
        }

        public Builder inCinema(String cinema) {
            this.cinema = cinema;
            return this;
        }

        public Ticket purchase() {
            return new Ticket(movie, session, cinema);
        }
    }
}
