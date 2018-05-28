package com.yash.moviebookingsystem.model;

import java.util.List;
import java.util.Map;

public class Show {
	
	private int id;
	
	private String startTime;
	
	private String endTime;
	
	private Map<Category, List<Row>> categoryWiseSeatBookingStatus;
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public Map<Category, List<Row>> getCategoryWiseSeatBookingStatus() {
		return categoryWiseSeatBookingStatus;
	}
	
	public void setCategoryWiseSeatBookingStatus(Map<Category, List<Row>> categoryWiseSeatBookingStatus) {
		this.categoryWiseSeatBookingStatus = categoryWiseSeatBookingStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	


}
