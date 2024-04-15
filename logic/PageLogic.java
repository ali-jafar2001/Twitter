package logic;

import java.awt.Color;
import java.io.IOException;

import Main.Main;
import models.Date;
import models.Request;
import models.Tweet;
import models.User;
import panels.MainFrame;

public class PageLogic {
	private static int i = 0;
	private static int i1 = 0;
	private static int i2 = 0;
	private static int i3 = 0;
	private static int i4 = 0;

	public static void editFname(String st) {
		SocialMedia.getCurrent_user().firstname = st;
		try {
			Logger.info("first name has been changed to " + st);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void editLname(String st) {
		SocialMedia.getCurrent_user().lastname = st;
		try {
			Logger.info("last name has been changed to " + st);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void editBio(String st) {
		SocialMedia.getCurrent_user().bio = st;
		try {
			Logger.info("bio has been changed to " + st);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void editEmail(String st) {
		SocialMedia.getCurrent_user().Email = st;
		try {
			Logger.info("Mail has been changed to " + st);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void editPhone(String st) {
		SocialMedia.getCurrent_user().phonenumber = st;
		try {
			Logger.info("phone number has been changed to " + st);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void editPassword(String st) {
		SocialMedia.getCurrent_user().setPassword(st);
		try {
			Logger.info("password has been changed to " + st);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void editBirth(String st) {
		int year = Integer.valueOf(st.substring(0, 2));
		int month = Integer.valueOf(st.substring(3, 5));
		int day = Integer.valueOf(st.substring(6));
		Date date = new Date(day, month, year);
		SocialMedia.getCurrent_user().birthdate = date;
		try {
			Logger.info("birth date has been set ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void editUsername(String st) {
		SocialMedia.getCurrent_user().username = st;
		try {
			Logger.info("username has been changed to " + st);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setProfile(String st) {
		if (st.contains(".png") || st.contains(".jpg")) {
			SocialMedia.getCurrent_user().getMenu().getPage().setProfileAddress(st);
			try {
				Logger.info("changed profile picture");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainFrame.getInstance().setProfilePicture();
		}
	}

	public static void addTweet(String st) {
		if (st != null && !st.equals("")) {
			Tweet tweet = new Tweet(st, SocialMedia.getCurrent_user().username);
			SocialMedia.getCurrent_user().getMenu().getPage().getTweets().add(tweet);
			SocialMedia.getTweets().add(tweet);
			try {
				Logger.info("added new tweet: " + tweet.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void addTweetPlus(String st) {
		if (!(st.equals("default") || (!st.contains(".png") && !st.contains(".jpg")))) {
			String text = Controller.getInstance().getTweetText();
			if (text != null && !text.equals("")) {
				Tweet tweet = new Tweet(text, SocialMedia.getCurrent_user().username);
				tweet.setImageAddress(st);
				SocialMedia.getCurrent_user().getMenu().getPage().getTweets().add(tweet);
				SocialMedia.getTweets().add(tweet);
				try {
					Logger.info("added new tweet: " + tweet.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void setNextMyTweet() {
		i++;
		if (i == SocialMedia.getCurrent_user().getMenu().getPage().getTweets().size())
			i = 0;
		MainFrame.getInstance().setMyTweet(SocialMedia.getCurrent_user().getMenu().getPage().getTweets().get(i));
		try {
			Logger.info("watched tweet : "
					+ SocialMedia.getCurrent_user().getMenu().getPage().getTweets().get(i).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setPrevMyTweet() {
		i--;
		if (i == -1)
			i = SocialMedia.getCurrent_user().getMenu().getPage().getTweets().size() - 1;
		MainFrame.getInstance().setMyTweet(SocialMedia.getCurrent_user().getMenu().getPage().getTweets().get(i));
		try {
			Logger.info("watched tweet : "
					+ SocialMedia.getCurrent_user().getMenu().getPage().getTweets().get(i).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showMyTweets() {
		if (SocialMedia.getCurrent_user().getMenu().getPage().getTweets().size() == 0) {
			MainFrame.getInstance().emptyMyTweetsMessage();
		} else {
			MainFrame.getInstance().createMyTweetsPanel();
			MainFrame.getInstance().setMyTweet(SocialMedia.getCurrent_user().getMenu().getPage().getTweets().get(0));
		}
		try {
			Logger.info("watched all tweets");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void reSet() {
		i1 = 0;
		i2 = 0;
		i3 = 0;
		i4 = 0;
	}

	public static void showNewsPanel() {
		if (SocialMedia.getCurrent_user().getMenu().getPage().getNews().size() == 0) {
			MainFrame.getInstance().showMessageDialog("no more news");
		} else {
			reSet();
			MainFrame.getInstance().showNewsPanel();
			for (String st : SocialMedia.getCurrent_user().getMenu().getPage().getNews())
				MainFrame.getInstance().println(st, Color.YELLOW);
		}
		try {
			Logger.info("watched news");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showFollowersPanel() {
		if (SocialMedia.getCurrent_user().getMenu().getPage().getFollowers().size() == 0) {
			MainFrame.getInstance().showMessageDialog("threr is no follower");
		} else {
			reSet();
			MainFrame.getInstance().showFollowersPanel();
			MainFrame.getInstance().setFollowersPanelUserInfo(
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getPage().getFollowers().get(0)));

		}
		try {
			Logger.info("watched followers");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void nextFollowersPanel() {
		i1++;
		if (i1 == SocialMedia.getCurrent_user().getMenu().getPage().getFollowers().size())
			i1 = 0;
		MainFrame.getInstance().setFollowersPanelUserInfo(
				SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getPage().getFollowers().get(i1)));
		try {
			Logger.info("watched user: " + SocialMedia.getCurrent_user().getMenu().getPage().getFollowers().get(i1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void prevFollowersPanel() {
		i1--;
		if (i1 == -1)
			i1 = SocialMedia.getCurrent_user().getMenu().getPage().getFollowers().size() - 1;
		MainFrame.getInstance().setFollowersPanelUserInfo(
				SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getPage().getFollowers().get(i1)));
		try {
			Logger.info("watched user: " + SocialMedia.getCurrent_user().getMenu().getPage().getFollowers().get(i1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showFollowingsPanel() {
		if (SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().size() == 0) {
			MainFrame.getInstance().showMessageDialog("there is no following user");
		} else {
			reSet();
			MainFrame.getInstance().showFollowingsPanel();
			MainFrame.getInstance().setFollowingsPanelUserInfo(
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().get(0)));

		}
		try {
			Logger.info("watched followings");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void nextFollowingsPanel() {
		i2++;
		if (i2 == SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().size())
			i2 = 0;
		MainFrame.getInstance().setFollowingsPanelUserInfo(
				SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().get(i2)));
		try {
			Logger.info("watched user: " + SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().get(i2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void prevFollowingsPanel() {
		i2--;
		if (i2 == -1)
			i2 = SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().size() - 1;
		MainFrame.getInstance().setFollowingsPanelUserInfo(
				SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().get(i2)));
		try {
			Logger.info("watched user: " + SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().get(i2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void unFollow() {
		String st = SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().get(i2);
		User u = SocialMedia.findUser(st);
		u.getMenu().getPage().getFollowers().remove(SocialMedia.getCurrent_user().username);
		SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().remove(st);
		u.getMenu().getPage().getNews().add(SocialMedia.getCurrent_user().username + " finished following you");
		reSet();
		if (SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().size() == 0)
			MainFrame.getInstance().notShowPanels();
		else
			MainFrame.getInstance().setFollowingsPanelUserInfo(
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getPage().getFollowings().get(0)));
		try {
			Logger.info("finished following " + u.username);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void showBlackListPanel() {
		if (SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().size() == 0) {
			MainFrame.getInstance().showMessageDialog("threr is no user");
		} else {
			reSet();
			MainFrame.getInstance().showBlackListPanel();
			MainFrame.getInstance().setBlackListPanelUserInfo(
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().get(0)));
		}
		try {
			Logger.info("watched black list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void unBlock() {
		String st = SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().get(i3);
		User u = SocialMedia.findUser(st);
		u.getMenu().getPage().getNews().add(SocialMedia.getCurrent_user().username + " has unblocked you");
		SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().remove(st);
		reSet();
		if (SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().size() == 0)
			MainFrame.getInstance().notShowPanels();
		else
			MainFrame.getInstance().setBlackListPanelUserInfo(
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().get(0)));
		try {
			Logger.info("unblocked " + u.username);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void nextBlackListPanel() {
		i3++;
		if (i3 == SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().size())
			i3 = 0;
		MainFrame.getInstance().setBlackListPanelUserInfo(
				SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().get(i3)));
		try {
			Logger.info("watched user: " + SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().get(i3));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void prevBlackListPanel() {
		i3--;
		if (i3 == -1)
			i3 = SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().size() - 1;
		MainFrame.getInstance().setBlackListPanelUserInfo(
				SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().get(i3)));
		try {
			Logger.info("watched user: " + SocialMedia.getCurrent_user().getMenu().getPage().getBlacklist().get(i3));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showRequestsPanel() {
		if (SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().size() == 0) {
			MainFrame.getInstance().showMessageDialog("threr is no request");
		} else {
			reSet();
			MainFrame.getInstance().showRequestsPanel();
			MainFrame.getInstance().setRequestsPanelUserInfo(SocialMedia.findUser(
					SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().get(0).getUsername()));
		}
		try {
			Logger.info("watched black list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void accept() {
		Request r = SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().get(i4);
		User u = SocialMedia.findUser(r.getUsername());
		u.getMenu().getPage().getNews().add(SocialMedia.getCurrent_user().username + " accepted your request");
		SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().remove(r);
		SocialMedia.getCurrent_user().getMenu().getPage().getFollowers().add(r.getUsername());
		u.getMenu().getPage().getFollowings().add(SocialMedia.getCurrent_user().username);
		reSet();
		if (SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().size() == 0)
			MainFrame.getInstance().notShowPanels();
		else
			MainFrame.getInstance().setRequestsPanelUserInfo(SocialMedia.findUser(
					SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().get(0).getUsername()));
		try {
			Logger.info(" accepted " + u.username + " request");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void reject() {
		Request r = SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().get(i4);
		User u = SocialMedia.findUser(r.getUsername());
		u.getMenu().getPage().getNews().add(SocialMedia.getCurrent_user().username + " rejectted your request");
		SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().remove(r);
		reSet();
		if (SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().size() == 0)
			MainFrame.getInstance().notShowPanels();
		else
			MainFrame.getInstance().setRequestsPanelUserInfo(SocialMedia.findUser(
					SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().get(0).getUsername()));
		try {
			Logger.info(" rejected " + u.username + " request");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void nextRequestsPanel() {
		i4++;
		if (i4 == SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().size())
			i4 = 0;
		MainFrame.getInstance().setRequestsPanelUserInfo(SocialMedia.findUser(
				SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().get(i4).getUsername()));
		try {
			Logger.info(
					"watched user: " + SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().get(i4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void prevRequestsPanel() {
		i4--;
		if (i4 == -1)
			i4 = SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().size() - 1;
		MainFrame.getInstance().setRequestsPanelUserInfo(SocialMedia.findUser(
				SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().get(i4).getUsername()));
		try {
			Logger.info(
					"watched user: " + SocialMedia.getCurrent_user().getMenu().getPage().getRecivedrequests().get(i4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
