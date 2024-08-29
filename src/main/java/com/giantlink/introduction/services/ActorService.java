package com.giantlink.introduction.services;

import java.util.List;

import com.giantlink.introduction.entities.Actor;
import com.giantlink.introduction.models.requests.ActorRequest;



public interface ActorService {

	Actor add(ActorRequest actor);

	List<Actor> getAll();

	Actor get(Long id);

	void delete(Long id);
}
