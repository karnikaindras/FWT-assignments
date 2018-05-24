package com.yash.moviebookingsystem.exceptions;

public class NoScreensAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoScreensAvailableException(String message) {
		super(message);
	}

}
