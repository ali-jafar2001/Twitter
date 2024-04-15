package panels;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import logic.Controller;
import logic.SocialMedia;
import models.Tweet;
import models.User;
import util.PageConfig;

public class PagePanel extends JPanel implements ActionListener {
	private PageConfig config;
	private JScrollPane personalScrollPane;
	private PersonalInfoPanel personalInfoPanel;
	private Image background, prof;
	private JButton back, setProfile, tweet, tweetPlus, MyTweets;
	private JLabel profile;
	private MyTweetsPanel myTweetsPanel;
	private JButton followers, followings, blackList, news, requests;
	private followersPanel followersPanel;
	private followingsPanel followingsPanel;
	private blackListPanel blackListPanel;
	private newsPanel newsPanel;
	private requestsPanel requestsPanel;

	public void reset() {
		myTweetsPanel.setVisible(false);
		followersPanel.setVisible(false);
		followingsPanel.setVisible(false);
		blackListPanel.setVisible(false);
		newsPanel.setVisible(false);
		requestsPanel.setVisible(false);
		newsPanel.reset();
		setProfilePictue();
		personalInfoPanel.reset();
	}

	public PagePanel() {
		super();
		this.setLayout(null);
		init();
		this.add(back);
		this.add(personalScrollPane);
		this.add(setProfile);
		this.add(profile);
		this.add(tweet);
		this.add(tweetPlus);
		this.add(MyTweets);
		this.add(myTweetsPanel);
		this.add(followers);
		this.add(followings);
		this.add(news);
		this.add(requests);
		this.add(blackList);
		this.add(newsPanel);
		this.add(followersPanel);
		this.add(followingsPanel);
		this.add(blackListPanel);
		this.add(requestsPanel);
	}

	public void setFollowersPanelUserInfo(User user) {
		followersPanel.setUserInfo(user);
	}

	public void setFollowingsPanelUserInfo(User user) {
		followingsPanel.setUserInfo(user);
	}

	public void setBlackListPanelUserInfo(User user) {
		blackListPanel.setUserInfo(user);
	}

	public void setRequestsPanelUserInfo(User user) {
		requestsPanel.setUserInfo(user);
	}

	public void println(String text, Color color) {
		newsPanel.println(text, color);
	}

	public void notShowPanels() {
		blackListPanel.setVisible(false);
		followersPanel.setVisible(false);
		followingsPanel.setVisible(false);
		requestsPanel.setVisible(false);
		newsPanel.setVisible(false);
	}

	public void showNewsPanel() {
		blackListPanel.setVisible(false);
		followersPanel.setVisible(false);
		followingsPanel.setVisible(false);
		requestsPanel.setVisible(false);
		newsPanel.setVisible(true);
	}

	public void showFollowersPanel() {
		blackListPanel.setVisible(false);
		followersPanel.setVisible(true);
		followingsPanel.setVisible(false);
		requestsPanel.setVisible(false);
		newsPanel.setVisible(false);
	}

	public void showFollowingsPanel() {
		blackListPanel.setVisible(false);
		followersPanel.setVisible(false);
		followingsPanel.setVisible(true);
		requestsPanel.setVisible(false);
		newsPanel.setVisible(false);
	}

	public void showRequestsPanel() {
		blackListPanel.setVisible(false);
		followersPanel.setVisible(false);
		followingsPanel.setVisible(false);
		requestsPanel.setVisible(true);
		newsPanel.setVisible(false);
	}

	public void showBlackListPanel() {
		blackListPanel.setVisible(true);
		followersPanel.setVisible(false);
		followingsPanel.setVisible(false);
		requestsPanel.setVisible(false);
		newsPanel.setVisible(false);
	}

	public void setMyTweet(Tweet tweet) {
		this.myTweetsPanel.setMyTweet(tweet);
	}

	public void createMyTweetsPanel() {
//		this.add(myTweetsPanel);
		myTweetsPanel.setVisible(true);
	}

