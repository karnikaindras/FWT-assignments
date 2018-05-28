package com.yash.moviebookingsystem.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yash.moviebookingsystem.exceptions.NullValueNotAllowedException;
import com.yash.moviebookingsystem.messages.ExceptionMessage;
import com.yash.moviebookingsystem.model.Category;
import com.yash.moviebookingsystem.model.CategoryName;
import com.yash.moviebookingsystem.model.Row;
import com.yash.moviebookingsystem.model.Seat;
import com.yash.moviebookingsystem.service.SeatingArrangementService;

public class SeatingArrangementServiceImpl implements SeatingArrangementService {
	
	private Set<Category> categoriesWithDesignGuideline;
	

	public Set<Category> getCategoriesWithDesignGuideline() {
		return categoriesWithDesignGuideline;
	}

	public void setCategoriesWithDesignGuideline(Set<Category> categoriesWithDesignGuideline) {
		this.categoriesWithDesignGuideline = categoriesWithDesignGuideline;
	}

	public SeatingArrangementServiceImpl() {
		categoriesWithDesignGuideline = new HashSet<Category>();
	}


	private List<Row> prepareSeatingArrangementForCategory(Category category) {
		if(category == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_CATEGORY_IS_NULL);
		}
		
		List<Row> seatingArrangementforGivenCategory = new ArrayList<>();
		int maxRow = category.getMaximumRowsForThisCategory();
		int maxSeat = category.getMaximumSeatForRow();
		for (int i = 1; i <= maxRow; i++) {
			
			Row row = new Row();
			List<Seat> seatsInARow = new LinkedList<>();
			
			for (int j = 1; j <= maxSeat; j++) {
				Seat seat = new Seat(maxRow, j);
				seatsInARow.add(seat);
			}
			row.setSeatsInRow(seatsInARow);
			seatingArrangementforGivenCategory.add(row);
			maxSeat -= 2;
		}
		return seatingArrangementforGivenCategory;
	}

	@Override
	public void displaySeatingArrangementForScreen(Map<Category, List<Row>> seatingArrangmentForScreen) {
		
		Set<Category> setOfCategories = new HashSet<Category>();
		setOfCategories = seatingArrangmentForScreen.keySet();
		for (Category category : setOfCategories) {
			List<Row> rowsInCategory = seatingArrangmentForScreen.get(category);
			printRowArrangement(rowsInCategory, category.getMaximumRowsForThisCategory());
		}
		
	}
	
	private void printRowArrangement(List<Row> rowsOfCategory, int maxRows) {
		int rowId =1;
		for (Row row : rowsOfCategory) {
			printSeatsForTheRow(row, rowId );
			System.out.println();
			rowId++;
		}
	}
	
	private void printSeatsForTheRow(Row row, int rowId) {

		System.out.print("R"+rowId+" ");
		for (Seat seat : row.getSeatsInRow()) {
			
			System.out.print(seat+" ");
		}
	}

	@Override
	public void addDesignGuidelineForCategory(Category category) {
		
		categoriesWithDesignGuideline.add(category);
	}

	@Override
	public Map<Category, List<Row>> prepareCategoryWiseBookingStatus() {
		Map<Category, List<Row>> categoryWiseSeatBookingStatus = new HashMap<Category, List<Row>>();
		for (Category category : categoriesWithDesignGuideline) {
			List<Row> listOfRowsForCategory = prepareSeatingArrangementForCategory(category);
			categoryWiseSeatBookingStatus.put(category, listOfRowsForCategory);
		}
		return categoryWiseSeatBookingStatus;
	}

	@Override
	public boolean addTicketPricingForEachCategory(int priceForGold, int priceForSilver, int priceForPremium) {
		for (Category category : categoriesWithDesignGuideline) {
			if(category.getCategoryName()== CategoryName.GOLD) {
					category.setTicketPrice(priceForGold);		
			}			
		}
		return true;
	}
	
	

}
