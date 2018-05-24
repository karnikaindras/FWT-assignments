package com.yash.moviebookingsystem.model;

public class Screen implements IScreen {
	/**
	 * unique id of Screen
	 */
	private int screenId;
	
	/**
	 * unique name of screen
	 */
	private String screenName;
	
	private Movie movie;
	
	public Screen() {
		movie = new Movie(this);
	}

	
	public Screen(int screenId, String screenName) {
		super();
		this.screenId = screenId;
		this.screenName = screenName;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((screenName == null) ? 0 : screenName.hashCode());
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
		Screen other = (Screen) obj;
		if (screenName == null) {
			if (other.screenName != null)
				return false;
		} else if (!screenName.equals(other.screenName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Screen [screenName=" + screenName + ", movie=" + movie + "]";
	}
	

}
