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
import javax.swing.JTextField;

import logic.Controller;
import models.Tweet;
import models.User;
import util.ExplorerConfig;
import util.PageConfig;

public class ExplorerPanel extends JPanel implements ActionListener {
	private ExplorerConfig config;
	private PopTweetsPanel popTweetsPanel;
	private Image background;
	private JButton back;
	private JButton popTweets;
	private JTextField username;
	private JButton search;
	private UserProfilePanel userProfilePanel;

	public void reset() {
		this.userProfilePanel.setVisible(false);
		this.popTweetsPanel.setVisible(false);
		username.setText("Username:");
	}

	public String getExploreUsername() {
		return username.getText();
	}

	public ExplorerPanel() {
		super();
		this.setLayout(null);
		init();
		this.add(back);
		this.add(popTweets);
		this.add(popTweetsPanel);
		this.add(search);
		this.add(username);
		this.add(userProfilePanel);
	}

	public void setPopTweetsPanel(Tweet tweet, User user) {
		popTweetsPanel.setPopTweet(tweet, user);
	}

	public void showPoptweets() {
//		this.add(popTweetsPanel);
		popTweetsPanel.setVisible(true);
	}

	public void setUserInformation(User user) {
		this.userProfilePanel.setUserInformation(user);
	}

	private void init() {
		config = new ExplorerConfig("EXPLORER_CONFIG_FILE");
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

		search = new JButton(new ImageIcon(config.getBtImage()));
		search.setBounds(1100, 50, config.getBtWidth(), config.getBtHeight() - 10);
		search.setText("Search");
		search.setHorizontalTextPosition(JButton.CENTER);
		search.setVerticalTextPosition(JButton.CENTER);
		search.setForeground(Color.white);
		search.setFont(f);
		search.addActionListener(this);

		username = new JTextField("Username:");
		username.setFont(f);
		username.setBounds(940, 50, config.getBtWidth(), config.getBtHeight() - 10);
		popTweets = new JButton(new ImageIcon(config.getBtImage()));
		popTweets.setBounds(220, 40, config.getBtWidth(), config.getBtHeight() - 10);
		popTweets.setText("popular tweets");
		popTweets.setHorizontalTextPosition(JButton.CENTER);
		popTweets.setVerticalTextPosition(JButton.CENTER);
		popTweets.setForeground(Color.white);
		popTweets.setFont(f);
		popTweets.addActionListener(this);

		popTweetsPanel = new PopTweetsPanel();
		popTweetsPanel.setBounds(100, 100, 400, 500);
		popTweetsPanel.setVisible(false);

		userProfilePanel = new UserProfilePanel();
		userProfilePanel.setBounds(850, 100, 400, 500);
		userProfilePanel.setVisible(false);
	}

	public void showUserProfile() {
		userProfilePanel.setVisible(true);
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
			Controller.getInstance().listen("explorerToMenu");
		} else if (e.getSource() == popTweets) {
			Controller.getInstance().listen("popTweets");
		} else if (e.getSource() == search) {
			Controller.getInstance().listen("searchUser");
		}
	}

}

class PopTweetsPanel extends JPanel implements ActionListener {
	private ExplorerConfig config;
	private Image background;
	private JButton prev, next;
	private JLabel text, time, like, retweet, profile;

	public PopTweetsPanel() {
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
	}

