package logic;

import java.io.IOException;
import java.util.ArrayList;

import models.Message;
import models.Tweet;
import models.User;
import panels.MainFrame;

public class TimeLineLogic {
	private static ArrayList<Tweet> tweets;
	private static int i = 0;
	private static int j = 0;

	public static void showTweets() {
		i = 0;
		j = 0;
		tweets = SocialMedia.getTweets();
		if (tweets.size() == 0) {
			MainFrame.getInstance().showMessageDialog("there is no tweet!");
		} else {
			MainFrame.getInstance().showTimeLineTweets();
			MainFrame.getInstance().setTweet(tweets.get(0), SocialMedia.findUser(tweets.get(0).getOwner()));
		}
		try {
			Logger.info("watched time line tweets");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showComments() {
		j = 0;
		if (tweets.get(i).getComments().size() == 0)
			MainFrame.getInstance().showMessageDialog("there is no tweet!");
		else {
			MainFrame.getInstance().showCommentsPanel();
			MainFrame.getInstance().setComment(tweets.get(i).getComments().get(0),
					SocialMedia.findUser(tweets.get(i).getComments().get(0).getOwner()));
		}
		try {
			Logger.info("watched comments");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void nextTimeLineComment() {
		j++;
		if (tweets.get(i).getComments().size() == j)
			j = 0;
		MainFrame.getInstance().setComment(tweets.get(i).getComments().get(j),
				SocialMedia.findUser(tweets.get(i).getComments().get(j).getOwner()));
		try {
			Logger.info("watched comment : " + tweets.get(i).getComments().get(j).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void prevTimeLineComment() {
		j--;
		if (j == -1)
			j = tweets.get(i).getComments().size() - 1;
		MainFrame.getInstance().setComment(tweets.get(i).getComments().get(j),
				SocialMedia.findUser(tweets.get(i).getComments().get(j).getOwner()));
		try {
			Logger.info("watched comment : " + tweets.get(i).getComments().get(j).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void nextTimeLineTweet() {
		i++;
		if (i == tweets.size())
			i = 0;
		MainFrame.getInstance().setTweet(tweets.get(i), SocialMedia.findUser(tweets.get(i).getOwner()));
		try {
			Logger.info("watched tweet : " + tweets.get(i).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainFrame.getInstance().notShowCommentsPanel();
		j = 0;
	}

	public static void prevTimeLineTweet() {
		i--;
		if (i == -1)
			i = tweets.size() - 1;
		MainFrame.getInstance().setTweet(tweets.get(i), SocialMedia.findUser(tweets.get(i).getOwner()));
		try {
			Logger.info("watched tweet : " + tweets.get(i).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		j = 0;
		MainFrame.getInstance().notShowCommentsPanel();
	}

	public static void addComment(String st) {
		if (st != null && !st.equals("")) {
			Tweet t = new Tweet(st, SocialMedia.getCurrent_user().username);
			tweets.get(i).getComments().add(t);
			try {
				Logger.info("added new comment: " + t.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void saveTweet() {
		Message m = new Message(tweets.get(i).getMessage());
		m.setImageAddress(tweets.get(i).getImageAddress());
		SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().add(m);
		MainFrame.getInstance()
				.showMessageDialog("'" + m.getMessage() + "'" + " has been saved in your saved messages");
		try {
			Logger.info("saved a message: " + m.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveComment() {
		Message m = new Message(tweets.get(i).getComments().get(j).getMessage());
		SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().add(m);
		MainFrame.getInstance().showMessageDialog(m.getMessage() + " has been saved in your saved messages");
		try {
			Logger.info("saved a message: " + m.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void reTweet() {
		Tweet t = new Tweet(tweets.get(i).getMessage());
		t.setOwner(SocialMedia.getCurrent_user().username);
		t.setImageAddress(tweets.get(i).getImageAddress());
		t.setComments(new ArrayList<Tweet>());
		SocialMedia.getCurrent_user().getMenu().getPage().getTweets().add(t);
		tweets.get(i).setRetweets(tweets.get(i).getRetweets() + 1);
		try {
			Logger.info("retweeted: " + t.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainFrame.getInstance().showMessageDialog("'" + t.getMessage() + "'" + " has been retweeted");
		MainFrame.getInstance().setRetweetLabel(tweets.get(i).getRetweets());
	}

	public static void reComment() {
		Tweet t = new Tweet(tweets.get(i).getComments().get(j).getMessage());
		t.setOwner(SocialMedia.getCurrent_user().username);
		t.setImageAddress(tweets.get(i).getComments().get(j).getImageAddress());
		t.setComments(new ArrayList<Tweet>());
		SocialMedia.getCurrent_user().getMenu().getPage().getTweets().add(t);
		tweets.get(i).getComments().get(j).setRetweets(tweets.get(i).getComments().get(j).getRetweets() + 1);
		try {
			Logger.info("retweeted: " + t.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainFrame.getInstance().showMessageDialog("'" + t.getMessage() + "'" + " has been retweeted");
		MainFrame.getInstance().setRecommentLabel(tweets.get(i).getComments().get(j).getRetweets());
	}

	public static void likeTweet() {
		tweets.get(i).setLovers(tweets.get(i).getLovers() + 1);
		try {
			Logger.info("liked: " + tweets.get(i).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainFrame.getInstance().showMessageDialog("you liked this tweet");
		MainFrame.getInstance().setLikeTweetLabel(tweets.get(i).getLovers());
	}

	public static void likeComment() {
		tweets.get(i).getComments().get(j).setLovers(tweets.get(i).getComments().get(j).getLovers() + 1);
		try {
			Logger.info("liked: " + tweets.get(i).getComments().get(j).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainFrame.getInstance().showMessageDialog("you liked this tweet");
		MainFrame.getInstance().setLikeCommentLabel(tweets.get(i).getComments().get(j).getLovers());
	}

	public static void spamTweet() {
		tweets.get(i).setSpam(tweets.get(i).getSpam() + 1);
		MainFrame.getInstance().showMessageDialog("report has been sent");
		try {
			Logger.info("reported: " + tweets.get(i).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		deleteTweet();
	}

	public static void spamComment() {
		tweets.get(i).getComments().get(j).setSpam(tweets.get(i).getComments().get(j).getSpam() + 1);
		MainFrame.getInstance().showMessageDialog("report has been sent");
		try {
			Logger.info("reported: " + tweets.get(i).getComments().get(j).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		deleteComment();
	}

	private static void deleteTweet() {
		if (tweets.get(i).getSpam() >= 5) {
			try {
				Logger.info("tweet: " + tweets.get(i).getMessage() + " has been deleted");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Tweet t = tweets.get(i);
			User user = SocialMedia.findUser(tweets.get(i).getOwner());
			user.getMenu().getPage().getTweets().remove(t);
			SocialMedia.getTweets().remove(t);
			tweets.remove(t);
			MainFrame.getInstance().showMessageDialog("tweet was deleted baecuse of spam reports");
			if (tweets.size() > 0) {
				i = 0;
				j = 0;
				MainFrame.getInstance().setTweet(tweets.get(0), SocialMedia.findUser(tweets.get(0).getOwner()));
				MainFrame.getInstance().notShowCommentsPanel();
			} else {
				i = 0;
				j = 0;
				MainFrame.getInstance().notShowCommentsPanel();
				MainFrame.getInstance().notShowTweetsPanel();
			}
		}
	}

	private static void deleteComment() {
		if (tweets.get(i).getComments().get(j).getSpam() >= 5) {
			try {
				Logger.info("tweet: " + tweets.get(i).getComments().get(j).getMessage() + " has been deleted");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Tweet t = tweets.get(i).getComments().get(j);
			tweets.get(i).getComments().remove(t);
			MainFrame.getInstance().showMessageDialog("comment was deleted baecuse of spam reports");
			if (tweets.get(i).getComments().size() > 0) {
				j = 0;
				MainFrame.getInstance().setComment(tweets.get(i).getComments().get(0),
						SocialMedia.findUser(tweets.get(i).getComments().get(0).getOwner()));

			} else {
				MainFrame.getInstance().notShowCommentsPanel();
				j = 0;
			}

		}
	}
}
