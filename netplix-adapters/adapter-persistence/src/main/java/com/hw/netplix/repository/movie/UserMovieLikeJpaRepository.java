package com.hw.netplix.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.hw.netplix.entity.movie.UserMovieLikeEntity;

public interface UserMovieLikeJpaRepository extends JpaRepository<UserMovieLikeEntity, String> {
	Optional<UserMovieLikeEntity> findByUserIdAndMovieId(String userId, String movieId);
}