	private void init() {
		config = new PageConfig("PAGE_CONFIG_FILE");
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

		tweet = new JButton(new ImageIcon(config.getBtImage()));
		tweet.setBounds(1150, 660, config.getBtWidth() - 20, config.getBtHeight() - 10);
		tweet.setText("tweet");
		tweet.setHorizontalTextPosition(JButton.CENTER);
		tweet.setVerticalTextPosition(JButton.CENTER);
		tweet.setForeground(Color.white);
		tweet.setFont(f);
		tweet.addActionListener(this);

		tweetPlus = new JButton(new ImageIcon(config.getBtImage()));
		tweetPlus.setBounds(1150, 710, config.getBtWidth() - 20, config.getBtHeight() - 10);
		tweetPlus.setText("tweet++");
		tweetPlus.setHorizontalTextPosition(JButton.CENTER);
		tweetPlus.setVerticalTextPosition(JButton.CENTER);
		tweetPlus.setForeground(Color.white);
		tweetPlus.setFont(f);
		tweetPlus.addActionListener(this);

		followers = new JButton(new ImageIcon(config.getBtImage()));
		followers.setBounds(10, 110, config.getBtWidth() - 20, config.getBtHeight() - 10);
		followers.setText("followers");
		followers.setHorizontalTextPosition(JButton.CENTER);
		followers.setVerticalTextPosition(JButton.CENTER);
		followers.setForeground(Color.white);
		followers.setFont(f);
		followers.addActionListener(this);

		followings = new JButton(new ImageIcon(config.getBtImage()));
		followings.setBounds(10, 170, config.getBtWidth() - 20, config.getBtHeight() - 10);
		followings.setText("followings");
		followings.setHorizontalTextPosition(JButton.CENTER);
		followings.setVerticalTextPosition(JButton.CENTER);
		followings.setForeground(Color.white);
		followings.setFont(f);
		followings.addActionListener(this);

		blackList = new JButton(new ImageIcon(config.getBtImage()));
		blackList.setBounds(10, 230, config.getBtWidth() - 20, config.getBtHeight() - 10);
		blackList.setText("black list");
		blackList.setHorizontalTextPosition(JButton.CENTER);
		blackList.setVerticalTextPosition(JButton.CENTER);
		blackList.setForeground(Color.white);
		blackList.setFont(f);
		blackList.addActionListener(this);

		news = new JButton(new ImageIcon(config.getBtImage()));
		news.setBounds(10, 290, config.getBtWidth() - 20, config.getBtHeight() - 10);
		news.setText("news");
		news.setHorizontalTextPosition(JButton.CENTER);
		news.setVerticalTextPosition(JButton.CENTER);
		news.setForeground(Color.white);
		news.setFont(f);
		news.addActionListener(this);

		requests = new JButton(new ImageIcon(config.getBtImage()));
		requests.setBounds(10, 350, config.getBtWidth() - 20, config.getBtHeight() - 10);
		requests.setText("requests");
		requests.setHorizontalTextPosition(JButton.CENTER);
		requests.setVerticalTextPosition(JButton.CENTER);
		requests.setForeground(Color.white);
		requests.setFont(f);
		requests.addActionListener(this);

		MyTweets = new JButton(new ImageIcon(config.getBtImage()));
		MyTweets.setBounds(800, 250, config.getBtWidth() - 20, config.getBtHeight() - 10);
		MyTweets.setText("Tweets");
		MyTweets.setHorizontalTextPosition(JButton.CENTER);
		MyTweets.setVerticalTextPosition(JButton.CENTER);
		MyTweets.setForeground(Color.white);
		MyTweets.setFont(f);
		MyTweets.addActionListener(this);

		setProfile = new JButton(new ImageIcon(config.getBtImage()));
		setProfile.setText("set prof pic");
		setProfile.setHorizontalTextPosition(JButton.CENTER);
		setProfile.setVerticalTextPosition(JButton.CENTER);
		setProfile.setForeground(Color.white);
		setProfile.setFont(f);
		setProfile.addActionListener(this);
		setProfile.setBounds(1050, 255, config.getBtWidth() - 20, config.getBtHeight() - 10);

		profile = new JLabel();
		profile.setBounds(1050, 0, 300, 250);
		setProfilePictue();

		personalInfoPanel = new PersonalInfoPanel();
		personalScrollPane = new JScrollPane(personalInfoPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		personalScrollPane.setBounds(1050, 300, 300, 350);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.myTweetsPanel = new MyTweetsPanel();
		this.myTweetsPanel.setBounds(720, 300, 300, 400);
		this.myTweetsPanel.setVisible(false);

		followersPanel = new followersPanel();
		followersPanel.setBounds(200, 200, 400, 500);
		followersPanel.setVisible(false);

		newsPanel = new newsPanel();
		newsPanel.setBounds(200, 200, 400, 500);
		newsPanel.setVisible(false);

		followingsPanel = new followingsPanel();
		followingsPanel.setBounds(200, 200, 400, 500);
		followingsPanel.setVisible(false);

		blackListPanel = new blackListPanel();
		blackListPanel.setBounds(200, 200, 400, 500);
		blackListPanel.setVisible(false);

		requestsPanel = new requestsPanel();
		requestsPanel.setBounds(200, 200, 400, 500);
		requestsPanel.setVisible(false);
	}

	public void setProfilePictue() {
		if (SocialMedia.getCurrent_user().getMenu().getPage().getProfileAddress().equals("default")) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(config.getProfImage()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			prof = img.getScaledInstance(profile.getWidth(), profile.getHeight(), Image.SCALE_SMOOTH);
			profile.setIcon(new ImageIcon(prof));
		} else {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(SocialMedia.getCurrent_user().getMenu().getPage().getProfileAddress()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			prof = img.getScaledInstance(profile.getWidth(), profile.getHeight(), Image.SCALE_SMOOTH);
			profile.setIcon(new ImageIcon(prof));
		}
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
			Controller.getInstance().listen("pageToMenu");
		} else if (e.getSource() == setProfile) {
			Controller.getInstance().listen("setProfile");
		} else if (e.getSource() == tweet) {
			Controller.getInstance().listen("tweet");
		} else if (e.getSource() == tweetPlus) {
			Controller.getInstance().listen("tweetPlus");
		} else if (e.getSource() == MyTweets) {
			Controller.getInstance().listen("showMyTweets");
		} else if (e.getSource() == news) {
			Controller.getInstance().listen("showNewsPanel");
		} else if (e.getSource() == followers) {
			Controller.getInstance().listen("showFollowersPanel");
		} else if (e.getSource() == followings) {
			Controller.getInstance().listen("showFollowingsPanel");
		} else if (e.getSource() == blackList) {
			Controller.getInstance().listen("showBlackListPanel");
		} else if (e.getSource() == requests) {
			Controller.getInstance().listen("showRequestsPanel");
		}
	}

	public void editFname(String st) {
		this.personalInfoPanel.editFname(st);
	}

	public void editLname(String st) {
		this.personalInfoPanel.editLname(st);
	}

	public void editBio(String st) {
		this.personalInfoPanel.editBio(st);
	}

	public void editEmail(String st) {
		this.personalInfoPanel.editEmail(st);
	}

	public void editUsername(String st) {
		this.personalInfoPanel.editUsername(st);
	}

	public void editPhone(String st) {
		this.personalInfoPanel.editPhone(st);
	}

	public void editPassword(String st) {
		this.personalInfoPanel.editPassword(st);
	}

	public void editBirth(String st) {
		this.personalInfoPanel.editBirth(st);
	}
}

class PersonalInfoPanel extends JPanel implements ActionListener {
	private PageConfig config;
	private Image background;
	private JLabel fname, lname, bio, mail, phone, password, username, birth;
	private JButton edit_fname, edit_lname, edit_bio, edit_mail, edit_phone, edit_password, edit_username, edit_birth;

