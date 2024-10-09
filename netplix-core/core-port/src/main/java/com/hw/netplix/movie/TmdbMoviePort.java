package com.hw.netplix.movie;

public interface TmdbMoviePort {
    TmdbPageableMovies fetchPageable(int page);
}
