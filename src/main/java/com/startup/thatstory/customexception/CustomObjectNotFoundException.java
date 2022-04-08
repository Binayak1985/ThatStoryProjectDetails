package com.startup.thatstory.customexception;

public class CustomObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 723744985126865124L;

	public CustomObjectNotFoundException(String message)
	{
		super(message);
	}
}
