package com.hw.netplix.movie;

import com.hw.netplix.movie.download.UserMovieDownloadRoleValidator;
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
public class MovieService implements FetchMovieUseCase, InsertMovieUseCase, DownloadMovieUseCase {
    private final TmdbMoviePort tmdbMoviePort;
    private final PersistenceMoviePort persistenceMoviePort;
    private final DownloadMoviePort downloadMoviePort;
    private final List<UserMovieDownloadRoleValidator> validators;

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

    @Override
    public String download(String userId, String role, String movieId) {
        long cnt = downloadMoviePort.downloadCntToday(userId);
        // 전략패턴
        boolean validate = validators.stream()
            .filter(validator -> validator.isTarget(role))
            .findAny()
            .orElseThrow()
            .validate(cnt);

        if (!validate) {
            throw new RuntimeException("더 이상 다운로드를 할 수 없습니다.");
        }

        NetplixMovie by = persistenceMoviePort.findBy(movieId);

        downloadMoviePort.save(UserMovieDownload.newDownload(userId, movieId));

        return by.getMovieName();
    }
}
