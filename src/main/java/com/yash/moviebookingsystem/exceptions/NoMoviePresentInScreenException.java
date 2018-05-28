package com.yash.moviebookingsystem.exceptions;

public class NoMoviePresentInScreenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMoviePresentInScreenException(String message) {
		super(message);
	}

}
