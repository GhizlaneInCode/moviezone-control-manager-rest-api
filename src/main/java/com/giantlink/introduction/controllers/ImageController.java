package com.giantlink.introduction.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.giantlink.introduction.entities.Image;
import com.giantlink.introduction.models.requests.ImageRequest;
import com.giantlink.introduction.models.responses.ImageResponse;
import com.giantlink.introduction.services.ImageService;
import com.giantlink.introduction.services.MovieService;

@Validated
@RestController
@RequestMapping("/api/movie")
@CrossOrigin(origins = "http://localhost:4200") // allow origin
public class ImageController {
	
	@Autowired
	ImageService imageService;
	
	
	@GetMapping("/images")
	public ResponseEntity<List<Image>> get(){
		return new ResponseEntity<List<Image>>(imageService.getAll(),HttpStatus.OK);
	}
	

	/*
	@PostMapping("/add")
	public ResponseEntity<Image> add(@RequestBody ImageRequest image){		
		return new ResponseEntity<Image>(imageService.add(image), HttpStatus.CREATED);
	}
	
	
	
	
	/*@GetMapping("/{id}")
	public ResponseEntity<Image> get(@PathVariable Long id){
		return new ResponseEntity<Image>(imageService.get(id), HttpStatus.OK);
	}*/
	
	
	
	
	@PostMapping("/upload/{movieId}")
	public ResponseEntity<ImageResponse> add(@PathVariable("movieId") Long movieId, @RequestParam("file") MultipartFile file ){
		try {
			Image saveImage = imageService.saveImage(movieId, file);
			String imageUrl=ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/movie/").path("/download/").path(saveImage.getId()).toUriString();
			saveImage.setLink(imageUrl);
			imageService.editImage(saveImage);
			ImageResponse response = ImageResponse.builder().id(saveImage.getId()).link(imageUrl).imageName(saveImage.getFileName()).imageSize(file.getSize()).type(file.getContentType()).build();
			return new ResponseEntity<ImageResponse>(response, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	

	
	
	@GetMapping("/download/{imageId}")
	public ResponseEntity<Resource> get(@PathVariable("imageId") String imageId) {
		try {
			Image image = imageService.getImage(imageId);
			
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(image.getType()))
					.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+image.getFileName())
					.body(new ByteArrayResource(image.getFile()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@PostMapping("/uploadFile/{movieId}")
	public ResponseEntity<ImageResponse> addFile(@PathVariable("movieId") Long movieId,
			@RequestParam("file") MultipartFile file,@RequestParam("is_cover") Boolean isCover) throws IOException {
		
			Image saveImage = imageService.SaveImageLocal(movieId, file,isCover);

			ImageResponse response = ImageResponse.builder().id(saveImage.getId())
					.link(saveImage.getLink()).imageName(saveImage.getFileName()).imageSize(file.getSize())
					.type(file.getContentType()).build();
			return new ResponseEntity<ImageResponse>(response, HttpStatus.CREATED);
	}
	
	
	

	
	
	@GetMapping("/downloadFile/{imageId}")
	public ResponseEntity<Resource> getFile(@PathVariable("imageId") String imageId) {
		try {
			Image image = imageService.getImage(imageId);
			Path file = imageService.getUploadPath().resolve(image.getFileName());
			Resource resource = new UrlResource(file.toUri());

			return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName())
					.body(resource);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



	
}
