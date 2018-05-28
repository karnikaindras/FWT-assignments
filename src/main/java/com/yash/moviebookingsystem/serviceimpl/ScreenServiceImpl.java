package com.yash.moviebookingsystem.serviceimpl;

import java.util.List;
import java.util.Set;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.daoimpl.ScreenDAOImpl;
import com.yash.moviebookingsystem.exceptions.DuplicateValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.EmptyCollectionException;
import com.yash.moviebookingsystem.exceptions.EntityNotFoundException;
import com.yash.moviebookingsystem.exceptions.NoMoviePresentInScreenException;
import com.yash.moviebookingsystem.exceptions.NoScreensAvailableException;
import com.yash.moviebookingsystem.exceptions.NoScreensSavedException;
import com.yash.moviebookingsystem.exceptions.NullValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.SizeOverflowException;
import com.yash.moviebookingsystem.messages.ExceptionMessage;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Show;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.service.SeatingArrangementService;
import com.yash.moviebookingsystem.service.ShowService;
import com.yash.moviebookingsystem.util.ObjectUtil;

public class ScreenServiceImpl implements ScreenService {
	private ScreenDAO screenDAO;
	private SeatingArrangementService seatingArrangementService;
	private ShowService showService;

	public ScreenServiceImpl(ScreenDAO screenDao, ShowService showService) {
		this.screenDAO = screenDao;
		this.showService = showService;
	}
	
	public ScreenServiceImpl() {
		this.screenDAO = new ScreenDAOImpl();
		this.seatingArrangementService = new SeatingArrangementServiceImpl();
		this.showService = new ShowServiceImpl();
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
		return screenDAO.updateScreen(screen);
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
	
	public boolean isScreenListSizeZero() {
		boolean screenListSizeIsZero = false;
		if(screenDAO.getScreenListSize()==0) {
			throw new NoScreensSavedException(ExceptionMessage.WHEN_THERE_IS_NO_SCREEN_TO_ADD_MOVIE);
		}
		return screenListSizeIsZero;
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

	@Override
	public boolean addShowToTheScreen(String screenName, Set<Show> setOFShowsForTheScreen) {
		if(setOFShowsForTheScreen == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_SET_OF_SHOWS_IS_NULL);
		}
		
		if(screenName == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_SCREENNAME_IS_NULL);
		}
		if(setOFShowsForTheScreen.isEmpty()) {
			throw new EmptyCollectionException(ExceptionMessage.WHEN_SHOWS_ARE_EMPTY);
		}
		Screen updatedScreen = getScreenByName(screenName);
		
		if(updatedScreen == null) {
			throw new EntityNotFoundException(ExceptionMessage.WHEN_NO_SCREEN_WITH_GIVEN_NAME_EXIST);
		}
		updatedScreen.setShowsForThisScreen(setOFShowsForTheScreen);
		return screenDAO.updateScreen(updatedScreen);
	}

	@Override
	public Show getShowByMovie(String movieName, String showStartTime) {
		if(movieName == null || showStartTime == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_MOVIENAME_OR_SHOW_START_TIME_IS_NULL);
		}
		Screen screenForMovieName = screenDAO.findScreenByMovieName(movieName);
		if(screenForMovieName == null) {
			throw new EntityNotFoundException(ExceptionMessage.WHEN_NO_SCREEN_WITH_GIVEN_MOVIE_NAME_EXIST);
		}
		Show showWithGivenStartTime = showService.findShowByShowStartTime(screenForMovieName, showStartTime);
		if(showWithGivenStartTime == null) {
			throw new EntityNotFoundException(ExceptionMessage.WHEN_NO_SHOW_START_TIME_IS_WRONG);
		}
		return showWithGivenStartTime;
	}

	@Override
	public boolean isAnyMoviePresentToShow() {
		List<Screen> listOfAllScreen = screenDAO.getAllScreen();
		boolean isAtleastOneMoviePresent = false;
		Movie movie;
		for (Screen screen : listOfAllScreen) {
			movie = screen.getMovie();
			if(movie != null) {
				isAtleastOneMoviePresent = true;
			}
		}
		return isAtleastOneMoviePresent;
	}

	@Override
	public boolean isAnyMoviePresentInTheScreen(String screenName) {
		boolean isMoviePresentInScreen = false;
		Screen searchedScreen = screenDAO.findScreenByName(screenName);
		if(searchedScreen.getMovie() != null) {
			isMoviePresentInScreen = true;
		}
		else {
			throw new NoMoviePresentInScreenException(ExceptionMessage.WHEN_NO_MOVIE_IS_ADDED_TO_THE_SCREEN);
		}
		return isMoviePresentInScreen;
	}

}
