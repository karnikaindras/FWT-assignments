package com.yash.moviebookingsystem.serviceimpl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.yash.moviebookingsystem.service.SeatingArrangementService;

public class SeatingArrangementServiceImplTest {

	private SeatingArrangementService seatingArrangementService;
	
	@Before
	public void setUp() {
		seatingArrangementService = new SeatingArrangementServiceImpl();
	}
	
	@Test
	public void addTicketPricingForEachCategory_when_CategoryObjectPassedIsNull_shouldThrowException() {
		boolean isTicketPriceDetailsSaved = seatingArrangementService.addTicketPricingForEachCategory(10, 20, 90);
		assertEquals(true, isTicketPriceDetailsSaved);
	}

}
