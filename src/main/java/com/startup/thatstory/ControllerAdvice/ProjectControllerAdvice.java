package com.startup.thatstory.ControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectControllerAdvice {

	@ExceptionHandler (value = org.springframework.dao.DuplicateKeyException.class)
	ResponseEntity <String> exception (org.springframework.dao.DuplicateKeyException ex)
	{
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("ProjectId and VersionId exists");
	}
}
