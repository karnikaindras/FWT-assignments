package com.yash.moviebookingsystem.service;

import java.util.Set;

import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Show;

public interface ScreenService {

	boolean addScreen(Screen screen);

	boolean addMovieToScreen(Screen screen, Movie movie);

	Screen getScreenByName(String screenName);
	
	boolean isScreenListSizeZero();

	boolean addShowToTheScreen(String screenName, Set<Show> setOFShowsForTheScreen);

	Show getShowByMovie(String movieName, String showStartTime);
	
	boolean isAnyMoviePresentToShow();
	
	boolean isAnyMoviePresentInTheScreen(String screenName);

}
