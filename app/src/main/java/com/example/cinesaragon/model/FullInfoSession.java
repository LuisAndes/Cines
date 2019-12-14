package com.example.cinesaragon.model;

public class FullInfoSession {

    private final Movie movie;
    private final Cinema cinema;
    private final Session session;

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
        sb.append("movie=").append(movie.getName());
        sb.append(", cinema=").append(cinema.getName());
        sb.append(", session=").append(session.getTime());
        sb.append(", screen=").append(session.getScreen());
        sb.append('}');
        return sb.toString();
    }
}
