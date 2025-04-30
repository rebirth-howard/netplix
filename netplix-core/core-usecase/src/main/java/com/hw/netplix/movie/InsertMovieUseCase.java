package com.hw.netplix.movie;

import java.util.List;

import com.hw.netplix.movie.reponse.MovieResponse;

public interface InsertMovieUseCase {
	void insert(List<MovieResponse> movies);
}
