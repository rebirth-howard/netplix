package com.hw.netplix.movie.download;

public interface UserMovieDownloadRoleValidator {
	boolean validate(long count);
	boolean isTarget(String role);
}