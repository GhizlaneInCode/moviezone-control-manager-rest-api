package com.giantlink.introduction.models.requests;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.giantlink.introduction.entities.Actor;
import com.giantlink.introduction.entities.Categorie;
import com.giantlink.introduction.entities.Image;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieRequest {

	@NotNull()
	@Size(min = 3,max = 20)
	private String title;

	@NotNull()
	@Size(min = 3,max = 255)
	private String description;

	@NotNull()
	@Valid
	private DirectorRequest director;

	
	@NotNull()
	private Date releaseDate;
	
	@NotNull()
	private int duration;
	
	@NotNull()
	@Size(min = 3,max = 20)
	private String originalLanguage;
	
	
	private List<Image> mvImages;
	private List<Categorie> mvCategories;
	private List<Actor>  mvActors;

}