	private void init() {
		config = new ExplorerConfig("EXPLORER_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		Font f = new Font("serif", Font.PLAIN, 18);

		prev = new JButton(new ImageIcon(config.getBtImage()));
		prev.setBounds(80, 440, 100, 30);
		prev.setText("previous");
		prev.setHorizontalTextPosition(JButton.CENTER);
		prev.setVerticalTextPosition(JButton.CENTER);
		prev.setForeground(Color.white);
		prev.setFont(f);
		prev.addActionListener(this);

		next = new JButton(new ImageIcon(config.getBtImage()));
		next.setText("next");
		next.setBounds(190, 440, 100, 30);
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
		text.setBounds(10, 10, 370, 250);
		text.setIconTextGap(0);
		text.setBorder(BorderFactory.createLineBorder(Color.white));

		profile = new JLabel();
		profile.setFont(new Font("serif", Font.PLAIN, 22));
		profile.setForeground(Color.white);
		profile.setHorizontalTextPosition(JLabel.RIGHT);
		profile.setVerticalTextPosition(JLabel.CENTER);
		profile.setBounds(60, 270, 220, 80);
		profile.setIconTextGap(0);
		profile.setBorder(BorderFactory.createLineBorder(Color.white));

		like = new JLabel();
		like.setIcon(new ImageIcon(config.getLikeImage()));
		like.setFont(f);
		like.setForeground(Color.white);
		like.setHorizontalTextPosition(JLabel.RIGHT);
		like.setVerticalTextPosition(JLabel.CENTER);
		like.setBounds(60, 360, 80, 30);
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
		retweet.setBounds(240, 360, 80, 30);
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
		time.setBounds(80, 400, 220, 30);
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

	public void setPopTweet(Tweet tweet, User user) {
		text.setText(tweet.getMessage());
		time.setText(tweet.getDateandtime().getDateandTime());
		like.setText(String.valueOf(tweet.getLovers()));
		retweet.setText(String.valueOf(tweet.getRetweets()));
		if (tweet.getImageAddress().equals("default")) {
			text.setIcon(null);
			text.setBounds(10, 240, 370, 20);
		} else {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(tweet.getImageAddress()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			text.setBounds(10, 10, 370, 250);
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
		Image img2 = img.getScaledInstance(profile.getWidth() - 100, profile.getHeight(), Image.SCALE_SMOOTH);
		profile.setIcon(new ImageIcon(img2));
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
			Controller.getInstance().listen("nextPopTweets");
		} else if (e.getSource() == prev) {
			Controller.getInstance().listen("prevPopTweets");
		}
	}

}

class UserProfilePanel extends JPanel implements ActionListener {
	private ExplorerConfig config;
	private Image background;
	private JLabel profile, username, fname, lname, access, lastSeen, followers, followings;
	private JButton follow, block, direct;

	public UserProfilePanel() {
		super();
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		init();
		this.add(block);
		this.add(follow);
		this.add(direct);
		this.add(profile);
		this.add(username);
		this.add(fname);
		this.add(lname);
		this.add(access);
		this.add(lastSeen);
		this.add(followers);
		this.add(followings);
	}

	private void init() {
		config = new ExplorerConfig("EXPLORER_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		Font f = new Font("serif", Font.PLAIN, 20);

		profile = new JLabel();
		profile.setFont(f);
		profile.setBounds(290, 10, 100, 100);
		profile.setBorder(BorderFactory.createLineBorder(Color.white));
		profile.setBackground(Color.red);

		username = new JLabel();
		username.setFont(f);
		username.setBounds(10, 10, 200, 40);
		username.setText("username: ");
		username.setForeground(Color.white);

		fname = new JLabel();
		fname.setFont(f);
		fname.setBounds(10, 60, 200, 40);
		fname.setText("first name: ");
		fname.setForeground(Color.white);

		lname = new JLabel();
		lname.setFont(f);
		lname.setBounds(10, 110, 200, 40);
		lname.setText("last name: ");
		lname.setForeground(Color.white);

		lastSeen = new JLabel();
		lastSeen.setFont(f);
		lastSeen.setBounds(10, 210, 200, 40);
		lastSeen.setText("last seen type: ");
		lastSeen.setForeground(Color.white);

		access = new JLabel();
		access.setFont(f);
		access.setBounds(10, 160, 200, 40);
		access.setText("access level: ");
		access.setForeground(Color.white);

		followers = new JLabel();
		followers.setFont(f);
		followers.setBounds(10, 260, 200, 40);
		followers.setText("followers: ");
		followers.setForeground(Color.white);

		followings = new JLabel();
		followings.setFont(f);
		followings.setBounds(10, 310, 200, 40);
		followings.setText("followings: ");
		followings.setForeground(Color.white);

		follow = new JButton(new ImageIcon(config.getBtImage()));
		follow.setBounds(10, 450, config.getBtWidth() - 40, config.getBtHeight() - 10);
		follow.setText("follow");
		follow.setHorizontalTextPosition(JButton.CENTER);
		follow.setVerticalTextPosition(JButton.CENTER);
		follow.setForeground(Color.white);
		follow.setFont(f);
		follow.addActionListener(this);

		direct = new JButton(new ImageIcon(config.getBtImage()));
		direct.setBounds(130, 450, config.getBtWidth() - 40, config.getBtHeight() - 10);
		direct.setText("direct");
		direct.setHorizontalTextPosition(JButton.CENTER);
		direct.setVerticalTextPosition(JButton.CENTER);
		direct.setForeground(Color.white);
		direct.setFont(f);
		direct.addActionListener(this);

		block = new JButton(new ImageIcon(config.getBtImage()));
		block.setBounds(250, 450, config.getBtWidth() - 40, config.getBtHeight() - 10);
		block.setText("block");
		block.setHorizontalTextPosition(JButton.CENTER);
		block.setVerticalTextPosition(JButton.CENTER);
		block.setForeground(Color.white);
		block.setFont(f);
		block.addActionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == follow) {
			Controller.getInstance().listen("follow");
		} else if (e.getSource() == direct) {
			Controller.getInstance().listen("direct");
		} else if (e.getSource() == block) {
			Controller.getInstance().listen("block");
		}
	}

	public void setUserInformation(User user) {
		username.setText("user name: " + user.username);
		fname.setText("first name: " + user.firstname);
		lname.setText("last name: " + user.lastname);
		lastSeen.setText("last seen type: " + user.getLastseen().getLevel());
		access.setText("access level: " + user.getAccesslevel());
		followers.setText("followers: " + user.getMenu().getPage().getFollowers().size());
		followings.setText("followings: " + user.getMenu().getPage().getFollowings().size());
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
		Image img2 = img.getScaledInstance(profile.getWidth(), profile.getHeight(), Image.SCALE_SMOOTH);
		profile.setIcon(new ImageIcon(img2));
	}
}
