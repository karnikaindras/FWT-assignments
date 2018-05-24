package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exceptions.DuplicateValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.EntityNotFoundException;
import com.yash.moviebookingsystem.exceptions.NullValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.SizeOverflowException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.service.ScreenService;

public class ScreenServiceImplTest {
	
	private ScreenDAO screenDao;
	private ScreenService screenService ;
	
	@Before
	public void setUp() {
		screenDao = mock(ScreenDAO.class);
		screenService = new ScreenServiceImpl(screenDao); 
	}

	@Test(expected = NullValueNotAllowedException.class)
	public void addScreen_ScreenObjectIsNull_should_Throw_Exception() {
		
		Screen screen = null;
		screenService.addScreen(screen);
	}
	
	@Test(expected = DuplicateValueNotAllowedException.class)
	public void addScreen_ScreenObjectHasADuplicateScreenName_should_Throw_Exception() {

		Screen screen = new Screen();
		screen.setScreenName("Audi-1");
		when(screenDao.findScreenByName(anyString())).thenReturn(screen);
		screenService.addScreen(screen);
		
	}
	
	@Test(expected = SizeOverflowException.class)
	public void addScreen_whenThreeScreensAreAlreadyPresent_should_Throw_Exception() {

		Screen screen = new Screen();
		screen.setScreenName("Audi-1");
		when(screenDao.getScreenListSize()).thenReturn(3);
		screenService.addScreen(screen);
	}
	
	@Test
	public void addScreen_whenNotNullAndUniqueScreenObjectIsGivenAndScreensPresentIsLessThanThree() {

		Screen screen = new Screen();
		screen.setScreenName("Audi-1");
		screen.setScreenId(1);
		when(screenDao.addScreen(screen)).thenReturn(true);
		assertTrue(screenService.addScreen(screen));
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void addMovieToScreen_whenMovieObjectIsNull_shouldThrow_Exception() {

		Movie movie = null;
		screenService.addMovieToScreen(new Screen(),movie);
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void addMovieToScreen_whenScreenObjectIsNull_shouldThrow_Exception() {

		Movie movie = new Movie();
		Screen screen = null;
		screenService.addMovieToScreen(screen,movie);
	}
	
	@Test
	public void addMovieToScreen_ScreenObjectToWhichMovieIsToBeAddedIsGiven_shouldReturnTrue() {

		Screen screen = new Screen();
		screen.setScreenName("screen1");
		screen.setScreenId(11);
		Movie movie = new Movie();
		movie.setMovieName("raazi");
		movie.setCast("alia");
		when(screenDao.addScreen(screen)).thenReturn(true);
		assertTrue(screenService.addMovieToScreen(screen,movie));
	}
	

	@Test(expected = NullValueNotAllowedException.class)
	public void getScreenByName_screenNameGivenIsNull_shouldThrowException() {

		String screenName = null;
		Screen screen=screenService.getScreenByName(screenName);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void getScreenByName_screenNameGiven_But_DoesNotExist_shouldThrowException() {

		String screenName = "Audi-1";
		when(screenDao.findScreenByName(any(String.class))).thenReturn(null);
		screenService.getScreenByName(screenName);
	}
	
	@Test
	public void getScreenByName_screenNameGiven_shouldReturnScreenObject() {

		String screenName = "Audi-1";
		Screen screen = new Screen();
		screen.setScreenName(screenName);
		screen.setScreenId(1);
		when(screenDao.findScreenByName(screenName)).thenReturn(screen);
		assertEquals(screen,screenService.getScreenByName(screenName));
	}
	
	
	
	
	
	
	

}
