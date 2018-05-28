package com.yash.moviebookingsystem.serviceimpl;

import java.util.Set;

import com.yash.moviebookingsystem.exceptions.NoShowsAvailaibleForThisScreenException;
import com.yash.moviebookingsystem.exceptions.NullValueNotAllowedException;
import com.yash.moviebookingsystem.messages.ExceptionMessage;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Show;
import com.yash.moviebookingsystem.service.ShowService;

public class ShowServiceImpl implements ShowService {
	
	public ShowServiceImpl() {
		
	}

	@Override
	public Show findShowByShowStartTime(Screen screen, String showStartTime) {
		if(screen == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_SCREEN_IS_NULL);
		}		
		if(showStartTime == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_SHOW_START_TIME_IS_NULL);
		}
		Set<Show> showsInTheScreen = screen.getShowsForThisScreen();
		if(showsInTheScreen.isEmpty()) {
			throw new NoShowsAvailaibleForThisScreenException(ExceptionMessage.WHEN_NO_SHOWS_ARE_PRESENT_IN_THE_SCREEN);
		}
		Show showWithTheGivenStartTime = null;
		for (Show show : showsInTheScreen) {
			if(show.getStartTime().equals(showStartTime)) {
				showWithTheGivenStartTime = show;
			}
		}
		return showWithTheGivenStartTime;
	}

}
