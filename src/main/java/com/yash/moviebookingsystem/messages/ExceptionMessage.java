package com.yash.moviebookingsystem.messages;

public interface ExceptionMessage {

	String WHEN_SCREEN_IS_NULL = "Screen Object cannot be null";
	String WHEN_MOVIE_IS_NULL = "Movie Object cannot be null";
	String WHEN_SCREENNAME_IS_DUPLICATE = "Screen Name is duplicate";
	String WHEN_SCREEN_SIZE_IS_MORE_THAN_THREE = "Not more than three screens can be added";
	String WHEN_OBJECT_IS_NULL = "Null Values not allowed";
	String WHEN_THERE_IS_NO_SCREEN_TO_ADD_MOVIE = "Add Screens first";
	String WHEN_SCREENNAME_IS_NULL = "Screen Name cannot be null";
	String WHEN_SEARCHED_SCREEN_IS_NOT_FOUND = "No such screen present";

}
