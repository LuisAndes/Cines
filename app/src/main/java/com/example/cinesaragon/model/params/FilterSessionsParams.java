package com.example.cinesaragon.model.params;

public class FilterSessionsParams {
    private final boolean byDuration;
    private final int duration;
    private final boolean byHour;
    private final int hour;
    private final boolean byMovie;
    private final String movie;
    private final boolean byCinema;
    private final String cinema;
    private final boolean byGenre;
    private final String genre;

    private FilterSessionsParams(boolean byDuration, int duration,
                                 boolean byHour, int hour,
                                 boolean byMovie, String movie,
                                 boolean byCinema, String cinema,
                                 boolean byGenre, String genre) {
        this.byDuration = byDuration;
        this.duration = duration;
        this.byHour = byHour;
        this.hour = hour;
        this.byMovie = byMovie;
        this.movie = movie;
        this.byCinema = byCinema;
        this.cinema = cinema;
        this.byGenre = byGenre;
        this.genre = genre;
    }

    public boolean isByDuration() {
        return byDuration;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isByHour() {
        return byHour;
    }

    public int getHour() {
        return hour;
    }

    public boolean isByMovie() {
        return byMovie;
    }

    public String getMovie() {
        return movie.toLowerCase();
    }

    public boolean isByCinema() {
        return byCinema;
    }

    public String getCinema() {
        return cinema.toLowerCase();
    }

    public boolean isByGenre() {
        return byGenre;
    }

    public String getGenre() {
        return genre.toLowerCase();
    }

    public static class Builder {
        private boolean byDuration = false;
        private int duration = 0;
        private boolean byHour = false;
        private int hour = 0;
        private boolean byMovie = false;
        private String movie = "";
        private boolean byCinema = false;
        private String cinema = "";
        private boolean byGenre = false;
        private String genre = "";

        public Builder() {
        }

        public Builder filterByDuration(int duration) {
            this.byDuration = true;
            this.duration = duration;
            return this;
        }

        public Builder filterByHour(int hour) {
            this.byHour = true;
            this.hour = hour;
            return this;
        }

        public Builder filterByMovie(String movie) {
            this.byMovie = true;
            this.movie = movie;
            return this;
        }

        public Builder filterByCinema(String cinema) {
            this.byCinema = true;
            this.cinema = cinema;
            return this;
        }

        public Builder filterByGenre(String genre) {
            this.byGenre = true;
            this.genre = genre;
            return this;
        }

        public FilterSessionsParams build() {
            return new FilterSessionsParams(byDuration, duration,
                    byHour, hour,
                    byMovie, movie,
                    byCinema, cinema,
                    byGenre, genre);
        }


    }


}
