package com.yash.moviebookingsystem.exceptions;

public class NoScreensSavedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoScreensSavedException(String message) {
		super(message);
	}

}
