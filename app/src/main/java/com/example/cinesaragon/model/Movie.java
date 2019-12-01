package com.example.cinesaragon.model;

import java.util.List;

public class Movie {

    final String id;
    final String name;
    final String description;
    final List<String> images;
    final String trailer;

    private Movie(String id, String name, String description, List<String> images, String trailer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.images = images;
        this.trailer = trailer;
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
}
