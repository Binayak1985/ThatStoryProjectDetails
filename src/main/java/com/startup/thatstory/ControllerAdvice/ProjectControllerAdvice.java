package com.startup.thatstory.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectControllerAdvice  {

	@ExceptionHandler (org.springframework.web.bind.MethodArgumentNotValidException.class)
	ResponseEntity <String> payLoadErrorExceptions(org.springframework.web.bind.MethodArgumentNotValidException ex)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler (Exception.class)
	ResponseEntity <String> objectNotFoundException(Exception ex)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
