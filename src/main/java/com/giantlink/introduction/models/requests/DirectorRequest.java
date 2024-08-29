package com.giantlink.introduction.models.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DirectorRequest {

	@NotNull()
	@Size(min = 3, max = 20)
	private String name;

	@NotNull
	@Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
	private String phone;
}
