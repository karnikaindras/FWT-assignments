package com.yash.moviebookingsystem.model;

public class Category {
	
	private CategoryName categoryName;
	
	private int maximumRowsForThisCategory;

	private int maximumSeatForRow;
	
	private int ticketPrice;
	
	public Category(CategoryName categoryName, int maximumRowsForThisCategory, int maximumSeatForRow) {
		this.categoryName = categoryName;
		this.maximumRowsForThisCategory = maximumRowsForThisCategory;
		this.maximumSeatForRow = maximumSeatForRow;
	}

	public Category() {
	
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public CategoryName getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(CategoryName categoryName) {
		this.categoryName = categoryName;
	}

	public int getMaximumRowsForThisCategory() {
		return maximumRowsForThisCategory;
	}

	public void setMaximumRowsForThisCategory(int maximumRowsForThisCategory) {
		this.maximumRowsForThisCategory = maximumRowsForThisCategory;
	}

	public int getMaximumSeatForRow() {
		return maximumSeatForRow;
	}

	public void setMaximumSeatForRow(int maximumSeatForRow) {
		this.maximumSeatForRow = maximumSeatForRow;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryName != other.categoryName)
			return false;
		return true;
	}
}
