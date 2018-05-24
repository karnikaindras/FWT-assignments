package com.yash.moviebookingsystem.exceptions;

public class ScreenDoesNotExist extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ScreenDoesNotExist(String message) {
		super(message);
	}

}
