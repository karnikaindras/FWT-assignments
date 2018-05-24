package com.yash.moviebookingsystem.serviceimpl;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exceptions.DuplicateValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.EntityNotFoundException;
import com.yash.moviebookingsystem.exceptions.NoScreensAvailableException;
import com.yash.moviebookingsystem.exceptions.NullValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.SizeOverflowException;
import com.yash.moviebookingsystem.messages.ExceptionMessage;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.util.ObjectUtil;

public class ScreenServiceImpl implements ScreenService {
	private ScreenDAO screenDAO;

	public ScreenServiceImpl(ScreenDAO screenDao) {
		this.screenDAO = screenDao;
	}

	public boolean addScreen(Screen screen) {
		if(screen == null)
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_SCREEN_IS_NULL);
		doesScreenNameAlreadyExist(screen);
		IsScreenListSizeNotMoreThanThree();
		return screenDAO.addScreen(screen);
		
	}
	
	public boolean addMovieToScreen(Screen screen, Movie movie) {
		
		if(screen == null)
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_SCREEN_IS_NULL);
		
		if(movie == null)
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_MOVIE_IS_NULL);
		
		screen.setMovie(movie);
		movie.setScreen(screen);
		return screenDAO.addScreen(screen);
	}
	
	public Screen getScreenByName(String screenName) {
		Screen searchedScreen =null;
		if(screenName == null)
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_SCREENNAME_IS_NULL);
		searchedScreen = screenDAO.findScreenByName(screenName);
		if(searchedScreen == null)
			throw new EntityNotFoundException(ExceptionMessage.WHEN_SEARCHED_SCREEN_IS_NOT_FOUND);
		return searchedScreen;
	}
	
	private boolean IsScreenListSizeNotMoreThanThree() {
		
		boolean screenSizeIsNotMoreThanThree = false;
		if(screenDAO.getScreenListSize()>=3) {
			throw new SizeOverflowException(ExceptionMessage.WHEN_SCREEN_SIZE_IS_MORE_THAN_THREE);
		}
		return screenSizeIsNotMoreThanThree;
	}

	private boolean doesScreenNameAlreadyExist(Screen screen) {
		boolean isScreenNameAlreadyPresent =false;
		if(screenDAO.findScreenByName(screen.getScreenName())!=null)
			throw new DuplicateValueNotAllowedException(ExceptionMessage.WHEN_SCREENNAME_IS_DUPLICATE);
		return isScreenNameAlreadyPresent;
	}

}
