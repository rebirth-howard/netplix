package com.hw.netplix.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hw.netplix.entity.movie.UserMovieDownloadEntity;

public interface UserMovieDownloadJpaRepository extends JpaRepository<UserMovieDownloadEntity, String>, UserMovieDownloadCustomRepository {

}