	public void reset() {
		fname.setText("first name: " + SocialMedia.getCurrent_user().firstname);
		lname.setText("last name: " + SocialMedia.getCurrent_user().lastname);
		bio.setText("bio: " + SocialMedia.getCurrent_user().bio);
		mail.setText("email: " + SocialMedia.getCurrent_user().Email);
		phone.setText("phone number: " + SocialMedia.getCurrent_user().phonenumber);
		password.setText("password: " + SocialMedia.getCurrent_user().getPassword());
		username.setText("username: " + SocialMedia.getCurrent_user().username);
		birth.setText("birth date: " + SocialMedia.getCurrent_user().birthdate);

	}

	public PersonalInfoPanel() {
		super();
		this.setLayout(new GridLayout(10, 2));
		init();
		this.add(fname);
		this.add(edit_fname);
		this.add(lname);
		this.add(edit_lname);
		this.add(bio);
		this.add(edit_bio);
		this.add(mail);
		this.add(edit_mail);
		this.add(phone);
		this.add(edit_phone);
		this.add(password);
		this.add(edit_password);
		this.add(username);
		this.add(edit_username);
		this.add(birth);
		this.add(edit_birth);
	}

	private void init() {
		config = new PageConfig("PAGE_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		Font f = new Font("serif", Font.PLAIN, 18);

		fname = new JLabel("first name: " + SocialMedia.getCurrent_user().firstname);
		fname.setHorizontalTextPosition(JLabel.CENTER);
		fname.setVerticalTextPosition(JLabel.CENTER);
		fname.setForeground(Color.white);
		fname.setFont(f);

		lname = new JLabel("last name: " + SocialMedia.getCurrent_user().lastname);
		lname.setHorizontalTextPosition(JLabel.CENTER);
		lname.setVerticalTextPosition(JLabel.CENTER);
		lname.setForeground(Color.white);
		lname.setFont(f);

		bio = new JLabel("bio: " + SocialMedia.getCurrent_user().bio);
		bio.setHorizontalTextPosition(JLabel.CENTER);
		bio.setVerticalTextPosition(JLabel.CENTER);
		bio.setForeground(Color.white);
		bio.setFont(f);

		mail = new JLabel("email: " + SocialMedia.getCurrent_user().Email);
		mail.setHorizontalTextPosition(JLabel.CENTER);
		mail.setVerticalTextPosition(JLabel.CENTER);
		mail.setForeground(Color.white);
		mail.setFont(f);

		phone = new JLabel("phone number: " + SocialMedia.getCurrent_user().phonenumber);
		phone.setHorizontalTextPosition(JLabel.CENTER);
		phone.setVerticalTextPosition(JLabel.CENTER);
		phone.setForeground(Color.white);
		phone.setFont(f);

		password = new JLabel("password: " + SocialMedia.getCurrent_user().getPassword());
		password.setHorizontalTextPosition(JLabel.CENTER);
		password.setVerticalTextPosition(JLabel.CENTER);
		password.setForeground(Color.white);
		password.setFont(f);

		username = new JLabel("username: " + SocialMedia.getCurrent_user().username);
		username.setHorizontalTextPosition(JLabel.CENTER);
		username.setVerticalTextPosition(JLabel.CENTER);
		username.setForeground(Color.white);
		username.setFont(f);

		birth = new JLabel("birth date: " + SocialMedia.getCurrent_user().birthdate);
		birth.setHorizontalTextPosition(JLabel.CENTER);
		birth.setVerticalTextPosition(JLabel.CENTER);
		birth.setForeground(Color.white);
		birth.setFont(f);

		edit_fname = new JButton();
		edit_fname.setBackground(new Color(0, 0, 255));
		edit_fname.setText("first name");
		edit_fname.setHorizontalTextPosition(JButton.CENTER);
		edit_fname.setVerticalTextPosition(JButton.CENTER);
		edit_fname.setForeground(Color.white);
		edit_fname.setFont(f);
		edit_fname.addActionListener(this);

		edit_lname = new JButton();
		edit_lname.setBackground(new Color(0, 0, 255));
		edit_lname.setText("last name");
		edit_lname.setHorizontalTextPosition(JButton.CENTER);
		edit_lname.setVerticalTextPosition(JButton.CENTER);
		edit_lname.setForeground(Color.white);
		edit_lname.setFont(f);
		edit_lname.addActionListener(this);

		edit_bio = new JButton();
		edit_bio.setBackground(new Color(0, 0, 255));
		edit_bio.setText("bio");
		edit_bio.setHorizontalTextPosition(JButton.CENTER);
		edit_bio.setVerticalTextPosition(JButton.CENTER);
		edit_bio.setForeground(Color.white);
		edit_bio.setFont(f);
		edit_bio.addActionListener(this);

		edit_mail = new JButton();
		edit_mail.setBackground(new Color(0, 0, 255));
		edit_mail.setText("email");
		edit_mail.setHorizontalTextPosition(JButton.CENTER);
		edit_mail.setVerticalTextPosition(JButton.CENTER);
		edit_mail.setForeground(Color.white);
		edit_mail.setFont(f);
		edit_mail.addActionListener(this);

		edit_phone = new JButton();
		edit_phone.setBackground(new Color(0, 0, 255));
		edit_phone.setText("phone number");
		edit_phone.setHorizontalTextPosition(JButton.CENTER);
		edit_phone.setVerticalTextPosition(JButton.CENTER);
		edit_phone.setForeground(Color.white);
		edit_phone.setFont(f);
		edit_phone.addActionListener(this);

		edit_password = new JButton();
		edit_password.setBackground(new Color(0, 0, 255));
		edit_password.setText("password");
		edit_password.setHorizontalTextPosition(JButton.CENTER);
		edit_password.setVerticalTextPosition(JButton.CENTER);
		edit_password.setForeground(Color.white);
		edit_password.setFont(f);
		edit_password.addActionListener(this);

		edit_username = new JButton();
		edit_username.setBackground(new Color(0, 0, 255));
		edit_username.setText("username");
		edit_username.setHorizontalTextPosition(JButton.CENTER);
		edit_username.setVerticalTextPosition(JButton.CENTER);
		edit_username.setForeground(Color.white);
		edit_username.setFont(f);
		edit_username.addActionListener(this);

		edit_birth = new JButton();
		edit_birth.setBackground(new Color(0, 0, 255));
		edit_birth.setText("birth date");
		edit_birth.setHorizontalTextPosition(JButton.CENTER);
		edit_birth.setVerticalTextPosition(JButton.CENTER);
		edit_birth.setForeground(Color.white);
		edit_birth.setFont(f);
		edit_birth.addActionListener(this);

	}

	public void editFname(String st) {
		fname.setText("first name: " + st);
	}

	public void editLname(String st) {
		lname.setText("last name: " + st);
	}

	public void editBio(String st) {
		bio.setText("bio: " + st);
	}

	public void editEmail(String st) {
		mail.setText("email: " + st);
	}

	public void editPhone(String st) {
		phone.setText("phone: " + st);
	}

	public void editUsername(String st) {
		username.setText("username: " + st);
	}

	public void editPassword(String st) {
		password.setText("password: " + st);
	}

	public void editBirth(String st) {
		birth.setText("birth date: " + st);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == edit_fname) {
			Controller.getInstance().listen("editFirstName");
		} else if (e.getSource() == edit_lname) {
			Controller.getInstance().listen("editLastName");
		} else if (e.getSource() == edit_bio) {
			Controller.getInstance().listen("editBio");
		} else if (e.getSource() == edit_mail) {
			Controller.getInstance().listen("edit_Email");
		} else if (e.getSource() == edit_phone) {
			Controller.getInstance().listen("editPhone");
		} else if (e.getSource() == edit_username) {
			Controller.getInstance().listen("ediUsername");
		} else if (e.getSource() == edit_password) {
			Controller.getInstance().listen("editPassword");
		} else if (e.getSource() == edit_birth) {
			Controller.getInstance().listen("editBirth");
		}
	}
}

class MyTweetsPanel extends JPanel implements ActionListener {
	private PageConfig config;
	private Image background;
	private JButton prev, next;
	private JLabel text, time, like, retweet;

