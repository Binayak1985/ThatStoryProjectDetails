package com.startup.thatstory.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectControllerAdvice {

	@ExceptionHandler (value = org.springframework.dao.DuplicateKeyException.class)
	ResponseEntity <String> exception (org.springframework.dao.DuplicateKeyException ex)
	{
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("ProjectId and VersionId exists");
	}
}
