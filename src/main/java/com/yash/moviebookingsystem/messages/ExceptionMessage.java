package com.yash.moviebookingsystem.messages;

public interface ExceptionMessage {

	String WHEN_SCREEN_IS_NULL = "Screen Object cannot be null";
	
	String WHEN_MOVIE_IS_NULL = "Movie Object cannot be null";
	
	String WHEN_SCREENNAME_IS_DUPLICATE = "Screen Name is duplicate";
	
	String WHEN_SCREEN_SIZE_IS_MORE_THAN_THREE = "Not more than three screens can be added";
	
	String WHEN_OBJECT_IS_NULL = "Null Values not allowed";
	
	String WHEN_THERE_IS_NO_SCREEN_TO_ADD_MOVIE = "Add Screens first";
	
	String WHEN_SCREENNAME_IS_NULL = "Screen Name cannot be null";
	
	String WHEN_SEARCHED_SCREEN_IS_NOT_FOUND = "No such screen present ";
	
	String WHEN_SCREEN_OBJECT_IS_NULL = "Screen object cannot be null";

	String WHEN_SHOWS_ARE_EMPTY = "Shows cannot be empty";

	String WHEN_MOVIENAME_OR_SHOW_START_TIME_IS_NULL = "Movie name or show start time cant be null";

	String MOVIE_NAME_CANT_BE_NULL = "Movie name cannot be null";

	String WHEN_NO_SCREEN_WITH_GIVEN_NAME_EXIST = "No screen with the given name exists";

	String WHEN_SET_OF_SHOWS_IS_NULL = "Set of shows cannot be null";

	String WHEN_NO_SCREEN_WITH_GIVEN_MOVIE_NAME_EXIST = "No screen with given movie name";

	String WHEN_NO_SHOW_START_TIME_IS_WRONG = "No Show with given start time exist";

	String WHEN_SHOW_START_TIME_IS_NULL = "Show start time cannot be null";

	String WHEN_NO_SHOWS_ARE_PRESENT_IN_THE_SCREEN = "No shows Available Add shows first";

	String WHEN_CATEGORY_IS_NULL = "Category cannot be null";

	String WHEN_NO_MOVIE_IS_ADDED_TO_THE_SCREEN = "No movie added to the screen add movie first";

}