	public MyTweetsPanel() {
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
	}

	private void init() {
		config = new PageConfig("PAGE_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		Font f = new Font("serif", Font.PLAIN, 18);

		prev = new JButton(new ImageIcon(config.getBtImage()));
		prev.setBounds(30, 350, 100, 30);
		prev.setText("previous");
		prev.setHorizontalTextPosition(JButton.CENTER);
		prev.setVerticalTextPosition(JButton.CENTER);
		prev.setForeground(Color.white);
		prev.setFont(f);
		prev.addActionListener(this);

		next = new JButton(new ImageIcon(config.getBtImage()));
		next.setText("next");
		next.setBounds(140, 350, 100, 30);
		next.setHorizontalTextPosition(JButton.CENTER);
		next.setVerticalTextPosition(JButton.CENTER);
		next.setForeground(Color.white);
		next.setFont(f);
		next.addActionListener(this);

		text = new JLabel();
		text.setFont(f);
		text.setForeground(Color.white);
		text.setHorizontalTextPosition(JLabel.CENTER);
		text.setVerticalTextPosition(JLabel.BOTTOM);
		text.setBounds(10, 10, 270, 250);
		text.setIconTextGap(0);
		text.setBorder(BorderFactory.createLineBorder(Color.white));

		like = new JLabel();
		like.setIcon(new ImageIcon(config.getLikeImage()));
		like.setFont(f);
		like.setForeground(Color.white);
		like.setHorizontalTextPosition(JLabel.RIGHT);
		like.setVerticalTextPosition(JLabel.CENTER);
		like.setBounds(10, 270, 80, 30);
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
		retweet.setBounds(190, 270, 80, 30);
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
		time.setBounds(20, 310, 200, 30);
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
			Controller.getInstance().listen("nextMyTweets");
		} else if (e.getSource() == prev) {
			Controller.getInstance().listen("prevMyTweets");
		}
	}

