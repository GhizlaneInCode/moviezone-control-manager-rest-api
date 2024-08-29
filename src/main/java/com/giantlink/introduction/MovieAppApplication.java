package com.giantlink.introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giantlink.introduction.services.ImageService;

@SpringBootApplication
public class MovieAppApplication implements CommandLineRunner {

	@Autowired
	private ImageService imageService;

	public static void main(String[] args) {
		SpringApplication.run(MovieAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		imageService.init();
		
	}
	
	
	

	
	
	
	

	


}
