package com.hw.netplix.movie;

import com.hw.netplix.movie.reponse.MovieResponse;
import com.hw.netplix.movie.reponse.PageableMoviesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService implements FetchMovieUseCase, InsertMovieUseCase {
    private final TmdbMoviePort tmdbMoviePort;
    private final PersistenceMoviePort persistenceMoviePort;

    @Override
    public PageableMoviesResponse fetchFromClient(int page) {
        TmdbPageableMovies tmdbPageableMovies = tmdbMoviePort.fetchPageable(page);
        return new PageableMoviesResponse(
                tmdbPageableMovies.getTmdbMovies().stream()
                        .map(movie -> new MovieResponse(
                                movie.getMovieName(),
                                movie.getIsAdult(),
                                movie.getGenre(),
                                movie.getOverview(),
                                movie.getReleasedAt()
                        ))
                        .toList(),
                tmdbPageableMovies.getPage(),
                tmdbPageableMovies.isHasNext()
        );
    }

    @Override
    public PageableMoviesResponse fetchFromDb(int page) {
        List<NetplixMovie> netplixMovies = persistenceMoviePort.fetchBy(page, 10);
        return new PageableMoviesResponse(
            netplixMovies.stream().map(it -> new MovieResponse(it.getMovieName(), it.getIsAdult(), List.of(), it.getOverview(), it.getReleasedAt())).toList(),
            page,
            true
        );
    }

    @Override
    public void insert(List<MovieResponse> items) {
        items.forEach(it -> {
                NetplixMovie netplixMovie = NetplixMovie.builder()
                    .movieName(it.getMovieName())
                    .isAdult(it.getIsAdult())
                    .overview(it.getOverview())
                    .releasedAt(it.getReleasedAt())
                    .genre("")
                    .build();

                persistenceMoviePort.insert(netplixMovie);
            }
        );
    }
}
