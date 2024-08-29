package com.giantlink.introduction.models.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

import com.giantlink.introduction.entities.Director;

import com.giantlink.introduction.models.requests.DirectorRequest;


@Mapper(componentModel = "spring")
public interface DirectorMapper {

	DirectorMapper INSTANCE = Mappers.getMapper(DirectorMapper.class);
	Director requestToDirector(DirectorRequest directorReq);

	/*
	AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

	AuthorResponse entityToResponse(Author author);

	Author requestToEntity(AuthorRequest authorRequest);
	*/
}
