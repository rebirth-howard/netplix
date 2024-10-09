package com.hw.netplix.movie;

import lombok.Getter;

import java.util.List;

@Getter
public class TmdbMovie {

    private final String movieName;
    private final Boolean isAdult;
    private final List<String> genre;
    private final String overview;
    private final String releasedAt;

    public TmdbMovie(String movieName, Boolean isAdult, List<String> genre, String overview, String releasedAt) {
        this.movieName = movieName;
        this.isAdult = isAdult;
        this.genre = genre;
        this.overview = overview;
        this.releasedAt = releasedAt;
    }
}
