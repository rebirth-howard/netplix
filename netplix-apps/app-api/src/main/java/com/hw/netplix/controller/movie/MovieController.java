package com.hw.netplix.controller.movie;

import com.hw.netplix.controller.NetplixApiResponse;
import com.hw.netplix.filter.JwtTokenProvider;
import com.hw.netplix.movie.DownloadMovieUseCase;
import com.hw.netplix.movie.FetchMovieUseCase;
import com.hw.netplix.movie.LikeMovieUseCase;
import com.hw.netplix.movie.reponse.MovieResponse;
import com.hw.netplix.movie.reponse.PageableMoviesResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final FetchMovieUseCase fetchMovieUseCase;
    private final DownloadMovieUseCase downloadMovieUseCase;
    private final JwtTokenProvider jwtTokenProvider;
    private final LikeMovieUseCase likeMovieUseCase;

    @GetMapping("/api/v1/movie/client/{page}")
    public NetplixApiResponse<PageableMoviesResponse> fetchMoviePageable(@PathVariable("page") int page) {
        PageableMoviesResponse pageableMoviesResponse = fetchMovieUseCase.fetchFromClient(page);
        return NetplixApiResponse.ok(pageableMoviesResponse);
    }

    @PostMapping("/api/v1/movie/search")
    public NetplixApiResponse<PageableMoviesResponse> search(@RequestParam int page) {
        PageableMoviesResponse pageableMoviesResponse = fetchMovieUseCase.fetchFromDb(page);
        return NetplixApiResponse.ok(pageableMoviesResponse);
    }

    @PostMapping("/api/v1/movie/{movieId}/download")
    @PreAuthorize("hasAnyRole('ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
    public NetplixApiResponse<String> download(@PathVariable String movieId) {
        String userId = jwtTokenProvider.getUserId();
        String role = jwtTokenProvider.getRole();
        return NetplixApiResponse.ok(downloadMovieUseCase.download(userId, role, movieId));
    }

    @PostMapping("/api/v1/movie/{movieId}/like")
    @PreAuthorize("hasAnyRole('ROLE_FREE', 'ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
    public NetplixApiResponse<String> likeMovie(@PathVariable String movieId) {
        String userId = jwtTokenProvider.getUserId();
        likeMovieUseCase.like(userId, movieId);
        return NetplixApiResponse.ok("");
    }

}
