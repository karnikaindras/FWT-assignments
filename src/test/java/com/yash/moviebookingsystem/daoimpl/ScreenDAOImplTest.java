package com.yash.moviebookingsystem.daoimpl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exceptions.DuplicateValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.EntityNotFoundException;
import com.yash.moviebookingsystem.exceptions.NullValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.SizeOverflowException;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;

public class ScreenDAOImplTest {
	private static Gson gson;
	private static ScreenDAO screenDAO;
	
	@Before
	public void setUp() {
		gson = new Gson();
		screenDAO = new ScreenDAOImpl(gson);
		Screen screen1 = new Screen(1, "Audi-1");
		Movie pokhran = new Movie();
		pokhran.setMovieName("pokhran");
		screen1.setMovie(pokhran);
		Screen screen2 = new Screen(1, "Audi-2");
		Movie razi = new Movie();
		razi.setMovieName("raazi");
		screen2.setMovie(razi);
		screenDAO.addScreen(screen1);
		screenDAO.addScreen(screen2);
	}

	@Test(expected = NullValueNotAllowedException.class)
	public void findScreenByName_NullScreenNameIsPassed_shouldThrowException() {
		String screenName = null;
		screenDAO.findScreenByName(screenName);
	}
	
	@Test()
	public void findScreenByName_ScreenNameIsPassedThatDoesNotExist_ReturnNull() {
		String screenNameThatDoesNotExist = "Audi-4";
		Screen screen = screenDAO.findScreenByName(screenNameThatDoesNotExist);
		assertEquals(null, screen);
	}
	
	@Test()
	public void findScreenByName_ScreenNameIsPassedExist_ReturnsTheScreenObjectWithGivenScreenName() {
		String screenNameThatExist = "Audi-1";
		Screen screen = screenDAO.findScreenByName(screenNameThatExist);
		assertEquals(screenNameThatExist,screen.getScreenName());
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void addScreen_ScreenObjectPassedIsNull_shouldThrowException() {
		Screen screen = null;
		screenDAO.addScreen(screen);
	}
	
	@Test(expected = SizeOverflowException.class)
	public void addScreen_ScreenObjectPassedIsNotNull_But3ScreensAreAlreadyPresent_shouldThrowException() {
		Screen screen3 = new Screen(1, "Audi-3");
		screenDAO.addScreen(screen3);
		Screen screen4 = new Screen(1, "Audi-4");
		screenDAO.addScreen(screen4);
	}
	
	@Test(expected = DuplicateValueNotAllowedException.class)
	public void addScreen_ScreenObjectPassedIsNotNull_ButAScreenWithTheSameNameExist_shouldThrowException() {
		Screen screenHasANameThatExist = new Screen(1, "Audi-1"); 
		screenDAO.addScreen(screenHasANameThatExist);
	}
	
	@Test
	public void getScreenListSize_shouldReturn2() {
		int screenListSize = screenDAO.getScreenListSize();
		assertEquals(2, screenListSize);	
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void updateScreen_when_null_is_passed_shouldThrowException() {
		screenDAO.updateScreen(null);	
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void updateScreen_when_notNullScreenObjectIsPassed_But_screenName_doesNotExist_shouldThrowException() {
		Screen updatedScreen = new Screen();
		updatedScreen.setScreenName("Audi-4");
		screenDAO.updateScreen(updatedScreen);	
	}
	
	@Test
	public void updateScreen_when_notNullScreenObjectIsPassed_But_screenName_Exists_shouldReturnTrue() {
		Screen updatedScreen = new Screen();
		updatedScreen.setScreenName("Audi-1");
		Movie pokhran = new Movie();
		pokhran.setMovieName("pokhran");
		updatedScreen.setMovie(pokhran);
		screenDAO.updateScreen(updatedScreen);	
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void findScreenByMovieName_when_null_is_passed_shouldThrowException() {
		screenDAO.findScreenByMovieName(null);	
	}
	
	@Test
	public void findScreenByMovieName_when_nonNullMovieNameisPassed_AndMovieName_DoesNotExist_shouldReturnNull() {
		Screen screen = screenDAO.findScreenByMovieName("NH10");	
		assertEquals(null, screen);
	}
	
	@Test
	public void findScreenByMovieName_when_nonNullMovieNameisPassed_AndMovieName_Exists_shouldReturnNonNullScreen_havingMovieNameAsGiven() {
		Screen screenWithMovieNameRazi = screenDAO.findScreenByMovieName("raazi");	
		assertEquals("raazi", screenWithMovieNameRazi.getMovie().getMovieName());
	}
	
}
