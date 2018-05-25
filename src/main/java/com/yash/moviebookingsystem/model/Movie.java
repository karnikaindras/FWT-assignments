package com.yash.moviebookingsystem.model;

public class Movie {
	private String movieName;
	private String cast;
	private transient IScreen screen;
	private String screenName;
	
	public Movie(IScreen screen)
	{
		this.screenName = screen.getScreenName();
	}
	
	public Movie() {
		
	}
	
	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public IScreen getScreen() {
		return screen;
	}

	public void setScreen(IScreen screen) {
		this.screen = screen;
	}
	
	@Override
	public String toString() {
		return "Movie [movieName=" + movieName + ", cast=" + cast + ", screen=" + screenName + "]";
	}

}
