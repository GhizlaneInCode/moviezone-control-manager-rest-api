package com.giantlink.introduction.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantlink.introduction.entities.Actor;
import com.giantlink.introduction.entities.Image;
import com.giantlink.introduction.entities.Movie;
import com.giantlink.introduction.models.mapper.ActorMapper;
import com.giantlink.introduction.models.requests.ActorRequest;
import com.giantlink.introduction.repositories.ActorRepository;
import com.giantlink.introduction.repositories.DirectorRepository;
import com.giantlink.introduction.repositories.MovieRepository;
import com.giantlink.introduction.services.ActorService;

@Service
public class ActorServiceImpl implements ActorService {
	
	@Autowired
	ActorRepository actorRepository;

	@Autowired
	ActorMapper actorMapper;
	
	@Autowired // l'injection des dependences
	MovieRepository movieRepository;
	
	
	
	@Override
	public Actor add(ActorRequest actor) {
		//Optional<Movie> findMovie = movieRepository.findById(actor.getMovieId());
		
		Actor newActor = actorMapper.requestToActor(actor);
		actorRepository.save(newActor);
		
		/*if (findMovie.isPresent()) {
			List<Actor> mvActors = findMovie.get().getMvActors();
			mvActors.add(newActor);
			findMovie.get().setMvActors(mvActors);
			movieRepository.save(findMovie.get());
			
		//---------------------------------------------//
			List<Movie> movies = newActor.getActorMovies();
			movies.add(findMovie.get());
			newActor.setActorMovies(movies);
			
			actorRepository.save(newActor);
			
		}*/
		
		return newActor;
	}
	

	@Override
	public List<Actor> getAll() {
		
		return actorRepository.findAll();
	}

	@Override
	public Actor get(Long id) {
		
		return actorRepository.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		actorRepository.deleteById(id);
		
	}

	

	
	

}
