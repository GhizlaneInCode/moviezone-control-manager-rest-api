package com.giantlink.introduction.services.impl;

import java.io.IOException;
import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.giantlink.introduction.entities.Actor;
import com.giantlink.introduction.entities.Categorie;
import com.giantlink.introduction.entities.Director;
import com.giantlink.introduction.entities.Image;
import com.giantlink.introduction.entities.Movie;
import com.giantlink.introduction.models.mapper.CategoryMapper;
import com.giantlink.introduction.models.mapper.DirectorMapper;
import com.giantlink.introduction.models.mapper.ImageMapper;
import com.giantlink.introduction.models.mapper.MovieCoverMapper;
import com.giantlink.introduction.models.mapper.MovieMapper;
import com.giantlink.introduction.models.requests.MovieRequest;
import com.giantlink.introduction.models.responses.ImageResponse;
import com.giantlink.introduction.models.responses.MovieResponse;
import com.giantlink.introduction.models.responses.MovieResponseCover;
import com.giantlink.introduction.repositories.ActorRepository;
import com.giantlink.introduction.repositories.CategoryRepository;
import com.giantlink.introduction.repositories.DirectorRepository;
import com.giantlink.introduction.repositories.ImageRepository;
import com.giantlink.introduction.repositories.MovieRepository;
import com.giantlink.introduction.services.ImageService;
import com.giantlink.introduction.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieCoverMapper movieCoverMapper;

	@Autowired
	MovieMapper movieMapper;

	@Autowired // l'injection des dependences
	MovieRepository movieRepository;

	@Autowired
	DirectorRepository directorRepository;

	@Autowired
	ImageMapper imageMapper;

	@Autowired
	ActorRepository actorRepository;

	@Autowired
	ResourceBundleMessageSource messageSource;

	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	DirectorMapper directorMapper;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ImageService imageService;

	@Transactional
	@Override
	public Movie add(MovieRequest movie) {

		List<Categorie> categories = new ArrayList<>();
		List<Actor> actors = new ArrayList<>();

		Optional<Director> findDirector = directorRepository.findByName(movie.getDirector().getName());

		Movie newMovie = Movie.builder().title(movie.getTitle()).description(movie.getDescription())
				.releaseDate(movie.getReleaseDate()).duration(movie.getDuration())
				.originalLanguage(movie.getOriginalLanguage()).build();

		movieRepository.save(newMovie);
		newMovie.setMvCategories(categories);
		newMovie.setMvActors(actors);

		if (movie.getMvActors() != null && !movie.getMvActors().isEmpty()) {
			movie.getMvActors().forEach(act -> {

				Optional<Actor> findActor = actorRepository.findActorByfirstName(act.getFirstName());
				if (findActor.isPresent()) {

					newMovie.getMvActors().add(findActor.get());
				} else {
					Actor actor = Actor.builder().firstName(act.getFirstName()).lastName(act.getLastName())
							.age(act.getAge()).photolink(act.getPhotolink()).build();

					actorRepository.save(actor);
					newMovie.getMvActors().add(actor);
				}

				// check
				// actorRepository.save(act);
			});

		}

		if (movie.getMvCategories() != null && !movie.getMvCategories().isEmpty()) {

			movie.getMvCategories().forEach(cat -> {

				Optional<Categorie> findCategorie = categoryRepository.findByName(cat.getName());
				if (findCategorie.isPresent()) {

					newMovie.getMvCategories().add(findCategorie.get());
				} else {
					Categorie categorie = Categorie.builder().name(cat.getName()).description(cat.getDescription())
							.build();

					categoryRepository.save(categorie);
					newMovie.getMvCategories().add(categorie);
				}
			});
		}

		if (movie.getMvImages() != null && !movie.getMvImages().isEmpty()) {
			movie.getMvImages().forEach(img -> {
				// check
				img.setMovie(newMovie);
				imageRepository.save(img);
			});

		}

		newMovie.setMvImages(movie.getMvImages());
		// newMovie.setMvActors(movie.getMvActors());
		// newMovie.setMvCategories(movie.getMvCategories());
		// movieRepository.save(newMovie);

		if (findDirector.isPresent()) {
			newMovie.setDirector(findDirector.get());
		} else {
			Director director = Director.builder().name(movie.getDirector().getName())
					.phone(movie.getDirector().getPhone()).build();
			directorRepository.save(director);
			newMovie.setDirector(director);
		}
		movieRepository.save(newMovie);
		return newMovie;
	}

	@Override
	public Movie get(Long id) {
		return movieRepository.findById(id).get();
		// return null;
	}

	@Override
	public void delete(Long id) {
		movieRepository.deleteById(id);
	}

	@Override
	public List<Movie> getAll() {
		return movieRepository.findAll();
	}

	@Override
	public List<MovieResponseCover> getAllWithCover() {
		List<MovieResponseCover> movieResponseCovers = new ArrayList<>();
		movieRepository.findAll().forEach(movie -> {
			MovieResponseCover movieResponseCover = MovieResponseCover.builder().title(movie.getTitle())
					.description(movie.getDescription()).director(movie.getDirector()).id(movie.getId())
					.releaseDate(movie.getReleaseDate()).build();

			Optional<Image> cover = movie.getMvImages().stream().filter(img -> img.getIsCover()).findFirst();
			if (cover.isPresent()) {
				ImageResponse coverResponse = ImageResponse.builder().id(cover.get().getId())
						.link(cover.get().getLink()).imageName(cover.get().getFileName()).type(cover.get().getType())
						.build();
				movieResponseCover.setCover(coverResponse);
			}
			movieResponseCovers.add(movieResponseCover);
		});
		return movieResponseCovers;
	}

	@Override
	public MovieResponseCover getMovieWithCover(Long id) {
		Movie movie1 = movieRepository.findById(id).get();
		MovieResponseCover movie = movieCoverMapper.movieToResponseCover(movieRepository.findById(id).get());
		MovieResponseCover movieResponseCover = MovieResponseCover.builder().title(movie.getTitle())
				.description(movie.getDescription()).director(movie.getDirector()).id(movie.getId())
				.releaseDate(movie.getReleaseDate()).build();

		Optional<Image> cover = movie1.getMvImages().stream().filter(img -> img.getIsCover()).findFirst();
		if (cover.isPresent()) {
			ImageResponse coverResponse = ImageResponse.builder().id(cover.get().getId()).link(cover.get().getLink())
					.imageName(cover.get().getFileName()).type(cover.get().getType()).build();
			movieResponseCover.setCover(coverResponse);
		}
		return movieResponseCover;

	}

	@Override
	public Movie update(Long id, MovieRequest movieDetails) throws IOException {

		Movie movie = movieRepository.findById(id).get();

		Optional<Director> findDirector = directorRepository.findByName(movieDetails.getDirector().getName());

		movie.setTitle(movieDetails.getTitle());
		movie.setDescription(movieDetails.getDescription());
		movie.setReleaseDate(movieDetails.getReleaseDate());
		movie.setOriginalLanguage(movieDetails.getOriginalLanguage());
		movie.setDuration(movieDetails.getDuration());
		movie.setDirector(directorMapper.requestToDirector(movieDetails.getDirector()));

		if (movieDetails.getMvCategories() != null && !movieDetails.getMvCategories().isEmpty()) {

			movieDetails.getMvCategories().forEach(cat -> {

				Optional<Categorie> findCategorie = categoryRepository.findByName(cat.getName());
				if (findCategorie.isPresent()) {

					movie.getMvCategories().add(findCategorie.get());
				} else {
					Categorie categorie = Categorie.builder().name(cat.getName()).description(cat.getDescription())
							.build();

					categoryRepository.save(categorie);
					movie.getMvCategories().add(categorie);
				}
			});
		}
		
		
		if (movieDetails.getMvActors() != null && !movieDetails.getMvActors().isEmpty()) {
			movieDetails.getMvActors().forEach(act -> {

				Optional<Actor> findActor = actorRepository.findActorByfirstName(act.getFirstName());
				if (findActor.isPresent()) {

					movie.getMvActors().add(findActor.get());
				} else {
					Actor actor = Actor.builder().firstName(act.getFirstName()).lastName(act.getLastName())
							.age(act.getAge()).photolink(act.getPhotolink()).build();

					actorRepository.save(actor);
					movie.getMvActors().add(actor);
				}

			});

		}
		
		

		if (movieDetails.getMvImages() != null && !movieDetails.getMvImages().isEmpty()) {
			movieDetails.getMvImages().forEach(img -> {
				// check
				img.setMovie(movie);
				imageRepository.save(img);
			});

		}

		movie.setMvImages(movieDetails.getMvImages());

		if (findDirector.isPresent()) {
			movie.setDirector(findDirector.get());
		} else {
			Director director = Director.builder().name(movieDetails.getDirector().getName())
					.phone(movieDetails.getDirector().getPhone()).build();
			directorRepository.save(director);
			movie.setDirector(director);
		}
		return movieRepository.save(movie);

	}

	@Override
	public Page<Movie> getAllPaginations(Pageable pageable) {

		return movieRepository.findAll(pageable);
	}

	@Override
	public Map<String, Object> getAllWithCoverPaginations(String title, Pageable pageable) {

		List<MovieResponseCover> movieResponseCovers = new ArrayList<>();
		Page<Movie> movies = (title.isBlank()) ? movieRepository.findAll(pageable)
				: movieRepository.findByTitleContainingOrderByReleaseDateDesc(title, pageable);
		movies.getContent().forEach(movie -> {
			MovieResponseCover movieResponseCover = MovieResponseCover.builder().title(movie.getTitle())
					.description(movie.getDescription()).director(movie.getDirector()).id(movie.getId())
					.releaseDate(movie.getReleaseDate()).build();

			Optional<Image> cover = movie.getMvImages().stream().filter(img -> img.getIsCover()).findFirst();
			if (cover.isPresent()) {
				ImageResponse coverResponse = ImageResponse.builder().id(cover.get().getId())
						.link(cover.get().getLink()).imageName(cover.get().getFileName()).type(cover.get().getType())
						.build();
				movieResponseCover.setCover(coverResponse);
			}
			movieResponseCovers.add(movieResponseCover);
		});
		Map<String, Object> moviesResponse = new HashMap<>();
		moviesResponse.put("content", movieResponseCovers);
		moviesResponse.put("currentPage", movies.getNumber());
		moviesResponse.put("totalElements", movies.getTotalElements());
		moviesResponse.put("totalPages", movies.getTotalPages());
		return moviesResponse;

	}

	@Override
	public void deleteCategorie(Long movieId, Long catId) {
		Movie movie = movieRepository.getById(movieId);
		Categorie cat = categoryRepository.getById(catId);
		movie.getMvCategories().remove(cat);
		movieRepository.save(movie);
	}

	// -------------------------------------------------------/

	/*
	 * @Override public Movie add(MovieRequest movie) { Optional<Director>
	 * findDirector = directorRepository.findByName(movie.getDirector().getName());
	 * Optional<Movie> findMovie =
	 * movieRepository.findMovieByTitle(movie.getTitle());
	 * 
	 * 
	 * 
	 * if(findMovie.isPresent()) throw new
	 * FindException("the movie is already exist !! please try again ..."); //Movie
	 * newMovie =
	 * Movie.builder().title(movie.getTitle()).description(movie.getDescription())
	 * //.image(movie.getImage()).build();
	 * 
	 * 
	 * 
	 * Movie newMovie = movieMapper.requestToMovie(movie);
	 * 
	 * movieRepository.save(newMovie); if (findDirector.isPresent()) {
	 * newMovie.setDirector(findDirector.get()); } else { Director director =
	 * Director.builder().name(movie.getDirector().getName())
	 * .phone(movie.getDirector().getPhone()).build();
	 * directorRepository.save(director); newMovie.setDirector(director); }
	 * movieRepository.save(newMovie); return newMovie; }
	 */

}
