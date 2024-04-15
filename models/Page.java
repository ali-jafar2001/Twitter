package models;


import java.util.ArrayList;

public class Page {
	private String profileAddress = "default";

	public String getProfileAddress() {
		return profileAddress;
	}

	public void setProfileAddress(String profileAddress) {
		this.profileAddress = profileAddress;
	}

	private ArrayList<Tweet> tweets;
	private ArrayList<String> blacklist;
	private ArrayList<String> followers;
	private ArrayList<String> followings;
	private ArrayList<Request> Recivedrequests;

	public ArrayList<Request> getRecivedrequests() {
		return Recivedrequests;
	}

	public void setRecivedrequests(ArrayList<Request> recivedrequests) {
		Recivedrequests = recivedrequests;
	}

	private ArrayList<String> news;

	public ArrayList<String> getNews() {
		return news;
	}

	public void setNews(ArrayList<String> news) {
		this.news = news;
	}

	public void Addtweet(String st) {
		Tweet tweet = new Tweet(st);
		tweets.add(tweet);
	}

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(ArrayList<Tweet> tweets) {
		this.tweets = tweets;
	}

	public ArrayList<String> getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(ArrayList<String> blacklist) {
		this.blacklist = blacklist;
	}

	public ArrayList<String> getFollowers() {
		return followers;
	}

	public void setFollowers(ArrayList<String> followers) {
		this.followers = followers;
	}

	public ArrayList<String> getFollowings() {
		return followings;
	}

	public void setFollowings(ArrayList<String> followings) {
		this.followings = followings;
	}

	public Page() {
		tweets = new ArrayList<Tweet>();
		blacklist = new ArrayList<String>();
		followers = new ArrayList<String>();
		followings = new ArrayList<String>();
		Recivedrequests = new ArrayList<Request>();
		news = new ArrayList<String>();
	}
}
