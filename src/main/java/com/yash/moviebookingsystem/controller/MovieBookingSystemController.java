package com.yash.moviebookingsystem.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.yash.moviebookingsystem.exceptions.DuplicateValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.EntityNotFoundException;
import com.yash.moviebookingsystem.exceptions.NoMoviePresentInScreenException;
import com.yash.moviebookingsystem.exceptions.NoScreensSavedException;
import com.yash.moviebookingsystem.exceptions.NullValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.SizeOverflowException;
import com.yash.moviebookingsystem.model.Category;
import com.yash.moviebookingsystem.model.CategoryName;
import com.yash.moviebookingsystem.model.Movie;
import com.yash.moviebookingsystem.model.Screen;
import com.yash.moviebookingsystem.model.Show;
import com.yash.moviebookingsystem.service.ScreenService;
import com.yash.moviebookingsystem.service.SeatingArrangementService;
import com.yash.moviebookingsystem.serviceimpl.ScreenServiceImpl;
import com.yash.moviebookingsystem.serviceimpl.SeatingArrangementServiceImpl;

public class MovieBookingSystemController {
	
	private ScreenService screenService;
	
	private SeatingArrangementService seatingArrangementService;
	
	private Scanner operatorInput; 

	public MovieBookingSystemController() {
		
		screenService = new ScreenServiceImpl();
		seatingArrangementService = new SeatingArrangementServiceImpl();
		operatorInput = new Scanner(System.in);
	}
	
	public void displayMenu() {
		int choice = 0;
		do {
			System.out.println("---------------- Movie Booking System ---------------");
			System.out.println(
					"1. Add Screen \n2. Add Seating Arrangement To Screen \n3. Add Movie To Screen \n4. Add Shows For Movie \n5. Show Ticket Availability \n0. Exit \n");
			System.out.println("Enter Your Choice :-");
			choice = operatorInput.nextInt();
			switch (choice) {
			case 1:
				addScreen();
				break;
			case 2:
				addSeatingArrangementToScreen();
				break;
			case 3:
				addMovieToScreen();
				break;
			case 4:
				addShowToScreen();
				break;
			case 5:
				checkTicketAvailability();
				break;
			case 0:
				System.out.println("\nThank you for Using Movie Booking System.\n");
				System.exit(0);
				break;
			default:
				System.out.println("Poor choice for option. Please Select Valid Option \n");
				break;
			}
		} while (true);
	}

	private void checkTicketAvailability() {
		if(screenService.isAnyMoviePresentToShow()) {
			
			System.out.println("Enter Movie name :");
			String movieName = operatorInput.next();
			System.out.println("Enter Show time :");
			String showStartTime = operatorInput.next();
			Show show = screenService.getShowByMovie(movieName, showStartTime);
			seatingArrangementService.displaySeatingArrangementForScreen(show.getCategoryWiseSeatBookingStatus());
		}
		System.out.println("No movies added to Screen. Add movie first");
		
	}

	private void addShowToScreen() {
		try {
			screenService.isScreenListSizeZero();
			System.out.println("Enter Screen Name :");
			String screenName = operatorInput.next();
			screenService.getScreenByName(screenName);
			screenService.isAnyMoviePresentInTheScreen(screenName);
			System.out.println("Enter Number of Shows for today");
			int numberOfShows = operatorInput.nextInt();
			int count = 0;
			Set<Show> setOFShowsForTheScreen;
			do{
				setOFShowsForTheScreen = new HashSet<Show>();
				Show show = new Show();
				System.out.println("enter start time");
				String startTime = operatorInput.next();
				show.setStartTime(startTime);
				System.out.println("enter end time");
				String endTime = operatorInput.next();
				show.setEndTime(endTime);
				show.setCategoryWiseSeatBookingStatus(seatingArrangementService.prepareCategoryWiseBookingStatus());
				setOFShowsForTheScreen.add(show);
				count++;
			} while(count < numberOfShows);
			screenService.addShowToTheScreen(screenName, setOFShowsForTheScreen);
		} catch (NoScreensSavedException exc) {
			System.out.println(exc.getMessage());
		} 
		catch(EntityNotFoundException exc) {
			System.out.println(exc.getMessage());
		}
		catch(NoMoviePresentInScreenException exc) {
			System.out.println(exc.getMessage());
		}

	}
	
