package com.giantlink.introduction.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giantlink.introduction.entities.Image;
import com.giantlink.introduction.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	Optional<Movie> findMovieByTitle(String title);
	
	//Optional<Movie> findByStringId(String id);
	
	Page<Movie> findByTitleContainingOrderByReleaseDateDesc(String title, Pageable pageable);

}
