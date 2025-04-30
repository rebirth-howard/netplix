package com.hw.netplix.repository.movie;

public interface UserMovieDownloadCustomRepository {
	long countDownloadToday(String userId);
}