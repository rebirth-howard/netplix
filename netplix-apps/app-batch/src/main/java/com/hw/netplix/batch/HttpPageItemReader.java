package com.hw.netplix.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;

import com.hw.netplix.movie.FetchMovieUseCase;
import com.hw.netplix.movie.reponse.MovieResponse;
import com.hw.netplix.movie.reponse.PageableMoviesResponse;

public class HttpPageItemReader extends AbstractItemCountingItemStreamItemReader<MovieResponse> {

	private int page;
	private final List<MovieResponse> contents = new ArrayList<>();
	private final FetchMovieUseCase fetchMovieUseCase;

	public HttpPageItemReader( int page, FetchMovieUseCase fetchMovieUseCase ) {
		this.page = page;
		this.fetchMovieUseCase = fetchMovieUseCase;
	}

	@Override
	protected MovieResponse doRead() throws Exception {
		if (this.contents.isEmpty()) {
			readRow();
		}

		int size = this.contents.size();
		int index = size - 1;

		if (index < 0) {
			return null;
		}

		return contents.remove(contents.size() - 1);
	}

	@Override
	protected void doOpen() throws Exception {
		setName(HttpPageItemReader.class.getSimpleName());
	}

	@Override
	protected void doClose() throws Exception {
		//
	}

	private void readRow() {
		PageableMoviesResponse pageableMoviesResponse = fetchMovieUseCase.fetchFromClient(page);
		contents.addAll(pageableMoviesResponse.getMovieResponses());
		page++;
	}
}
