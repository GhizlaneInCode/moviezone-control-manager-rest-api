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

import com.giantlink.introduction.entities.Actor;
import com.giantlink.introduction.models.requests.ActorRequest;
import com.giantlink.introduction.services.ActorService;

@RestController
@RequestMapping("/api/actor")
public class ActorController {
	
	@Autowired
	ActorService actorservice;
	
	@PostMapping("/add")
	public ResponseEntity<Actor> add(@RequestBody ActorRequest actor){		
		return new ResponseEntity<Actor>(actorservice.add(actor), HttpStatus.CREATED);
	}
	
	
	@GetMapping()
	public ResponseEntity<List<Actor>> get(){
		return new ResponseEntity<List<Actor>>(actorservice.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Actor> get(@PathVariable Long id){
		return new ResponseEntity<Actor>(actorservice.get(id), HttpStatus.OK);
	}
	

}
