package com.hw.netplix.repository.movie;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hw.netplix.entity.movie.MovieEntity;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, String>, MovieCustomRepository {
	Optional<MovieEntity> findByMovieName(String movieName);
}
