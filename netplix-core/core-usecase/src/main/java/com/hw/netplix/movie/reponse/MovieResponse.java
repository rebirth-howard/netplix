package com.hw.netplix.movie.reponse;

import lombok.Getter;

import java.util.List;

@Getter
public class MovieResponse {

    private final String movieName;
    private final Boolean isAdult;
    private final List<String> genre;
    private final String overview;
    private final String releasedAt;

    public MovieResponse(String movieName, Boolean isAdult, List<String> genre, String overview, String releasedAt) {
        this.movieName = movieName;
        this.isAdult = isAdult;
        this.genre = genre;
        this.overview = overview;
        this.releasedAt = releasedAt;
    }
}
