package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exceptions.DuplicateValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.EmptyCollectionException;
import com.yash.moviebookingsystem.exceptions.EntityNotFoundException;
import com.yash.moviebookingsystem.exceptions.NullValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.SizeOverflowException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Show;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.service.ShowService;

public class ScreenServiceImplTest {
	
	private ScreenDAO screenDao;
	private ScreenService screenService ;
	private ShowService showService;
	
	@Before
	public void setUp() {
		screenDao = mock(ScreenDAO.class);
		showService = mock(ShowService.class);
		screenService = new ScreenServiceImpl(screenDao, showService); 		
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
		Movie movie = new Movie();
		movie.setMovieName("raazi");
		movie.setProduction("dj");
		when(screenDao.updateScreen(screen)).thenReturn(true);
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
		when(screenDao.findScreenByName(screenName)).thenReturn(screen);
		assertEquals(screen,screenService.getScreenByName(screenName));
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void addShowToTheScreen_ScreenObjectIsNotNull_And_SetOfShowsPassedIsNull_shouldThrowException() {
		String screenName = "Audi-1";
		screenService.addShowToTheScreen(screenName, null);
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void addShowToTheScreen_ScreenNametIsNull_And_SetOfShowsPassedIsNotNull_shouldThrowException() {
		Set<Show> setOFShowsForScreen = new HashSet<Show>();
		screenService.addShowToTheScreen(null, setOFShowsForScreen);
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void addShowToTheScreen_ScreenNameIsNotNull_And_SetOfShowsPassedIsEmpty_shouldThrowException() {
		String screenName = "Audi-1";
		Set<Show> setOFShowsForScreen = new HashSet<Show>();
		screenService.addShowToTheScreen(screenName, setOFShowsForScreen);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void addShowToTheScreen_ScreenNamePassedDoesNotExist_And_SetOfShowsPassedIsEmpty_shouldThrowException() {
		String screenName = "Audi-45";
		Set<Show> setOFShowsForScreen = new HashSet<Show>();
		Show show = new Show();
		show.setStartTime("12.00");
		show.setEndTime("2:14");
		setOFShowsForScreen.add(show);
		screenService.addShowToTheScreen(screenName, setOFShowsForScreen);
	}
	
	@Test
	public void addShowToTheScreen_ScreenNamePassedExists_And_SetOfShowsPassedIsNotEmpty_shouldReturnTrue() {
		String screenName = "Audi-1";
		Screen screen = new Screen();
		screen.setScreenName(screenName);
		Set<Show> setOFShowsForScreen = new HashSet<Show>();
		Show show = new Show();
		show.setStartTime("12.00 pm");
		show.setEndTime("2:14 pm");
		setOFShowsForScreen.add(show);
		when(screenDao.findScreenByName(any(String.class))).thenReturn(screen);
		when(screenDao.updateScreen(any(Screen.class))).thenReturn(true);
		boolean isShowAddedToScreen = screenService.addShowToTheScreen(screenName, setOFShowsForScreen);
		assertEquals(true, isShowAddedToScreen);
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void getShowByMovie_WhenMovieNamePassedIsNull_And_ShowTimePassedIsNotNull_shouldThrowException() {
		String movieName = null;
		String showStartTime ="10:00 pm";
		screenService.getShowByMovie(movieName, showStartTime);
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void getShowByMovie_WhenMovieNamePassedIsNotNull_And_ShowTimePassedIsNull_shouldThrowException() {
		String movieName = "raazi";
		String showStartTime =null;
		screenService.getShowByMovie(movieName, showStartTime);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void getShowByMovie_WhenMovieNamePassedDoesNotExist_shouldThrowException() {
		String movieName = "raazi";
		String showStartTime ="12:00 pm";
		when(screenDao.findScreenByMovieName(any(String.class))).thenReturn(null);
		screenService.getShowByMovie(movieName, showStartTime);
	}
	

	@Test(expected = EntityNotFoundException.class)
	public void getShowByMovie_WhenMovieNamePassedExist_But_ShowWithGivenTime_DoesNotExist_shouldThrowException() {
		String movieName = "raazi";
		String showStartTime ="12:00 pm";
		Screen screen = new Screen();
		screen.setScreenName("audi-1");
		Movie movie = new Movie();
		movie.setMovieName(movieName);
		screen.setMovie(movie);
		Show show = new Show();
		show.setStartTime("10:00 am");
		show.setEndTime("1:00 pm");
		Set<Show> setOFShowsForScreen = new HashSet<Show>();
		setOFShowsForScreen.add(show);
		screen.setShowsForThisScreen(setOFShowsForScreen);
		when(screenDao.findScreenByMovieName(any(String.class))).thenReturn(screen);
		when(showService.findShowByShowStartTime(any(Screen.class), any(String.class))).thenReturn(null);
		screenService.getShowByMovie(movieName, showStartTime);
	}
	
	@Test
	public void getShowByMovie_WhenMovieNamePassedExist_But_ShowWithGivenTime_Exist_shouldReturnShowWithGivenStartTime() {
		String movieName = "raazi";
		String showStartTime ="12:00 pm";
		Screen screen = new Screen();
		screen.setScreenName("audi-1");
		Movie movie = new Movie();
		movie.setMovieName(movieName);
		screen.setMovie(movie);
		Show show = new Show();
		show.setStartTime("12:00 pm");
		show.setEndTime("1:00 pm");
		Set<Show> setOFShowsForScreen = new HashSet<Show>();
		setOFShowsForScreen.add(show);
		screen.setShowsForThisScreen(setOFShowsForScreen);
		when(screenDao.findScreenByMovieName(any(String.class))).thenReturn(screen);
		when(showService.findShowByShowStartTime(any(Screen.class), any(String.class))).thenReturn(show);
		Show showWithGivenStartTime = screenService.getShowByMovie(movieName, showStartTime);
		assertEquals(showStartTime, showWithGivenStartTime.getStartTime());
	}
	
	
	
	
	
	

}
