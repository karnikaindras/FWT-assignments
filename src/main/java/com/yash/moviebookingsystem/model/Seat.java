package com.yash.moviebookingsystem.model;

public class Seat {
	
private int seatNumber;
	
	private int rowNumber;
	
	private Availability availability;
	
	public Seat(int rowNumber, int seatNumber) {
		this.availability = Availability.A;
		this.rowNumber = rowNumber;
		this.seatNumber = seatNumber;
	}
	
	@Override
	public String toString() {
		return ""+seatNumber+""+availability;
	}

	public int getSeatNumber() {
		return seatNumber;
	}
	
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	public int getRowNumber() {
		return rowNumber;
	}
	
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	public Availability getAvailability() {
		return availability;
	}
	
	public void setAvailability(Availability availability) {
		this.availability = availability;
	}


}
