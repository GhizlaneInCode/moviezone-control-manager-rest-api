package com.giantlink.introduction.models.responses;

import java.util.Date;

import com.giantlink.introduction.entities.Director;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder
public class MovieResponseCover {
	
	private Long id;
	private String title;
	private String description;
	private Date releaseDate;
	private Director director;
	private ImageResponse cover;


}