	private void addMovieToScreen() {
		
		boolean screensAvailable = false;
		Screen screenWithGivenScreenName = null;
		
		try {
			screensAvailable =!(screenService.isScreenListSizeZero());
		} catch (NoScreensSavedException screenNotSavedExc) {
			System.out.println(screenNotSavedExc.getMessage());
		}
		if(screensAvailable) {
			System.out.println("Enter Screen Name : ");
			operatorInput.nextLine();
			String screenName = operatorInput.nextLine();
			try {
					screenWithGivenScreenName =screenService.getScreenByName(screenName);
			} catch (EntityNotFoundException screenNotFoundExc) {
				System.out.println(screenNotFoundExc.getMessage());
			} 
		}
			
		if(screenWithGivenScreenName!=null) {
			Movie movie = new Movie();
			System.out.println("Enter Movie name : ");
			movie.setMovieName(operatorInput.nextLine());
			System.out.println("Enter Production : ");
			movie.setProduction(operatorInput.nextLine());
			String stop = null;
			List<String> cast = new ArrayList<>();
			do {
				System.out.println("Enter Cast : ");
				cast.add(operatorInput.nextLine());
				System.out.println("Add More Actor (Y/N)");
				stop = operatorInput.nextLine();
			} while (stop.equalsIgnoreCase("Y"));
			movie.setCast(cast);
			System.out.println("Enter Movie Duration in HH:mm format : ");
			movie.setRunningTime(operatorInput.nextLine());
			try {
				boolean isMovieAddedToScreen =screenService.addMovieToScreen(screenWithGivenScreenName, movie);
				if(isMovieAddedToScreen) {
				System.out.println("Movie added Successfully!");
				}
			} catch (NullValueNotAllowedException nullValueExc) {
				System.out.println(nullValueExc.getMessage());
			}
		}
		
	}

	private void addSeatingArrangementToScreen() {
		int rowCount;
		int maximumSeatInARowForTheCategory;
		boolean isAdded = false;
		Category gold = new Category();
		Category premium = new Category();
		Category silver = new Category();
		
		
		
		
		System.out.println("Enter the maximum number of rows for GOLD");
		rowCount = operatorInput.nextInt();
		gold.setMaximumRowsForThisCategory(rowCount);
		System.out.println("Enter maximum number of seats in a row");
		maximumSeatInARowForTheCategory = operatorInput.nextInt();
		System.out.println("Enter Price for GOLD Class Seat : ");
		int goldTicketPrice = operatorInput.nextInt();
		gold.setCategoryName(CategoryName.GOLD);
		gold.setMaximumRowsForThisCategory(rowCount);
		gold.setMaximumSeatForRow(maximumSeatInARowForTheCategory);
		gold.setTicketPrice(goldTicketPrice);

		System.out.println("Enter the maximum number of rows for PREMIUM");
		rowCount = operatorInput.nextInt();
		premium.setMaximumRowsForThisCategory(rowCount);
		System.out.println("Enter maximum number of seats in a row");
		maximumSeatInARowForTheCategory = operatorInput.nextInt();
		System.out.println("Enter Price for PREMIUM Class Seat : ");
		int premiumTicketPrice = operatorInput.nextInt();
		premium.setCategoryName(CategoryName.PREMIUM);
		premium.setMaximumRowsForThisCategory(rowCount);
		premium.setMaximumSeatForRow(maximumSeatInARowForTheCategory);
		premium.setTicketPrice(premiumTicketPrice);

		System.out.println("Enter the maximum number of rows for SILVER");
		rowCount = operatorInput.nextInt();
		premium.setMaximumRowsForThisCategory(rowCount);
		System.out.println("Enter maximum number of seats in a row");
		maximumSeatInARowForTheCategory = operatorInput.nextInt();
		System.out.println("Enter Price for Silver Class Seat : ");
		int silverTicketPrice = operatorInput.nextInt();
		silver.setCategoryName(CategoryName.SILVER);
		silver.setMaximumRowsForThisCategory(rowCount);
		silver.setMaximumSeatForRow(maximumSeatInARowForTheCategory);
		silver.setTicketPrice(silverTicketPrice);

		seatingArrangementService.addDesignGuidelineForCategory(gold);
		seatingArrangementService.addDesignGuidelineForCategory(premium);
		seatingArrangementService.addDesignGuidelineForCategory(silver);
		System.out.println("Seating Arrangement Settings saved!");
			
	}

	private void addScreen() {
		boolean isAdded = false;
		Screen screen = new Screen();
		System.out.println("Enter New Screen Name : ");
		operatorInput.nextLine();
		screen.setScreenName(operatorInput.nextLine());
		try {
			isAdded = screenService.addScreen(screen);
		} catch (NullValueNotAllowedException nullValueExc) {
			System.out.println(nullValueExc.getMessage());
		}
		catch (DuplicateValueNotAllowedException duplicateValueExc) {
			System.out.println(duplicateValueExc.getMessage());
		}
		catch (SizeOverflowException sizeOverflowExc) {
			System.out.println(sizeOverflowExc.getMessage());
		}
		if (isAdded)
			System.out.println("New Screen " + screen.getScreenName() + " Added SuccessFully");
		else
			System.out.println("Screen Not Added");
	}
}

