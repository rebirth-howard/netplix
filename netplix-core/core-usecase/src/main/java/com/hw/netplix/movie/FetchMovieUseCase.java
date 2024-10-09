package com.hw.netplix.movie;

import com.hw.netplix.movie.reponse.PageableMoviesResponse;

public interface FetchMovieUseCase {
    PageableMoviesResponse fetchFromClient(int page);
}
