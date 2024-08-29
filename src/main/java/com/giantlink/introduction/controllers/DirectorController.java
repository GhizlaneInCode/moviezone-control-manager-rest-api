package com.giantlink.introduction.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giantlink.introduction.entities.Director;
import com.giantlink.introduction.models.requests.DirectorRequest;
import com.giantlink.introduction.services.DirectorService;

@RestController
@RequestMapping("/api/director")
public class DirectorController {

	@Autowired
	DirectorService directorService;
	
	@PostMapping("/add")
	public ResponseEntity<Director> add(@RequestBody DirectorRequest director){		
		return new ResponseEntity<Director>(directorService.add(director), HttpStatus.CREATED);
	}
	
	
	@GetMapping()
	public ResponseEntity<List<Director>> get(){
		return new ResponseEntity<List<Director>>(directorService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Director> get(@PathVariable Long id){
		return new ResponseEntity<Director>(directorService.get(id), HttpStatus.OK);
	}
	
	
}
