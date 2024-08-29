package com.giantlink.introduction.models.requests;



import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.giantlink.introduction.controllers.ValidImage;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageRequest {
	
	@NotNull()
	@Size(min = 3,max = 255)
	private String link;
	
	@NotNull()
	@Size(min = 3,max = 20)
	@ValidImage
	private String type;
	
	/*
	@Valid
	private Long movieId;
	*/
	
	@Valid
	private String movieId;
	
	private byte[] file;
	private String fileName;
}
