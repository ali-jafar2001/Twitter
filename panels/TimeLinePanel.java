package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Controller;
import models.Tweet;
import models.User;
import util.ExplorerConfig;
import util.TimeLineConfig;

public class TimeLinePanel extends JPanel implements ActionListener {
	private TimeLineConfig config;
	private Image background;
	private JButton back, tweets;
	private TweetsPanel tweetsPanel;
	private CommentsPanel commentsPanel;

	public void reset() {
		this.tweetsPanel.setVisible(false);
		this.commentsPanel.setVisible(false);
	}

	public void notShowCommentsPanel() {
		this.commentsPanel.setVisible(false);
	}

	public void notShowTweetsPanel() {
		this.tweetsPanel.setVisible(false);
	}

	public void setRetweetLabel(int i) {
		tweetsPanel.setRetweetLabel(i);
	}

	public void setRecommentLabel(int i) {
		commentsPanel.setRetweetLabel(i);
	}

	public void setLikeTweetLabel(int i) {
		tweetsPanel.setLikeLabel(i);
	}

	public void setLikeCommentLabel(int i) {
		commentsPanel.setLikeLabel(i);
	}

	public void setTweet(Tweet tweet, User user) {
		this.tweetsPanel.setTweet(tweet, user);
	}

	public void setComment(Tweet tweet, User user) {
		this.commentsPanel.setComment(tweet, user);
	}

	public void showTweetsPanel() {
		this.tweetsPanel.setVisible(true);
	}

	public void showCommentsPanel() {
		this.commentsPanel.setVisible(true);
	}

	public TimeLinePanel() {
		super();
		this.setLayout(null);
		init();
		this.add(back);
		this.add(tweets);
		this.add(tweetsPanel);
		this.add(commentsPanel);
	}

	private void init() {
		config = new TimeLineConfig("TIMELINE_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getBcImage());

		Font f = new Font("serif", Font.PLAIN, 18);
		back = new JButton(new ImageIcon(config.getBtImage()));
		back.setBounds(20, 710, config.getBtWidth(), config.getBtHeight() - 10);
		back.setText("Back");
		back.setHorizontalTextPosition(JButton.CENTER);
		back.setVerticalTextPosition(JButton.CENTER);
		back.setForeground(Color.white);
		back.setFont(f);
		back.addActionListener(this);

		tweets = new JButton(new ImageIcon(config.getBtImage()));
		tweets.setBounds(220, 20, config.getBtWidth(), config.getBtHeight() - 10);
		tweets.setText("Tweets");
		tweets.setHorizontalTextPosition(JButton.CENTER);
		tweets.setVerticalTextPosition(JButton.CENTER);
		tweets.setForeground(Color.white);
		tweets.setFont(f);
		tweets.addActionListener(this);

		tweetsPanel = new TweetsPanel();
		tweetsPanel.setBounds(50, 80, 450, 500);
		tweetsPanel.setVisible(false);

		commentsPanel = new CommentsPanel();
		commentsPanel.setBounds(700, 130, 450, 420);
		commentsPanel.setVisible(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, -300, -300, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			Controller.getInstance().listen("timeLineToMenu");
		} else if (e.getSource() == tweets) {
			Controller.getInstance().listen("timeLineTweets");
		}
	}

}

class TweetsPanel extends JPanel implements ActionListener {
	private TimeLineConfig config;
	private Image background;
	private JButton prev, next, spam, comment, comments, save, doLike, doRetweet;
	private JLabel text, time, like, retweet, profile;

