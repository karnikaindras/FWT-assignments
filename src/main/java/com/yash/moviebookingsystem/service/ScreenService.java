package com.yash.moviebookingsystem.service;

import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;

public interface ScreenService {

	boolean addScreen(Screen screen);

	boolean addMovieToScreen(Screen screen, Movie movie);

	Screen getScreenByName(String screenName);

}
