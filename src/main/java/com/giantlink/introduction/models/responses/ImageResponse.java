package com.giantlink.introduction.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageResponse {

	private String id;
	private String type;
	private String imageName;
	private String link;
	private Long imageSize;

}
