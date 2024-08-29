package com.giantlink.introduction.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giantlink.introduction.entities.Actor;
import com.giantlink.introduction.entities.Movie;


public interface ActorRepository extends JpaRepository<Actor, Long> {

	Optional<Actor> findActorByfirstName(String firstName);
}
