package com.giantlink.introduction.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.giantlink.introduction.entities.Image;
import com.giantlink.introduction.entities.Movie;
import com.giantlink.introduction.models.mapper.ImageMapper;
import com.giantlink.introduction.models.requests.ImageRequest;
import com.giantlink.introduction.repositories.ImageRepository;
import com.giantlink.introduction.repositories.MovieRepository;
import com.giantlink.introduction.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	
	private final Path uploadPath = Paths.get("uploads");


	
	@Autowired
	ImageMapper imageMapper;
	
	@Autowired
	MovieRepository movieRepository;

	@Autowired
	ImageRepository imageRepository;

	
	public Image saveImage(Long movieId, MultipartFile file) throws IOException {
		Optional<Movie> movie = movieRepository.findById(movieId);
		if (movie.isPresent()) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Image img = Image.builder().type(file.getContentType()).fileName(fileName)
					.file(file.getBytes()).build();
			img.setMovie(movie.get());
			imageRepository.save(img);
			return img;
		}
		return null;
	}

	public Image getImage(String imageId) throws Exception {
		return imageRepository.findById(imageId).orElseThrow(() -> new Exception("Image not found !"));
	}
	
	
	
	

	/*
	@Autowired // l'injection des dependences
	MovieRepository movieRepository;

	@Autowired
	ImageMapper imageMapper;

	@Autowired
	ImageRepository imageRepository;

	@Override
	public Image add(ImageRequest image) {
		Optional<Movie> findMovie = movieRepository.findById(image.getMovieId());

		Image newImage = imageMapper.ImagerequestToImage(image);
		imageRepository.save(newImage);

		if (findMovie.isPresent()) {
			List<Image> mvImages = findMovie.get().getMvImages();
			mvImages.add(newImage);
			findMovie.get().setMvImages(mvImages);
			movieRepository.save(findMovie.get());
			
		//---------------------------------------------//
			newImage.setMovie(findMovie.get());
			imageRepository.save(newImage);
			
		}
		
		return newImage;
	}
*/
	
	@Override
	public List<Image> getAll() {

		return imageRepository.findAll();
	}

	/*
	@Override
	public Image get(Long id) {

		return imageRepository.getById(id);
	}

	@Override
	public void delete(Long id) {
		imageRepository.deleteById(id);

	}
*/
	/*@Override
	public Image add(ImageRequest image) {
		Optional<Movie> findMovie = movieRepository.findByStringId(image.getMovieId());

		Image newImage = imageMapper.ImagerequestToImage(image);
		imageRepository.save(newImage);

		if (findMovie.isPresent()) {
			List<Image> mvImages = findMovie.get().getMvImages();
			mvImages.add(newImage);
			findMovie.get().setMvImages(mvImages);
			movieRepository.save(findMovie.get());
			
			newImage.setMovie(findMovie.get());
			imageRepository.save(newImage);
			
		}
		
		return newImage;
	}
*/
	//---------------------------------------------//

	
	
	
	@Transactional
	@Override
	public Image SaveImageLocal(Long movieId, MultipartFile file,Boolean isCover) throws RuntimeException, IOException {
		Optional<Movie> movie = movieRepository.findById(movieId);
		if (movie.isPresent()) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Files.copy(file.getInputStream(), this.uploadPath.resolve(file.getOriginalFilename()));
			Image img = Image.builder().fileName(fileName).type(file.getContentType()).isCover(isCover).build();
			img.setMovie(movie.get());
			if(isCover) {
				imageRepository.updateAllCovers(movieId);
			}
			imageRepository.save(img);
			String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/movie/")
					.path("/downloadFile/").path(img.getId()).toUriString();
			img.setLink(imageUrl);
			imageRepository.save(img);
			return img;
		}
		return null;

	}
	

	@Override
	public void init() {
		boolean exists = Files.exists(uploadPath);
		if (!exists) {
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				throw new RuntimeException("could not create uploads folter");
			}
		}

		
	}
	
	
	@Override
	public Image editImage(Image image) {
		Optional<Image> findImage = imageRepository.findById(image.getId());
		if (findImage.isPresent()) {
			imageRepository.save(image);
			return image;
		}
		return null;
	}

	

	@Override
	public Path getUploadPath() {
		return this.uploadPath;
	}

	@Override
	public Image add(ImageRequest image) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
