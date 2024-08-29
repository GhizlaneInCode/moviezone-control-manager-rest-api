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

import com.giantlink.introduction.entities.Categorie;
import com.giantlink.introduction.models.requests.CategorieRequest;
import com.giantlink.introduction.services.CategoryService;



@RestController
@RequestMapping("/api/category")
public class CategorieController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<Categorie> add(@RequestBody CategorieRequest category){		
		return new ResponseEntity<Categorie>(categoryService.add(category), HttpStatus.CREATED);
	}
	
	
	@GetMapping()
	public ResponseEntity<List<Categorie>> get(){
		return new ResponseEntity<List<Categorie>>(categoryService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categorie> get(@PathVariable Long id){
		return new ResponseEntity<Categorie>(categoryService.get(id), HttpStatus.OK);
	}

}
