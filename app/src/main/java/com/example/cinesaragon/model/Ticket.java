package com.example.cinesaragon.model;

public class Ticket {

    private final String user;
    private final String movie;
    private final String day;
    private final String session;
    private final String cinema;
    private final int screen;

    private Ticket(String user,
                   String movie,
                   String day, String session,
                   String cinema, int screen) {
        this.user = user;
        this.movie = movie;
        this.day = day;
        this.session = session;
        this.cinema = cinema;
        this.screen = screen;
    }

    public String getSession() {
        return session;
    }

    public String getCinema() {
        return cinema;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Ticket{");
        sb.append("movie='").append(movie).append('\'');
        sb.append(", session='").append(day).append(" ").append(session).append('\'');
        sb.append(", screen=").append(screen);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private String user = "";
        private String movie = "";
        private String day = "";
        private String session = "";
        private String cinema = "";
        private int screen = 0;

        public Builder(String user) {
            this.user = user;
        }

        public Builder(String user, FullInfoSession session) {
            this(user);
            this.movie = session.getMovie().getName();
            this.cinema = session.getCinema().getName();
            this.session = session.getSession().getTime();
            this.screen = session.getSession().getScreen();
        }

        public Builder seeMovie(String movie) {
            this.movie = movie;
            return this;
        }

        public Builder onDay(String day) {
            this.day = day;
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

        public Builder inScreen(int screen) {
            this.screen = screen;
            return this;
        }

        public Ticket purchase() {
            return new Ticket(user, movie, day, session, cinema, screen);
        }
    }
}
