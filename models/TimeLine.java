package models;

import java.util.ArrayList;

public class TimeLine {
	private ArrayList<Tweet> suggested;
	private ArrayList<Tweet> myretweets;

	public ArrayList<Tweet> getMyretweets() {
		return myretweets;
	}

	public void setMyretweets(ArrayList<Tweet> myretweets) {
		this.myretweets = myretweets;
	}

	public ArrayList<Tweet> getMyfavoritetweets() {
		return myfavoritetweets;
	}

	public void setMyfavoritetweets(ArrayList<Tweet> myfavoritetweets) {
		this.myfavoritetweets = myfavoritetweets;
	}

	private ArrayList<Tweet> myfavoritetweets;

	public ArrayList<Tweet> getSuggested() {
		return suggested;
	}

	public void setSuggested(ArrayList<Tweet> suggested) {
		this.suggested = suggested;
	}

	public TimeLine() {
		suggested = new ArrayList<Tweet>();
		myfavoritetweets = new ArrayList<Tweet>();
		myretweets = new ArrayList<Tweet>();
	}

}
