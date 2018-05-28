package com.yash.moviebookingsystem.model;

import java.util.List;

public class Movie {
	private String movieName;
	private List<String> cast;
	private transient IScreen screen;
	private String screenName;
	private String production;
	private String runningTime;
	
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

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public List<String> getCast() {
		return cast;
	}

	public void setCast(List<String> cast) {
		this.cast = cast;
	}

	public IScreen getScreen() {
		return screen;
	}

	public void setScreen(IScreen screen) {
		this.screen = screen;
	}
	
	public String getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	@Override
	public String toString() {
		return "Movie [movieName=" + movieName + ", cast=" + cast + ", screen=" + screenName + "]";
	}

}
