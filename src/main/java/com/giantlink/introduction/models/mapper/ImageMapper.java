package com.giantlink.introduction.models.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.giantlink.introduction.entities.Image;
import com.giantlink.introduction.models.requests.ImageRequest;

@Mapper(componentModel = "spring",uses = MovieMapper.class,injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ImageMapper {

	ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

	@Mapping(target = "movie" ,ignore = true)
	Image ImagerequestToImage(ImageRequest imageReq);
}
