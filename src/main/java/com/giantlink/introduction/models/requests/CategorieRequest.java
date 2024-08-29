package com.giantlink.introduction.models.requests;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategorieRequest {
	
	@NotNull()
	@Size(min = 3, max = 20)
	private String name;
	
	@NotNull()
	@Size(min = 3, max = 255)
	private String description;
}
