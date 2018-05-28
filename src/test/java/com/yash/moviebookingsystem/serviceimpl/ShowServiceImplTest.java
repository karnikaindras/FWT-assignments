package com.yash.moviebookingsystem.serviceimpl;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import com.yash.moviebookingsystem.exceptions.NoShowsAvailaibleForThisScreenException;
import com.yash.moviebookingsystem.exceptions.NullValueNotAllowedException;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Show;
import com.yash.moviebookingsystem.service.ShowService;

public class ShowServiceImplTest {
	private ShowService showService;
	
	@Before
	public void setUp() {
		showService = new ShowServiceImpl();
	}

	@Test(expected = NullValueNotAllowedException.class)
	public void findShowByShowStartTime_WhenScreenObjectPassedIsNull_shouldThrowException() {
		showService.findShowByShowStartTime(null, "10:00 pm");
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void findShowByShowStartTime_WhenShowTimePassedIsNull_shouldThrowException() {
		showService.findShowByShowStartTime(new Screen(), null);
	}
	
	@Test(expected = NoShowsAvailaibleForThisScreenException.class)
	public void findShowByShowStartTime_WhenScreenDoesNotHaveAnyShow_shouldThrowException() {
		Screen screen= new Screen();
		screen.setScreenName("Audi-1");
		screen.setShowsForThisScreen(new HashSet<Show>());
		showService.findShowByShowStartTime(screen, "10:00");
	}

	@Test
	public void findShowByShowStartTime_WhenScreenObjectPassedIsNotNull_And_NoShowsExistWithGivenStartTimeshouldReturnNull() {
		Screen screen= new Screen();
		screen.setScreenName("Audi-1");
		Set<Show> showsForScreen = new HashSet<Show>();
		Show show1 = new Show();
		show1.setStartTime("10:00 pm");
		show1.setEndTime("1:00 pm");
		Show show2 = new Show();
		show2.setStartTime("2:00 pm");
		show2.setEndTime("4:00 pm");
		showsForScreen.add(show1);
		showsForScreen.add(show2);
		screen.setShowsForThisScreen(showsForScreen);
		Show showWithGivenStartTime = showService.findShowByShowStartTime(screen, "5:00 pm");
		assertEquals(null, showWithGivenStartTime);
	}
	
	@Test
	public void findShowByShowStartTime_WhenScreenObjectPassedIsNotNull_And_NoShowExistWithGivenStartTimeshouldReturnTheShowWithGivenStartTime() {
		Screen screen= new Screen();
		screen.setScreenName("Audi-1");
		Set<Show> showsForScreen = new HashSet<Show>();
		Show show1 = new Show();
		show1.setStartTime("10:00 pm");
		show1.setEndTime("1:00 pm");
		Show show2 = new Show();
		show2.setStartTime("2:00 pm");
		show2.setEndTime("4:00 pm");
		showsForScreen.add(show1);
		showsForScreen.add(show2);
		screen.setShowsForThisScreen(showsForScreen);
		Show showWithGivenStartTime = showService.findShowByShowStartTime(screen, "10:00 pm");
		assertEquals("10:00 pm", showWithGivenStartTime.getStartTime());
	}


}
