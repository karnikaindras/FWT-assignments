package com.yash.moviebookingsystem.daoimpl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.yash.moviebookingsystem.dao.ScreenDAO;
import com.yash.moviebookingsystem.exceptions.DuplicateValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.EntityNotFoundException;
import com.yash.moviebookingsystem.exceptions.NullValueNotAllowedException;
import com.yash.moviebookingsystem.exceptions.SizeOverflowException;
import com.yash.moviebookingsystem.messages.ExceptionMessage;
import com.yash.moviebookingsystem.model.Screen;

public class ScreenDAOImpl implements ScreenDAO {
	private List<Screen> screenList;
	private Gson gson;
	private FileWriter writer;
	public ScreenDAOImpl(Gson gson) {
		screenList = new ArrayList<>();
		this.gson = gson;
	}

	
	public ScreenDAOImpl() {
		screenList = new ArrayList<>();
		gson = new Gson();
	}


	@Override
	public Screen findScreenByName(String screenName) {
		Screen screenWithTheGivenName = null;
		if(screenName == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_SCREENNAME_IS_NULL);
		}
		for (Screen screen : screenList) {
			if(screen.getScreenName().equals(screenName))
				screenWithTheGivenName = screen;
		}
		return screenWithTheGivenName;
	}

	@Override
	public int getScreenListSize() {
		return screenList.size();
	}

	@Override
	public boolean addScreen(Screen screen) {
		boolean isScreenAdded = true;
		if(screen == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_SCREEN_OBJECT_IS_NULL);
		}
		
		if(getScreenListSize()>=3) {
			throw new SizeOverflowException(ExceptionMessage.WHEN_SCREEN_SIZE_IS_MORE_THAN_THREE);
		}
		
		if(findScreenByName(screen.getScreenName())!= null) {
			throw new DuplicateValueNotAllowedException(ExceptionMessage.WHEN_SCREENNAME_IS_DUPLICATE);
		}
		screenList.add(screen);
		try {
			saveScreenListToFile();
		}
		catch(IOException e) {
			System.out.println("internal database error");
		}
		return isScreenAdded;
	}
	
	private void saveScreenListToFile() throws IOException {
		String screenListToJson = gson.toJson(screenList);
		writer = new FileWriter("Screen.json");
		writer.write(screenListToJson);
		writer.close();
	}


	@Override
	public boolean updateScreen(Screen updatedScreen) {
		boolean IsScreenUpdated = false; 
		if(updatedScreen == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_SCREEN_OBJECT_IS_NULL);
		}
		
		if(findScreenByName(updatedScreen.getScreenName()) == null) {
			throw new EntityNotFoundException(ExceptionMessage.WHEN_NO_SCREEN_WITH_GIVEN_NAME_EXIST);
		}
		int indexTobeUpdated = screenList.indexOf(updatedScreen);
		Screen oldScreen = screenList.set(indexTobeUpdated, updatedScreen);
		if(oldScreen != null) {
			try {
				IsScreenUpdated = true;
				saveScreenListToFile();
			} catch (IOException e) {
				System.out.println("internal database error");
			}
		}
			
		return IsScreenUpdated;
	}


	@Override
	public Screen findScreenByMovieName(String movieName) {
		Screen screenForTheMovieName = null;
		if(movieName == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.MOVIE_NAME_CANT_BE_NULL);
		}
		for (Screen screen : screenList) {
			if(screen.getMovie().getMovieName().equals(movieName))
				screenForTheMovieName = screen;
		}
		return screenForTheMovieName;
	}


	@Override
	public List<Screen> getAllScreen() {
		return screenList;
	}
}
