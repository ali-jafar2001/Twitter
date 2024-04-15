package logic;

import java.io.IOException;
import java.util.ArrayList;

import models.AccessLevel;
import models.Chat;
import models.Request;
import models.Tweet;
import models.User;
import panels.MainFrame;

public class ExplorerLogic {
	private static int i = 0;
	private static User user;

	public static void showPopTweets() {
		ArrayList<Tweet> tweets = new ArrayList<>();
		for (int i = 0; i < SocialMedia.getTweets().size(); i += 3) {
			tweets.add(SocialMedia.getTweets().get(i));
		}
		SocialMedia.getCurrent_user().getMenu().getExpelorer().setPopulars(tweets);
		if (tweets.size() == 0) {
			MainFrame.getInstance().emptyPopTweetsMessage();
		} else {
			MainFrame.getInstance().showPopTweets();
			MainFrame.getInstance().setPopTweets(tweets.get(0), SocialMedia.getPerson(tweets.get(0).getOwner()));
		}
		try {
			Logger.info("watched explorer tweets");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setNextPopTweet() {
		i++;
		if (i == SocialMedia.getCurrent_user().getMenu().getExpelorer().getPopulars().size())
			i = 0;
		MainFrame.getInstance().setPopTweets(
				SocialMedia.getCurrent_user().getMenu().getExpelorer().getPopulars().get(i), SocialMedia.getPerson(
						SocialMedia.getCurrent_user().getMenu().getExpelorer().getPopulars().get(i).getOwner()));
		try {
			Logger.info("watched tweet : "
					+ SocialMedia.getCurrent_user().getMenu().getExpelorer().getPopulars().get(i).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setPrevPopTweet() {
		i--;
		if (i == -1)
			i = SocialMedia.getCurrent_user().getMenu().getExpelorer().getPopulars().size() - 1;
		MainFrame.getInstance().setPopTweets(
				SocialMedia.getCurrent_user().getMenu().getExpelorer().getPopulars().get(i), SocialMedia.getPerson(
						SocialMedia.getCurrent_user().getMenu().getExpelorer().getPopulars().get(i).getOwner()));
		try {
			Logger.info("watched tweet : "
					+ SocialMedia.getCurrent_user().getMenu().getExpelorer().getPopulars().get(i).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void exploreUser(String st) {
		if (SocialMedia.isThere(st) && !st.equals(SocialMedia.getCurrent_user().username)) {
			user = SocialMedia.findUser(st);
			MainFrame.getInstance().showUserProfile();
			MainFrame.getInstance().setUserInformation(user);
			try {
				Logger.info("watched " + user.username + " profile");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			MainFrame.getInstance().emptyExploreUser();
		}

	}

	public static void follow() {
		if (isThereUserInFollowings(user))
			MainFrame.getInstance().showMessageDialog("user already exist in your following list ");
		else {
			if (user.getAccesslevel() == AccessLevel.pub) {
				followPublicUser(user);
				MainFrame.getInstance().showMessageDialog("user is now in your following list ");
				try {
					Logger.info("is now following " + user.username);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				sendRequest(user);
				MainFrame.getInstance().showMessageDialog("request has been sent");
				try {
					Logger.info("sent request to " + user.username);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void block() {
		boolean pre = isThereUserInBlckList(user);
		if (pre)
			MainFrame.getInstance().showMessageDialog("user already exist in your black list ");
		else {
			SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().add(user.username);
			user.getMenu().getPage().getNews().add(SocialMedia.getCurrent_user().username + " blocked you!");
			MainFrame.getInstance().showMessageDialog(user.username + " is now in your black list");
			try {
				Logger.warn("blocked " + user.username);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void direct() {
		if (isThereChat(user))
			MainFrame.getInstance().showMessageDialog("chat already exists");
		else {
			if (isUserBlocked(user))
				MainFrame.getInstance().showMessageDialog("you are in user's black list chat can not be created!");
			else {
				Chat c = new Chat(user.username);
				SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().add(c);
				c = new Chat(SocialMedia.getCurrent_user().username);
				user.getMenu().getMessaging().getChats().add(c);
				MainFrame.getInstance().showMessageDialog("chat created successfully");
				try {
					Logger.info("started chat with " + user.username);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private static boolean isThereChat(User user) {
		for (Chat chat : SocialMedia.getCurrent_user().getMenu().getMessaging().getChats()) {
			if (chat.getContact().equals(user.username))
				return true;
		}
		return false;
	}

	private static boolean isUserBlocked(User user) {
		for (String st : user.getMenu().getPage().getBlacklist()) {
			if (st.equals(SocialMedia.getCurrent_user().username))
				return true;
		}
		return false;
	}

	private static boolean isThereUserInBlckList(User user) {
		for (String st : SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist()) {
			if (user.username.equals(st))
				return true;
		}
		return false;
	}

	private static boolean isThereUserInFollowings(User user) {
		for (String st : SocialMedia.getCurrent_user().getMenu().getPage().getFollowings()) {
			if (user.username.equals(st))
				return true;
		}
		return false;

	}

	private static void followPublicUser(User user) {
		SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().add(user.username);
		user.getMenu().getPage().getFollowers().add(SocialMedia.getCurrent_user().username);
		user.getMenu().getPage().getNews().add(SocialMedia.getCurrent_user().username + " is following you");
	}

	private static void sendRequest(User user) {
		Request r = new Request(SocialMedia.getCurrent_user().username);
		user.getMenu().getPage().getRecivedrequests().add(r);
	}

}
