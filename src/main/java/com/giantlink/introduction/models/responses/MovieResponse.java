package com.giantlink.introduction.models.responses;


import com.giantlink.introduction.models.requests.DirectorRequest;
import java.util.List;

import lombok.Data;

@Data
public class MovieResponse {

	//private Long id;

	private String title;
	private String description;
	private DirectorRequest director;
	private List<ImageResponse> mvImages;
	
}


