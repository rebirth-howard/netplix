package com.hw.netplix.controller.movie;

import com.hw.netplix.controller.NetplixApiResponse;
import com.hw.netplix.movie.FetchMovieUseCase;
import com.hw.netplix.movie.reponse.PageableMoviesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final FetchMovieUseCase fetchMovieUseCase;

    @GetMapping("/api/v1/movie/client/{page}")
    public NetplixApiResponse<PageableMoviesResponse> fetchMoviePageable(@PathVariable("page") int page) {
        PageableMoviesResponse pageableMoviesResponse = fetchMovieUseCase.fetchFromClient(page);
        return NetplixApiResponse.ok(pageableMoviesResponse);
    }
}
