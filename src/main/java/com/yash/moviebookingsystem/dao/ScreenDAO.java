package com.yash.moviebookingsystem.dao;

import java.util.List;

import com.yash.moviebookingsystem.model.Screen;

public interface ScreenDAO {

	Screen findScreenByName(String screenName);

	int getScreenListSize();

	boolean addScreen(Screen screen);
	
	boolean updateScreen(Screen updatedScreen);
	
	Screen findScreenByMovieName(String movieName);
	
	List<Screen> getAllScreen();

}
