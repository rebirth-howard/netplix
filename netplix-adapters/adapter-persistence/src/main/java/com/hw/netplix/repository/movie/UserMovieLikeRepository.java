package com.hw.netplix.repository.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.hw.netplix.entity.movie.UserMovieLikeEntity;
import com.hw.netplix.movie.LikeMoviePort;
import com.hw.netplix.movie.UserMovieLike;

@Repository
@RequiredArgsConstructor
public class UserMovieLikeRepository implements LikeMoviePort {

	private final UserMovieLikeJpaRepository userMovieLikeJpaRepository;

	@Override
	@Transactional
	public UserMovieLike save(UserMovieLike domain) {
		UserMovieLikeEntity entity = UserMovieLikeEntity.toEntity(domain);
		return userMovieLikeJpaRepository.save(entity).toDomain();
	}

	@Override
	@Transactional
	public Optional<UserMovieLike> findByUserIdAndMovieId(String userId, String movieId) {
		return userMovieLikeJpaRepository.findByUserIdAndMovieId(userId, movieId)
			.map(UserMovieLikeEntity::toDomain);
	}
}