package com.giantlink.introduction.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.giantlink.introduction.entities.Actor;
import com.giantlink.introduction.models.requests.ActorRequest;

@Mapper(componentModel = "spring")
public interface ActorMapper {

	ActorMapper INSTANCE = Mappers.getMapper(ActorMapper.class);

	
	Actor requestToActor(ActorRequest actorReq);
}
