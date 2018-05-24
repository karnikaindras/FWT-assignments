package com.yash.moviebookingsystem.dao;

import com.yash.moviebookingsystem.model.Screen;

public interface ScreenDAO {

	Screen findScreenByName(String screenName);

	int getScreenListSize();

	boolean addScreen(Screen screen);

}
