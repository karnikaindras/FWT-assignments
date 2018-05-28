package com.yash.moviebookingsystem.model;

import java.util.Set;

public class Screen implements IScreen {
	
	private static int screenIdGenerator = 0;
	/**
	 * unique id of Screen
	 */
	private int screenId;
	
	/**
	 * unique name of screen
	 */
	private String screenName;
	
	private Set<Show> showsForThisScreen;

	private Movie movie;
	
	public Screen() {
		
		movie = new Movie(this);
		this.screenId = screenIdGenerator++;
	}

	
	public Screen(int screenId, String screenName) {
		super();
		
		this.screenName = screenName;
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
	
	
	public Set<Show> getShowsForThisScreen() {
		return showsForThisScreen;
	}


	public void setShowsForThisScreen(Set<Show> showsForThisScreen) {
		this.showsForThisScreen = showsForThisScreen;
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
