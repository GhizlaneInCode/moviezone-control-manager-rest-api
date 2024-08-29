package com.giantlink.introduction.models.requests;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActorRequest {

	@NotNull()
	@Size(min = 3, max = 20)
	private String firstName;
	
	@NotNull()
	@Size(min = 3, max = 20)
	private String lastName;
	
	@NotNull()
	private int age;
	
	@NotNull()
	@Size(min = 3, max = 255)
	private String photolink;
	
	//@Valid
	//private Long movieId;
}
