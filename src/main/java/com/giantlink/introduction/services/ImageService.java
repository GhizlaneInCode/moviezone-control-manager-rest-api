package com.giantlink.introduction.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.giantlink.introduction.entities.Image;
import com.giantlink.introduction.models.requests.ImageRequest;

public interface ImageService {

	Image add(ImageRequest image);

	List<Image> getAll();

	//Image get(Long id);

	//void delete(Long id);
	
	//save to DB 
	
	Image saveImage(Long movieId, MultipartFile file) throws IOException;

	Image getImage(String imageId) throws Exception;
	
	//save to a folder 
	
	Image editImage(Image image);
	
	Image SaveImageLocal(Long movieId, MultipartFile file,Boolean isCover) throws IOException;
	
	void init();
	
	Path getUploadPath();
		
		
		


}
