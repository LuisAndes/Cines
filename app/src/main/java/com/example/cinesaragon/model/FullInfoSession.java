package com.example.cinesaragon.model;

public class FullInfoSession {

    final Movie movie;
    final Cinema cinema;
    final Session session;

    public FullInfoSession(Movie movie, Cinema cinema, Session session) {
        this.movie = movie;
        this.cinema = cinema;
        this.session = session;
    }

    public Movie getMovie() {
        return movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FullInfoSession{");
        sb.append("movie=").append(movie.name);
        sb.append(", cinema=").append(cinema.name);
        sb.append(", session=").append(session.time).append(" - ").append(session.screen);
        sb.append('}');
        return sb.toString();
    }
}