	public void setMyTweet(Tweet tweet) {
		text.setText(tweet.getMessage());
		time.setText(tweet.getDateandtime().getDateandTime());
		like.setText(String.valueOf(tweet.getLovers()));
		retweet.setText(String.valueOf(tweet.getRetweets()));
		if (tweet.getImageAddress().equals("default")) {
			text.setIcon(null);
			text.setBounds(10, 240, 270, 20);
		} else {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(tweet.getImageAddress()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			text.setBounds(10, 10, 270, 250);
			Image img2 = img.getScaledInstance(text.getWidth(), text.getHeight() - 20, Image.SCALE_SMOOTH);
			text.setIcon(new ImageIcon(img2));
		}
	}

}

class followersPanel extends JPanel implements ActionListener {
	private PageConfig config;
	private Image background;
	private JButton prev, next;
	private JLabel prof, followers, followings, tweets, username;

	public void setUserInfo(User user) {
		username.setText("username: " + user.username);
		followers.setText("followers: " + String.valueOf(user.getMenu().getPage().getFollowers().size()));
		followings.setText("followings: " + String.valueOf(user.getMenu().getPage().getFollowings().size()));
		tweets.setText("tweets: " + String.valueOf(user.getMenu().getPage().getTweets().size()));
		BufferedImage img = null;
		if (user.getMenu().getPage().getProfileAddress().equals("default")) {
			try {
				img = ImageIO.read(new File(config.getProfImage()));
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
		Image img2 = img.getScaledInstance(prof.getWidth(), prof.getHeight(), Image.SCALE_SMOOTH);
		prof.setIcon(new ImageIcon(img2));
	}

	public followersPanel() {
		super();
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		init();
		this.add(prof);
		this.add(prev);
		this.add(next);
		this.add(username);
		this.add(followers);
		this.add(followings);
		this.add(tweets);
	}

	private void init() {
		config = new PageConfig("PAGE_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		Font f = new Font("serif", Font.PLAIN, 18);

		prev = new JButton(new ImageIcon(config.getBtImage()));
		prev.setBounds(280, 410, 100, 40);
		prev.setText("previous");
		prev.setHorizontalTextPosition(JButton.CENTER);
		prev.setVerticalTextPosition(JButton.CENTER);
		prev.setForeground(Color.white);
		prev.setFont(f);
		prev.addActionListener(this);

		next = new JButton(new ImageIcon(config.getBtImage()));
		next.setText("next");
		next.setBounds(280, 350, 100, 40);
		next.setHorizontalTextPosition(JButton.CENTER);
		next.setVerticalTextPosition(JButton.CENTER);
		next.setForeground(Color.white);
		next.setFont(f);
		next.addActionListener(this);

		prof = new JLabel();
		prof.setBorder(BorderFactory.createLineBorder(Color.white));
		prof.setBounds(75, 40, 250, 250);

		username = new JLabel("username: ");
		username.setHorizontalTextPosition(JLabel.CENTER);
		username.setVerticalTextPosition(JLabel.CENTER);
		username.setForeground(Color.white);
		username.setFont(f);
		username.setBounds(30, 300, 120, 40);

		followers = new JLabel("followers: ");
		followers.setHorizontalTextPosition(JLabel.CENTER);
		followers.setVerticalTextPosition(JLabel.CENTER);
		followers.setForeground(Color.white);
		followers.setFont(f);
		followers.setBounds(30, 350, 120, 40);

		followings = new JLabel("followings: ");
		followings.setHorizontalTextPosition(JLabel.CENTER);
		followings.setVerticalTextPosition(JLabel.CENTER);
		followings.setForeground(Color.white);
		followings.setFont(f);
		followings.setBounds(30, 400, 120, 40);

		tweets = new JLabel("tweets: ");
		tweets.setHorizontalTextPosition(JLabel.CENTER);
		tweets.setVerticalTextPosition(JLabel.CENTER);
		tweets.setForeground(Color.white);
		tweets.setFont(f);
		tweets.setBounds(30, 450, 120, 40);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			Controller.getInstance().listen("nextFollowersPanel");
		} else if (e.getSource() == prev) {
			Controller.getInstance().listen("prevFollowersPanel");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, this);

	}
}

class followingsPanel extends JPanel implements ActionListener {
	private PageConfig config;
	private Image background;
	private JButton prev, next, unfollow;
	private JLabel prof, followers, followings, tweets, username;

	public void setUserInfo(User user) {
		username.setText("username: " + user.username);
		followers.setText("followers: " + String.valueOf(user.getMenu().getPage().getFollowers().size()));
		followings.setText("followings: " + String.valueOf(user.getMenu().getPage().getFollowings().size()));
		tweets.setText("tweets: " + String.valueOf(user.getMenu().getPage().getTweets().size()));
		BufferedImage img = null;
		if (user.getMenu().getPage().getProfileAddress().equals("default")) {
			try {
				img = ImageIO.read(new File(config.getProfImage()));
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
		Image img2 = img.getScaledInstance(prof.getWidth(), prof.getHeight(), Image.SCALE_SMOOTH);
		prof.setIcon(new ImageIcon(img2));
	}

	public followingsPanel() {
		super();
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		init();
		this.add(prof);
		this.add(prev);
		this.add(next);
		this.add(username);
		this.add(followers);
		this.add(followings);
		this.add(tweets);
		this.add(unfollow);
	}

	private void init() {
		config = new PageConfig("PAGE_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		Font f = new Font("serif", Font.PLAIN, 18);

		prev = new JButton(new ImageIcon(config.getBtImage()));
		prev.setBounds(280, 450, 100, 40);
		prev.setText("previous");
		prev.setHorizontalTextPosition(JButton.CENTER);
		prev.setVerticalTextPosition(JButton.CENTER);
		prev.setForeground(Color.white);
		prev.setFont(f);
		prev.addActionListener(this);

		next = new JButton(new ImageIcon(config.getBtImage()));
		next.setText("next");
		next.setBounds(280, 400, 100, 40);
		next.setHorizontalTextPosition(JButton.CENTER);
		next.setVerticalTextPosition(JButton.CENTER);
		next.setForeground(Color.white);
		next.setFont(f);
		next.addActionListener(this);

		unfollow = new JButton(new ImageIcon(config.getBtImage()));
		unfollow.setText("unfollow");
		unfollow.setBounds(280, 300, 100, 40);
		unfollow.setHorizontalTextPosition(JButton.CENTER);
		unfollow.setVerticalTextPosition(JButton.CENTER);
		unfollow.setForeground(Color.white);
		unfollow.setFont(f);
		unfollow.addActionListener(this);

		prof = new JLabel();
		prof.setBorder(BorderFactory.createLineBorder(Color.white));
		prof.setBounds(75, 40, 250, 250);

		username = new JLabel("username: ");
		username.setHorizontalTextPosition(JLabel.CENTER);
		username.setVerticalTextPosition(JLabel.CENTER);
		username.setForeground(Color.white);
		username.setFont(f);
		username.setBounds(30, 300, 120, 40);

		followers = new JLabel("followers: ");
		followers.setHorizontalTextPosition(JLabel.CENTER);
		followers.setVerticalTextPosition(JLabel.CENTER);
		followers.setForeground(Color.white);
		followers.setFont(f);
		followers.setBounds(30, 350, 120, 40);

		followings = new JLabel("followings: ");
		followings.setHorizontalTextPosition(JLabel.CENTER);
		followings.setVerticalTextPosition(JLabel.CENTER);
		followings.setForeground(Color.white);
		followings.setFont(f);
		followings.setBounds(30, 400, 120, 40);

		tweets = new JLabel("tweets: ");
		tweets.setHorizontalTextPosition(JLabel.CENTER);
		tweets.setVerticalTextPosition(JLabel.CENTER);
		tweets.setForeground(Color.white);
		tweets.setFont(f);
		tweets.setBounds(30, 450, 120, 40);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			Controller.getInstance().listen("nextFollowingsPanel");
		} else if (e.getSource() == prev) {
			Controller.getInstance().listen("prevFollowingsPanel");
		} else if (e.getSource() == unfollow) {
			Controller.getInstance().listen("unFollow");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, this);

	}
}

class blackListPanel extends JPanel implements ActionListener {
	private PageConfig config;
	private Image background;
	private JButton prev, next, unblock;
	private JLabel prof, followers, followings, tweets, username;

	public void setUserInfo(User user) {
		username.setText("username: " + user.username);
		followers.setText("followers: " + String.valueOf(user.getMenu().getPage().getFollowers().size()));
		followings.setText("followings: " + String.valueOf(user.getMenu().getPage().getFollowings().size()));
		tweets.setText("tweets: " + String.valueOf(user.getMenu().getPage().getTweets().size()));
		BufferedImage img = null;
		if (user.getMenu().getPage().getProfileAddress().equals("default")) {
			try {
				img = ImageIO.read(new File(config.getProfImage()));
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
		Image img2 = img.getScaledInstance(prof.getWidth(), prof.getHeight(), Image.SCALE_SMOOTH);
		prof.setIcon(new ImageIcon(img2));
	}

	public blackListPanel() {
		super();
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		init();
		this.add(prof);
		this.add(prev);
		this.add(next);
		this.add(username);
		this.add(followers);
		this.add(followings);
		this.add(tweets);
		this.add(unblock);
	}

	private void init() {
		config = new PageConfig("PAGE_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		Font f = new Font("serif", Font.PLAIN, 18);

		prev = new JButton(new ImageIcon(config.getBtImage()));
		prev.setBounds(280, 450, 100, 40);
		prev.setText("previous");
		prev.setHorizontalTextPosition(JButton.CENTER);
		prev.setVerticalTextPosition(JButton.CENTER);
		prev.setForeground(Color.white);
		prev.setFont(f);
		prev.addActionListener(this);

		next = new JButton(new ImageIcon(config.getBtImage()));
		next.setText("next");
		next.setBounds(280, 400, 100, 40);
		next.setHorizontalTextPosition(JButton.CENTER);
		next.setVerticalTextPosition(JButton.CENTER);
		next.setForeground(Color.white);
		next.setFont(f);
		next.addActionListener(this);

		unblock = new JButton(new ImageIcon(config.getBtImage()));
		unblock.setText("unblock");
		unblock.setBounds(280, 300, 100, 40);
		unblock.setHorizontalTextPosition(JButton.CENTER);
		unblock.setVerticalTextPosition(JButton.CENTER);
		unblock.setForeground(Color.white);
		unblock.setFont(f);
		unblock.addActionListener(this);

		prof = new JLabel();
		prof.setBorder(BorderFactory.createLineBorder(Color.white));
		prof.setBounds(75, 40, 250, 250);

		username = new JLabel("username: ");
		username.setHorizontalTextPosition(JLabel.CENTER);
		username.setVerticalTextPosition(JLabel.CENTER);
		username.setForeground(Color.white);
		username.setFont(f);
		username.setBounds(30, 300, 120, 40);

		followers = new JLabel("followers: ");
		followers.setHorizontalTextPosition(JLabel.CENTER);
		followers.setVerticalTextPosition(JLabel.CENTER);
		followers.setForeground(Color.white);
		followers.setFont(f);
		followers.setBounds(30, 350, 120, 40);

		followings = new JLabel("followings: ");
		followings.setHorizontalTextPosition(JLabel.CENTER);
		followings.setVerticalTextPosition(JLabel.CENTER);
		followings.setForeground(Color.white);
		followings.setFont(f);
		followings.setBounds(30, 400, 120, 40);

		tweets = new JLabel("tweets: ");
		tweets.setHorizontalTextPosition(JLabel.CENTER);
		tweets.setVerticalTextPosition(JLabel.CENTER);
		tweets.setForeground(Color.white);
		tweets.setFont(f);
		tweets.setBounds(30, 450, 120, 40);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			Controller.getInstance().listen("nextBlackListPanel");
		} else if (e.getSource() == prev) {
			Controller.getInstance().listen("prevBlackListPanel");
		} else if (e.getSource() == unblock) {
			Controller.getInstance().listen("unBlock");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, this);

	}
}

class newsPanel extends JPanel implements ActionListener {
	private JTextPane console;
	private JScrollPane scrollpane;
	private StyledDocument document;

	public void reset() {
		try {
			document.remove(0, document.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public newsPanel() {
		super();
		this.setLayout(null);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		init();
		scrollpane.setBounds(0, 0, 400, 500);
		this.add(scrollpane);
		this.setBackground(Color.black);
	}

	private void init() {
		console = new JTextPane();
		console.setEditable(false);
		console.setFont(new Font("Courier New", Font.PLAIN, 18));
		console.setOpaque(false);
		document = console.getStyledDocument();

		scrollpane = new JScrollPane(console);
		scrollpane.setBorder(null);
		scrollpane.setOpaque(false);
		scrollpane.getViewport().setOpaque(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void println(String text, Color color) {
		print(text + "\n", color);
	}

	private void print(String text, Color color) {
		Style style = console.addStyle("Style", null);
		StyleConstants.setForeground(style, color);
		try {
			document.insertString(document.getLength(), text, style);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class requestsPanel extends JPanel implements ActionListener {
	private PageConfig config;
	private Image background;
	private JButton prev, next, accept, reject;
	private JLabel prof, followers, followings, tweets, username;

	public void setUserInfo(User user) {
		username.setText("username: " + user.username);
		followers.setText("followers: " + String.valueOf(user.getMenu().getPage().getFollowers().size()));
		followings.setText("followings: " + String.valueOf(user.getMenu().getPage().getFollowings().size()));
		tweets.setText("tweets: " + String.valueOf(user.getMenu().getPage().getTweets().size()));
		BufferedImage img = null;
		if (user.getMenu().getPage().getProfileAddress().equals("default")) {
			try {
				img = ImageIO.read(new File(config.getProfImage()));
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
		Image img2 = img.getScaledInstance(prof.getWidth(), prof.getHeight(), Image.SCALE_SMOOTH);
		prof.setIcon(new ImageIcon(img2));
	}

	public requestsPanel() {
		super();
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		init();
		this.add(prof);
		this.add(prev);
		this.add(next);
		this.add(username);
		this.add(followers);
		this.add(followings);
		this.add(tweets);
		this.add(accept);
		this.add(reject);
	}

	private void init() {
		config = new PageConfig("PAGE_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		Font f = new Font("serif", Font.PLAIN, 18);

		prev = new JButton(new ImageIcon(config.getBtImage()));
		prev.setBounds(280, 450, 100, 40);
		prev.setText("previous");
		prev.setHorizontalTextPosition(JButton.CENTER);
		prev.setVerticalTextPosition(JButton.CENTER);
		prev.setForeground(Color.white);
		prev.setFont(f);
		prev.addActionListener(this);

		next = new JButton(new ImageIcon(config.getBtImage()));
		next.setText("next");
		next.setBounds(280, 400, 100, 40);
		next.setHorizontalTextPosition(JButton.CENTER);
		next.setVerticalTextPosition(JButton.CENTER);
		next.setForeground(Color.white);
		next.setFont(f);
		next.addActionListener(this);

		accept = new JButton(new ImageIcon(config.getBtImage()));
		accept.setText("accept");
		accept.setBounds(280, 300, 100, 40);
		accept.setHorizontalTextPosition(JButton.CENTER);
		accept.setVerticalTextPosition(JButton.CENTER);
		accept.setForeground(Color.white);
		accept.setFont(f);
		accept.addActionListener(this);

		reject = new JButton(new ImageIcon(config.getBtImage()));
		reject.setText("reject");
		reject.setBounds(280, 350, 100, 40);
		reject.setHorizontalTextPosition(JButton.CENTER);
		reject.setVerticalTextPosition(JButton.CENTER);
		reject.setForeground(Color.white);
		reject.setFont(f);
		reject.addActionListener(this);

		prof = new JLabel();
		prof.setBorder(BorderFactory.createLineBorder(Color.white));
		prof.setBounds(75, 40, 250, 250);

		username = new JLabel("username: ");
		username.setHorizontalTextPosition(JLabel.CENTER);
		username.setVerticalTextPosition(JLabel.CENTER);
		username.setForeground(Color.white);
		username.setFont(f);
		username.setBounds(30, 300, 120, 40);

		followers = new JLabel("followers: ");
		followers.setHorizontalTextPosition(JLabel.CENTER);
		followers.setVerticalTextPosition(JLabel.CENTER);
		followers.setForeground(Color.white);
		followers.setFont(f);
		followers.setBounds(30, 350, 120, 40);

		followings = new JLabel("followings: ");
		followings.setHorizontalTextPosition(JLabel.CENTER);
		followings.setVerticalTextPosition(JLabel.CENTER);
		followings.setForeground(Color.white);
		followings.setFont(f);
		followings.setBounds(30, 400, 120, 40);

		tweets = new JLabel("tweets: ");
		tweets.setHorizontalTextPosition(JLabel.CENTER);
		tweets.setVerticalTextPosition(JLabel.CENTER);
		tweets.setForeground(Color.white);
		tweets.setFont(f);
		tweets.setBounds(30, 450, 120, 40);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			Controller.getInstance().listen("nextRequestsPanel");
		} else if (e.getSource() == prev) {
			Controller.getInstance().listen("prevRequestsPanel");
		} else if (e.getSource() == accept) {
			Controller.getInstance().listen("accept");
		} else if (e.getSource() == reject) {
			Controller.getInstance().listen("reject");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, this);

	}
}