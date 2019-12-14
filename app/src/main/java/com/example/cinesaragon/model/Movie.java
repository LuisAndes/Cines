package com.example.cinesaragon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie {

    private final String name;
    private final String description;
    private final List<String> images;
    private final String trailer;
    private final int duration;
    private final List<String> genres;

    private Movie() {
        this.name = "";
        this.description = "";
        this.images = new ArrayList<>();
        this.trailer = "";
        this.duration = 0;
        this.genres = new ArrayList<>();
    }

    private Movie(String name, String description, List<String> images, String trailer, int duration, List<String> genres) {
        this.name = name;
        this.description = description;
        this.images = images;
        this.trailer = trailer;
        this.duration = duration;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }

    public String getTrailer() {
        return trailer;
    }

    public int getDuration() {
        return duration;
    }

    public List<String> getGenres() {
        return genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(name, movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Movie{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
