package com.yash.moviebookingsystem.service;

import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Show;

public interface ShowService {
	
	public Show findShowByShowStartTime(Screen screen , String showStartTime);

}
