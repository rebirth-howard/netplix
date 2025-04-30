package com.hw.netplix.repository.movie;

import static com.hw.netplix.entity.movie.QUserMovieDownloadEntity.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserMovieDownloadCustomRepositoryImpl implements UserMovieDownloadCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public long countDownloadToday(String userId) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime start = now.truncatedTo(ChronoUnit.DAYS);
		LocalDateTime end = now.plusDays(1).truncatedTo(ChronoUnit.DAYS);

		return jpaQueryFactory.selectFrom(userMovieDownloadEntity)
			.where(userMovieDownloadEntity.userId.eq(userId)
				.and(userMovieDownloadEntity.createdAt.goe(start))
				.and(userMovieDownloadEntity.createdAt.lt(end)))
			.fetch()
			.size();
	}
}