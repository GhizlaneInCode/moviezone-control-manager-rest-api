package com.giantlink.introduction.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.giantlink.introduction.entities.Movie;
import com.giantlink.introduction.models.requests.MovieRequest;
import com.giantlink.introduction.models.responses.MovieResponse;
import com.giantlink.introduction.models.responses.MovieResponseCover;

public interface MovieService {

	Movie add(MovieRequest movie);

	List<Movie> getAll();

	Movie get(Long id);
	
	MovieResponseCover getMovieWithCover(Long id);

	void delete(Long id);
	
	List<MovieResponseCover> getAllWithCover();
	
	Movie update(Long id,MovieRequest movie) throws IOException;

	
	Page<Movie> getAllPaginations(Pageable pageable);
	
	Map<String, Object> getAllWithCoverPaginations(String title,Pageable pageable);

	void deleteCategorie(Long movieId, Long catId);
}
