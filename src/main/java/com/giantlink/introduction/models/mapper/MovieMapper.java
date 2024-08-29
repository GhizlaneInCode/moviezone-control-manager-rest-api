package com.giantlink.introduction.models.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.giantlink.introduction.entities.Movie;
import com.giantlink.introduction.models.requests.MovieRequest;
import com.giantlink.introduction.models.responses.MovieResponse;

@Mapper(componentModel = "spring", uses = DirectorMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieMapper {

	MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

	@Mapping(target = "director")
	Movie requestToMovie(MovieRequest movieReq);

	
	MovieResponse movieToResponse(Movie movie);
/*
	@Mapping(target = "author", ignore = true)
	Movie requestToEntity1(MovieRequest requestMovie);
	*/
}
