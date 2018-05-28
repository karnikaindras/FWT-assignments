package com.yash.moviebookingsystem.main;

import com.yash.moviebookingsystem.controller.MovieBookingSystemController;

public class StartUp {

	public static void main(String[] args) {
		MovieBookingSystemController mbsController = new MovieBookingSystemController();
		mbsController.displayMenu();

	}

}
