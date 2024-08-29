package com.giantlink.introduction.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giantlink.introduction.entities.Movie;
import com.giantlink.introduction.models.requests.MovieRequest;
import com.giantlink.introduction.models.responses.MovieResponseCover;
import com.giantlink.introduction.services.MovieService;


@RestController
@RequestMapping("/api/movie")
@CrossOrigin(origins = "http://localhost:4200") // allow origin 
public class MovieController {

	@Autowired
	MovieService movieService;
	@Autowired
	ResourceBundleMessageSource messageSource;

	/*@PostMapping("/add")
	public ResponseEntity<Movie> add(@RequestBody @Valid MovieRequest movie) {
		Movie add = movieService.add(movie);
		return new ResponseEntity<Movie>(add, HttpStatus.CREATED);
	}*/
	
	
	
	@PostMapping("/add")
	
	public Movie add(@RequestBody @Valid MovieRequest movie) {
		Movie add = movieService.add(movie);
		
		// Locale.setDefault(Locale.US);
		 //return messageSource.getMessage("movie.added", new Object[] {add.getTitle()} , locale) ;
		 return add;
		//return new ResponseEntity<Movie>(add, HttpStatus.CREATED);
	}

	
	@GetMapping
		
		public ResponseEntity<List<Movie>> get() {
		
			

		return new ResponseEntity<List<Movie>>(movieService.getAll(), HttpStatus.OK);
	}
	
	

	@DeleteMapping("/{id}") // locealhost:8080/api/movie/1
	
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		movieService.delete(id);
		return new ResponseEntity<String>("deleted !", HttpStatus.NO_CONTENT);
	}

	
	
	

	/*
	@GetMapping("/message")
	public String message(@RequestHeader(name = "Accept-Language", required = false) final Locale locale) {
		Locale lo = (locale == null) ? Locale.ENGLISH : locale;
		String title = "MATRIX";
		return messageSource.getMessage("movie.added", new Object[] {title}, lo);
	}
	*/
	
	@GetMapping("/message")
	
	public String message(@RequestHeader(name = "Accept-Language", required = false) final Locale locale) {
		//Locale lo = (locale == null) ? Locale.ENGLISH : locale;

		    Locale.setDefault(Locale.US);
		    
		    //System.out.println(locale);
		    
		String title = "MATRIX";
		return messageSource.getMessage("movie.added", new Object[] {title}, locale);
	}
	
	/*
	@GetMapping("/images/{id}")
	public ResponseEntity<List<Image>> getimg(@PathVariable Long id) {
	return new ResponseEntity<List<Image>>(movieService.get(id).getMvImages(), HttpStatus.OK);
	}
	
	*/
	@GetMapping("/home")
	
	public ResponseEntity<List<MovieResponseCover>> getWithCover() {
		return new ResponseEntity<List<MovieResponseCover>>(movieService.getAllWithCover(), HttpStatus.OK);
	}
	
	@GetMapping("getWithCover/{id}")
	public ResponseEntity<MovieResponseCover> getWithCover(@PathVariable Long id) {
		return new ResponseEntity<MovieResponseCover>(movieService.getMovieWithCover(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movie> get(@PathVariable Long id) {
		return new ResponseEntity<Movie>(movieService.get(id), HttpStatus.OK);
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody @Valid MovieRequest movie) throws IOException {
		
		Movie movieUpdated = movieService.update(id, movie);
		
		return new ResponseEntity<Movie>(movieUpdated, HttpStatus.CREATED);
}
	
	@GetMapping("/allOld") // select * from movies limit size,page
	public ResponseEntity<Page<Movie>> getPaginationsOld(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return new ResponseEntity<Page<Movie>>(movieService.getAllPaginations(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/all") // select * from movies limit size,page
	public ResponseEntity<Map<String, Object>> getPaginations(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size,@RequestParam(defaultValue = "",name="title")String title) {
		Pageable pageable = PageRequest.of(page, size);
		return new ResponseEntity<Map<String, Object>>(movieService.getAllWithCoverPaginations(title,pageable),HttpStatus.OK);
	}


	@DeleteMapping("/delete/{id}/{catId}") 
	public ResponseEntity<String> deleteCategorie(@PathVariable Long id , @PathVariable Long catId) {
		movieService.deleteCategorie(id, catId);
		return new ResponseEntity<String>("deleted !", HttpStatus.NO_CONTENT);
	}
	
}