	public void setTweet(Tweet tweet, User user) {
		text.setText(tweet.getMessage());
		time.setText(tweet.getDateandtime().getDateandTime());
		like.setText(String.valueOf(tweet.getLovers()));
		retweet.setText(String.valueOf(tweet.getRetweets()));
		if (tweet.getImageAddress().equals("default")) {
			text.setIcon(null);
			text.setBounds(10, 190, 270, 20);
		} else {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(tweet.getImageAddress()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			text.setBounds(10, 10, 270, 200);
			Image img2 = img.getScaledInstance(text.getWidth(), text.getHeight() - 20, Image.SCALE_SMOOTH);
			text.setIcon(new ImageIcon(img2));
		}
		profile.setText(user.username);
		BufferedImage img = null;
		if (user.getMenu().getPage().getProfileAddress().equals("default")) {
			try {
				img = ImageIO.read(new File("src\\resource\\images\\default.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				img = ImageIO.read(new File(user.getMenu().getPage().getProfileAddress()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Image img2 = img.getScaledInstance(profile.getWidth() - 50, profile.getHeight(), Image.SCALE_SMOOTH);
		profile.setIcon(new ImageIcon(img2));
	}

	public TweetsPanel() {
		super();
		this.setLayout(null);
		init();
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		this.add(next);
		this.add(prev);
		this.add(text);
		this.add(like);
		this.add(retweet);
		this.add(time);
		this.add(profile);
		this.add(comment);
		this.add(comments);
		this.add(doLike);
		this.add(doRetweet);
		this.add(save);
		this.add(spam);
	}

	public void setRetweetLabel(int i) {
		retweet.setText(String.valueOf(i));
	}

	public void setLikeLabel(int i) {
		like.setText(String.valueOf(i));
	}

	private void init() {
		config = new TimeLineConfig("TIMELINE_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		Font f = new Font("serif", Font.PLAIN, 18);

		prev = new JButton(new ImageIcon(config.getBtImage()));
		prev.setBounds(20, 420, 100, 40);
		prev.setText("Previous");
		prev.setHorizontalTextPosition(JButton.CENTER);
		prev.setVerticalTextPosition(JButton.CENTER);
		prev.setForeground(Color.white);
		prev.setFont(f);
		prev.addActionListener(this);

		next = new JButton(new ImageIcon(config.getBtImage()));
		next.setText("Next");
		next.setBounds(130, 420, 100, 40);
		next.setHorizontalTextPosition(JButton.CENTER);
		next.setVerticalTextPosition(JButton.CENTER);
		next.setForeground(Color.white);
		next.setFont(f);
		next.addActionListener(this);

		comment = new JButton(new ImageIcon(config.getBtImage()));
		comment.setText("Comment");
		comment.setBounds(300, 20, 130, 40);
		comment.setHorizontalTextPosition(JButton.CENTER);
		comment.setVerticalTextPosition(JButton.CENTER);
		comment.setForeground(Color.white);
		comment.setFont(f);
		comment.addActionListener(this);

		comments = new JButton(new ImageIcon(config.getBtImage()));
		comments.setText("Comments");
		comments.setBounds(300, 70, 130, 40);
		comments.setHorizontalTextPosition(JButton.CENTER);
		comments.setVerticalTextPosition(JButton.CENTER);
		comments.setForeground(Color.white);
		comments.setFont(f);
		comments.addActionListener(this);

		doLike = new JButton(new ImageIcon(config.getBtImage()));
		doLike.setText("Like");
		doLike.setBounds(300, 120, 130, 40);
		doLike.setHorizontalTextPosition(JButton.CENTER);
		doLike.setVerticalTextPosition(JButton.CENTER);
		doLike.setForeground(Color.white);
		doLike.setFont(f);
		doLike.addActionListener(this);

		doRetweet = new JButton(new ImageIcon(config.getBtImage()));
		doRetweet.setText("Retweet");
		doRetweet.setBounds(300, 170, 130, 40);
		doRetweet.setHorizontalTextPosition(JButton.CENTER);
		doRetweet.setVerticalTextPosition(JButton.CENTER);
		doRetweet.setForeground(Color.white);
		doRetweet.setFont(f);
		doRetweet.addActionListener(this);

		spam = new JButton(new ImageIcon(config.getBtImage()));
		spam.setText("Spam");
		spam.setBounds(270, 220, 130, 40);
		spam.setHorizontalTextPosition(JButton.CENTER);
		spam.setVerticalTextPosition(JButton.CENTER);
		spam.setForeground(Color.white);
		spam.setFont(f);
		spam.addActionListener(this);

		save = new JButton(new ImageIcon(config.getBtImage()));
		save.setText("Save");
		save.setBounds(270, 270, 130, 40);
		save.setHorizontalTextPosition(JButton.CENTER);
		save.setVerticalTextPosition(JButton.CENTER);
		save.setForeground(Color.white);
		save.setFont(f);
		save.addActionListener(this);

		text = new JLabel();
		text.setFont(f);
		text.setForeground(Color.white);
		text.setHorizontalTextPosition(JLabel.CENTER);
		text.setVerticalTextPosition(JLabel.BOTTOM);
		text.setBounds(10, 10, 270, 200);
		text.setIconTextGap(0);
		text.setBorder(BorderFactory.createLineBorder(Color.white));

		profile = new JLabel();
		profile.setFont(new Font("serif", Font.PLAIN, 18));
		profile.setForeground(Color.white);
		profile.setHorizontalTextPosition(JLabel.RIGHT);
		profile.setVerticalTextPosition(JLabel.CENTER);
		profile.setBounds(20, 230, 190, 80);
		profile.setIconTextGap(0);
		profile.setBorder(BorderFactory.createLineBorder(Color.white));

		like = new JLabel();
		like.setIcon(new ImageIcon(config.getLikeImage()));
		like.setFont(f);
		like.setForeground(Color.white);
		like.setHorizontalTextPosition(JLabel.RIGHT);
		like.setVerticalTextPosition(JLabel.CENTER);
		like.setBounds(20, 340, 80, 30);
		like.setIconTextGap(0);
		like.setBorder(BorderFactory.createLineBorder(Color.white));

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(config.getLikeImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image img2 = img.getScaledInstance(like.getWidth() - 40, like.getHeight(), Image.SCALE_SMOOTH);
		like.setIcon(new ImageIcon(img2));

		retweet = new JLabel();
		retweet.setIcon(new ImageIcon(config.getLikeImage()));
		retweet.setFont(f);
		retweet.setForeground(Color.white);
		retweet.setHorizontalTextPosition(JLabel.RIGHT);
		retweet.setVerticalTextPosition(JLabel.CENTER);
		retweet.setBounds(140, 340, 80, 30);
		retweet.setIconTextGap(0);
		retweet.setBorder(BorderFactory.createLineBorder(Color.white));

		img = null;
		try {
			img = ImageIO.read(new File(config.getRetweetImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img2 = img.getScaledInstance(like.getWidth() - 40, like.getHeight(), Image.SCALE_SMOOTH);
		retweet.setIcon(new ImageIcon(img2));

		time = new JLabel();
		time.setFont(f);
		time.setForeground(Color.white);
		time.setHorizontalTextPosition(JLabel.RIGHT);
		time.setVerticalTextPosition(JLabel.CENTER);
		time.setBounds(20, 380, 220, 30);
		time.setIconTextGap(0);
		time.setBorder(BorderFactory.createLineBorder(Color.white));
		img = null;
		try {
			img = ImageIO.read(new File(config.getClockImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img2 = img.getScaledInstance(like.getWidth() - 50, like.getHeight(), Image.SCALE_SMOOTH);
		time.setIcon(new ImageIcon(img2));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			Controller.getInstance().listen("timeLineNextTweet");
		} else if (e.getSource() == prev) {
			Controller.getInstance().listen("timeLinePrevTweet");
		} else if (e.getSource() == comment) {
			Controller.getInstance().listen("commentTimeLine");
		} else if (e.getSource() == comments) {
			Controller.getInstance().listen("commentsTimeLine");
		} else if (e.getSource() == save) {
			Controller.getInstance().listen("saveTweet");
		} else if (e.getSource() == doRetweet) {
			Controller.getInstance().listen("reTweet");
		} else if (e.getSource() == doLike) {
			Controller.getInstance().listen("likeTweet");
		} else if (e.getSource() == spam) {
			Controller.getInstance().listen("spamTweet");
		}
	}

}

class CommentsPanel extends JPanel implements ActionListener {
	private TimeLineConfig config;
	private Image background;
	private JButton prev, next, spam, save, doLike, doRetweet;
	private JLabel text, time, like, retweet, profile;

	public CommentsPanel() {
		super();
		this.setLayout(null);
		init();
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		this.add(next);
		this.add(prev);
		this.add(text);
		this.add(like);
		this.add(retweet);
		this.add(time);
		this.add(profile);
		this.add(doLike);
		this.add(doRetweet);
		this.add(save);
		this.add(spam);
	}

	public void setRetweetLabel(int i) {
		retweet.setText(String.valueOf(i));
	}

	public void setLikeLabel(int i) {
		like.setText(String.valueOf(i));
	}

	private void init() {
		config = new TimeLineConfig("TIMELINE_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		Font f = new Font("serif", Font.PLAIN, 18);

		prev = new JButton(new ImageIcon(config.getBtImage()));
		prev.setBounds(20, 320, 100, 40);
		prev.setText("Previous");
		prev.setHorizontalTextPosition(JButton.CENTER);
		prev.setVerticalTextPosition(JButton.CENTER);
		prev.setForeground(Color.white);
		prev.setFont(f);
		prev.addActionListener(this);

		next = new JButton(new ImageIcon(config.getBtImage()));
		next.setText("Next");
		next.setBounds(130, 320, 100, 40);
		next.setHorizontalTextPosition(JButton.CENTER);
		next.setVerticalTextPosition(JButton.CENTER);
		next.setForeground(Color.white);
		next.setFont(f);
		next.addActionListener(this);

		doLike = new JButton(new ImageIcon(config.getBtImage()));
		doLike.setText("Like");
		doLike.setBounds(300, 80, 130, 40);
		doLike.setHorizontalTextPosition(JButton.CENTER);
		doLike.setVerticalTextPosition(JButton.CENTER);
		doLike.setForeground(Color.white);
		doLike.setFont(f);
		doLike.addActionListener(this);

		doRetweet = new JButton(new ImageIcon(config.getBtImage()));
		doRetweet.setText("Retweet");
		doRetweet.setBounds(300, 130, 130, 40);
		doRetweet.setHorizontalTextPosition(JButton.CENTER);
		doRetweet.setVerticalTextPosition(JButton.CENTER);
		doRetweet.setForeground(Color.white);
		doRetweet.setFont(f);
		doRetweet.addActionListener(this);

		spam = new JButton(new ImageIcon(config.getBtImage()));
		spam.setText("Spam");
		spam.setBounds(270, 180, 130, 40);
		spam.setHorizontalTextPosition(JButton.CENTER);
		spam.setVerticalTextPosition(JButton.CENTER);
		spam.setForeground(Color.white);
		spam.setFont(f);
		spam.addActionListener(this);

		save = new JButton(new ImageIcon(config.getBtImage()));
		save.setText("Save");
		save.setBounds(270, 230, 130, 40);
		save.setHorizontalTextPosition(JButton.CENTER);
		save.setVerticalTextPosition(JButton.CENTER);
		save.setForeground(Color.white);
		save.setFont(f);
		save.addActionListener(this);

		text = new JLabel();
		text.setFont(f);
		text.setForeground(Color.white);
		text.setHorizontalTextPosition(JLabel.CENTER);
		text.setVerticalTextPosition(JLabel.BOTTOM);
		text.setBounds(10, 10, 390, 30);
		text.setIconTextGap(0);
		text.setBorder(BorderFactory.createLineBorder(Color.white));

		profile = new JLabel();
		profile.setFont(new Font("serif", Font.PLAIN, 18));
		profile.setForeground(Color.white);
		profile.setHorizontalTextPosition(JLabel.RIGHT);
		profile.setVerticalTextPosition(JLabel.CENTER);
		profile.setBounds(20, 90, 190, 80);
		profile.setIconTextGap(0);
		profile.setBorder(BorderFactory.createLineBorder(Color.white));

		like = new JLabel();
		like.setIcon(new ImageIcon(config.getLikeImage()));
		like.setFont(f);
		like.setForeground(Color.white);
		like.setHorizontalTextPosition(JLabel.RIGHT);
		like.setVerticalTextPosition(JLabel.CENTER);
		like.setBounds(20, 200, 80, 30);
		like.setIconTextGap(0);
		like.setBorder(BorderFactory.createLineBorder(Color.white));

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(config.getLikeImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image img2 = img.getScaledInstance(like.getWidth() - 40, like.getHeight(), Image.SCALE_SMOOTH);
		like.setIcon(new ImageIcon(img2));

		retweet = new JLabel();
		retweet.setIcon(new ImageIcon(config.getLikeImage()));
		retweet.setFont(f);
		retweet.setForeground(Color.white);
		retweet.setHorizontalTextPosition(JLabel.RIGHT);
		retweet.setVerticalTextPosition(JLabel.CENTER);
		retweet.setBounds(140, 200, 80, 30);
		retweet.setIconTextGap(0);
		retweet.setBorder(BorderFactory.createLineBorder(Color.white));

		img = null;
		try {
			img = ImageIO.read(new File(config.getRetweetImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img2 = img.getScaledInstance(like.getWidth() - 40, like.getHeight(), Image.SCALE_SMOOTH);
		retweet.setIcon(new ImageIcon(img2));

		time = new JLabel();
		time.setFont(f);
		time.setForeground(Color.white);
		time.setHorizontalTextPosition(JLabel.RIGHT);
		time.setVerticalTextPosition(JLabel.CENTER);
		time.setBounds(20, 240, 220, 30);
		time.setIconTextGap(0);
		time.setBorder(BorderFactory.createLineBorder(Color.white));
		img = null;
		try {
			img = ImageIO.read(new File(config.getClockImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img2 = img.getScaledInstance(like.getWidth() - 50, like.getHeight(), Image.SCALE_SMOOTH);
		time.setIcon(new ImageIcon(img2));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			Controller.getInstance().listen("nextTimeLineComment");
		} else if (e.getSource() == prev) {
			Controller.getInstance().listen("prevTimeLineComment");
		} else if (e.getSource() == save) {
			Controller.getInstance().listen("saveComment");
		} else if (e.getSource() == doRetweet) {
			Controller.getInstance().listen("reComment");
		} else if (e.getSource() == doLike) {
			Controller.getInstance().listen("likeComment");
		} else if (e.getSource() == spam) {
			Controller.getInstance().listen("spamComment");
		}
	}

	public void setComment(Tweet tweet, User user) {
		text.setText(tweet.getMessage());
		time.setText(tweet.getDateandtime().getDateandTime());
		like.setText(String.valueOf(tweet.getLovers()));
		retweet.setText(String.valueOf(tweet.getRetweets()));
		profile.setText(user.username);
		BufferedImage img = null;
		if (user.getMenu().getPage().getProfileAddress().equals("default")) {
			try {
				img = ImageIO.read(new File("src\\resource\\images\\default.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				img = ImageIO.read(new File(user.getMenu().getPage().getProfileAddress()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Image img2 = img.getScaledInstance(profile.getWidth() - 50, profile.getHeight(), Image.SCALE_SMOOTH);
		profile.setIcon(new ImageIcon(img2));
	}

}
