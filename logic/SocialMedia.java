 package logic;

import java.io.IOException;

import java.util.ArrayList;

import models.Tweet;
import models.User;

public class SocialMedia {
	private static ArrayList<User> users;
	private static ArrayList<Tweet> tweets;
	private static User current_user;

	public static User getCurrent_user() {
		return current_user;
	}

	public static void setCurrent_user(User current_user) {
		SocialMedia.current_user = current_user;
	}

	public static ArrayList<User> getUsers() {
		return users;
	}

	public static void setUsers(ArrayList<User> users) {
		SocialMedia.users = users;
	}

	public static boolean isThere(String ans) {
		for (User p : users) {
			if (p.username.equals(ans))
				return true;
		}
		return false;
	}

	public static boolean isCorrectEmail(String ans) {
		if (ans.length() < 10)
			return false;
		for (User per : users) {
			if (per.getEmail().equals(ans))
				return false;
		}
		String st1 = "@gmail.com";
		String st2 = "@ymail.com";
		String st = ans.substring(ans.length() - 10);
		if (st.equals(st1) || st.equals(st2))
			return true;
		return false;
	}

	public static User getPerson(String username) {
		for (User pr : users) {
			if (pr.username.equals(username))
				return pr;
		}
		return null;
	}

	public static boolean isCorrectPassword(User person, String password) {
		if (person.username.equals(password))
			return true;
		return false;
	}

	public static ArrayList<Tweet> getTweets() {
		return tweets;
	}

	public static void setTweets(ArrayList<Tweet> tweets) {
		SocialMedia.tweets = tweets;
	}

	public static void Init() {
		try {
			Logger.info("Program starts.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Saver.loadUsers();
		tweets = new ArrayList<Tweet>();
		for (User p : users) {
			for (Tweet t : p.getMenu().getPage().getTweets()) {
				tweets.add(t);
			}
		}
	}

	public static User findUser(String st) {
		for (User u : getUsers()) {
			if (u.username.equals(st))
				return u;
		}
		return null;
	}

}