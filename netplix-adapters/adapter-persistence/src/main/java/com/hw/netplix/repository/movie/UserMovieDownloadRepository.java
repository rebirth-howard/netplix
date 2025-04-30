package com.hw.netplix.repository.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hw.netplix.entity.movie.UserMovieDownloadEntity;
import com.hw.netplix.movie.DownloadMoviePort;
import com.hw.netplix.movie.UserMovieDownload;

@Repository
@RequiredArgsConstructor
public class UserMovieDownloadRepository implements DownloadMoviePort {

	private final UserMovieDownloadJpaRepository userMovieDownloadJpaRepository;

	@Override
	@Transactional
	public UserMovieDownload save(UserMovieDownload domain) {
		return userMovieDownloadJpaRepository.save(UserMovieDownloadEntity.toEntity(domain))
			.toDomain();
	}

	@Override
	@Transactional
	public long downloadCntToday(String userId) {
		return userMovieDownloadJpaRepository.countDownloadToday(userId);
	}
}
