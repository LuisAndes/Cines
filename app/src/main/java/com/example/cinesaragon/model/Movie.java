package com.example.cinesaragon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie {

    final String id;
    final String name;
    final String description;
    final List<String> images;
    final String trailer;
    final int duration;

    private Movie() {
        this.id = "";
        this.name = "";
        this.description = "";
        this.images = new ArrayList<>();
        this.trailer = "";
        this.duration = 0;
    }

    private Movie(String id, String name, String description, List<String> images, String trailer, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.images = images;
        this.trailer = trailer;
        this.duration = duration;
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
