package com.hw.netplix.tmdb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hw.netplix.client.TmdbHttpClient;
import com.hw.netplix.movie.TmdbMovie;
import com.hw.netplix.movie.TmdbMoviePort;
import com.hw.netplix.movie.TmdbPageableMovies;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TmdbMovieListHttpClient implements TmdbMoviePort {

    @Value("${tmdb.api.movie-lists.not-playing}")
    private String nowPlayingUrl;

    private final TmdbHttpClient tmdbHttpClient;

    @Override
    public TmdbPageableMovies fetchPageable(int page) {
        String url = nowPlayingUrl + "?language=ko-KR&page=" + page;
        String request = tmdbHttpClient.request(url, HttpMethod.GET, CollectionUtils.toMultiValueMap(Map.of()), Map.of());

        TmdbMovieNowPlayingResponse response;
        try {
            response = new ObjectMapper().readValue(request, TmdbMovieNowPlayingResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new TmdbPageableMovies(
                response.getResults().stream().map(movie -> new TmdbMovie(movie.getTitle(), movie.getAdult(), movie.getGenreIds(), movie.getOverview(), movie.getReleaseDate())).collect(Collectors.toUnmodifiableList()),
                page,
                Integer.parseInt(response.getTotalPages()) - page != 0
        );
    }

}
