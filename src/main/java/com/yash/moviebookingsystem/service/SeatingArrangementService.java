package com.yash.moviebookingsystem.service;

import java.util.List;
import java.util.Map;

import com.yash.moviebookingsystem.model.Category;
import com.yash.moviebookingsystem.model.Row;

public interface SeatingArrangementService {
	
	public void displaySeatingArrangementForScreen(Map<Category, List<Row>> seatingArrangmentForScreen);
	
	public void addDesignGuidelineForCategory(Category category);
	
	public Map<Category, List<Row>> prepareCategoryWiseBookingStatus();
	
	public boolean addTicketPricingForEachCategory(int priceForGold, int priceForSilver, int priceForPremium);
}
