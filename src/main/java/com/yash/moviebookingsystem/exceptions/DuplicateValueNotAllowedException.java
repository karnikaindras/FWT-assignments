package com.yash.moviebookingsystem.exceptions;

public class DuplicateValueNotAllowedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DuplicateValueNotAllowedException(String message) {
		super(message);
	}

}
