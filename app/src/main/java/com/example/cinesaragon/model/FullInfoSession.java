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
}
