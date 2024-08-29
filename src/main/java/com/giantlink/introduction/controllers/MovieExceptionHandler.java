package com.giantlink.introduction.controllers;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class MovieExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> HandleInput(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		return errors;
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FileAlreadyExistsException.class)
	public Map<String, String> HandleFileAlreadyExists(FileAlreadyExistsException ex) {
		Map<String, String> errors = new HashMap<String, String>();

		errors.put(ex.getFile(), " Already Exists ");
		System.out.println("ex ===========> " + ex.getFile() + " ============ " + ex.getMessage());		
		return errors;
	}

	
	// file not found
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(FileNotFoundException.class)
		public Map<String, String> HandleFileNotFound(FileNotFoundException ex) {
			Map<String, String> errors = new HashMap<String, String>();

			errors.put("FILE_NOT_FOUND", ex.getMessage());
			//System.out.println("ex ===========> " + ex.getFile() + " ============ " + ex.getMessage());		
			return errors;
		}	
		
		// max upload size
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(MaxUploadSizeExceededException.class)
		public Map<String, String> HandleMaxUploadSize(MaxUploadSizeExceededException ex) {
			Map<String, String> errors = new HashMap<String, String>();

			errors.put("MAX_UPLOAD_SIZE", ex.getMessage());
			//System.out.println("ex ===========> " + ex.getFile() + " ============ " + ex.getMessage());		
			return errors;
		}

	
		

	
	
	
}



