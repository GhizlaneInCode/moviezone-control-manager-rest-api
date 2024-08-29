package com.giantlink.introduction.models.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.giantlink.introduction.entities.Movie;
import com.giantlink.introduction.models.responses.MovieResponseCover;

@Mapper(componentModel = "spring")
public interface MovieCoverMapper {

	MovieCoverMapper INSTANCE = Mappers.getMapper(MovieCoverMapper.class);

	MovieResponseCover movieToResponseCover(Movie movie);
}
