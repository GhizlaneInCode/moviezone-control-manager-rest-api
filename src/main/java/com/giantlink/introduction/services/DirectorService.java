package com.giantlink.introduction.services;

import java.util.List;

import com.giantlink.introduction.entities.Director;
import com.giantlink.introduction.models.requests.DirectorRequest;

public interface DirectorService {
	
	Director add(DirectorRequest director);

	List<Director> getAll();

	Director get(Long id);

	void delete(Long id);
}
