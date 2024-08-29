package com.giantlink.introduction.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantlink.introduction.entities.Director;
import com.giantlink.introduction.entities.Movie;
import com.giantlink.introduction.models.mapper.DirectorMapper;
import com.giantlink.introduction.models.mapper.MovieMapper;
import com.giantlink.introduction.models.requests.DirectorRequest;
import com.giantlink.introduction.repositories.DirectorRepository;
import com.giantlink.introduction.services.DirectorService;

@Service
public class DirectorServiceImpl implements DirectorService {

	//@Autowired
	//DirectorMapper directorMapper;
	
	
	@Autowired
	DirectorRepository directorRepository;
	
	@Autowired
	DirectorMapper directorMapper;


	@Override
	public Director add(DirectorRequest director) {
		
		Director newDirector = directorMapper.requestToDirector(director);
		directorRepository.save(newDirector);
		return newDirector;
	}
	

	@Override
	public List<Director> getAll() {
		return directorRepository.findAll();
	}

	@Override
	public Director get(Long id) {
		return directorRepository.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		directorRepository.deleteById(id);
	}

	

}
