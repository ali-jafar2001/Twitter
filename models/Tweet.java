package models;

import java.util.ArrayList;

public class Tweet extends Message {
	private ArrayList<Tweet> comments;
	private int retweets;
	private Tweet sup;
	private int spam;

	public int getSpam() {
		return spam;
	}

	public void setSpam(int spam) {
		this.spam = spam;
	}

	public Tweet getSup() {
		return sup;
	}

	public void setSup(Tweet sup) {
		this.sup = sup;
	}

	public int getRetweets() {
		return retweets;
	}

	public void setRetweets(int retweets) {
		this.retweets = retweets;
	}

	public ArrayList<Tweet> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Tweet> comments) {
		this.comments = comments;
	}

	public int getLovers() {
		return lovers;
	}

	public void setLovers(int lovers) {
		this.lovers = lovers;
	}

	private int lovers;

	public Tweet(String tweet) {
		super(tweet);
		comments = new ArrayList<Tweet>();
		lovers = 0;
		retweets = 0;
		spam = 0;
	}

	public Tweet(String tweet, String owner) {
		super(tweet, owner);
		comments = new ArrayList<Tweet>();
		lovers = 0;
		retweets = 0;
		spam = 0;
	}

}
