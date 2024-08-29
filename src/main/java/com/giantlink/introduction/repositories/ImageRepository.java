package com.giantlink.introduction.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.giantlink.introduction.entities.Image;
import com.giantlink.introduction.entities.Movie;

@Repository
public interface ImageRepository extends JpaRepository<Image, String>{
	
	Optional<Image> findImageByLink(String link);
	
	
	@Modifying
	@Query("UPDATE Image mi SET mi.isCover=0 WHERE mi.movie.id= :movie_id")
	Integer updateAllCovers(@Param("movie_id") Long movieId);

	

}
