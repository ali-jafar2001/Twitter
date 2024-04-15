package panels;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import logic.Logger;
import logic.SocialMedia;
import models.Message;
import models.Tweet;
import models.User;
import util.MainFrameConfig;

public class MainFrame extends JFrame {
	private static MainFrame frame;
	private MainFrameConfig config;
	private LoginPanel loginPanel;
	private MenuPanel menuPanel;
	private SettingPanel settingPanel;
	private PagePanel pagePanel;
	private ExplorerPanel explorerPanel;
	private TimeLinePanel timeLinePanel;
	private MessagingPanel messagingPanel;

	public void createPanels() {
//		this.settingPanel = new SettingPanel();
//		this.pagePanel = new PagePanel();
//		this.explorerPanel = new ExplorerPanel();
//		this.timeLinePanel = new TimeLinePanel();
//		this.messagingPanel = new MessagingPanel();
		MyThread1 myThread1 = new MyThread1();
		MyThread2 myThread2 = new MyThread2();
		MyThread3 myThread3 = new MyThread3();
		myThread1.start();
		myThread2.start();
		myThread3.start();
	}

	public MainFrame() {
		super("tweeter");
		SocialMedia.Init();
		config = new MainFrameConfig("FRAME_CONFIG_FILE");
		loginPanel = new LoginPanel();
		menuPanel = new MenuPanel();
		this.menuPanel.setVisible(false);

		this.add(loginPanel);

		this.setUndecorated(true);
		this.setResizable(config.isResizaable());
		this.setExtendedState(config.getExtendedState());
		this.setVisible(true);
	}

	public static void start() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame = new MainFrame();
			}
		});

	}

	public static MainFrame getInstance() {
		if (frame == null)
			frame = new MainFrame();
		return frame;
	}

	public void addSignInPanel() {
		this.loginPanel.addSignInPanel();
		try {
			Logger.info("Sign in button clicked");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addSignUpPanel() {
		this.loginPanel.addSignUpPanel();
		try {
			Logger.info("Sign up button clicked");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exitProgram() {
		try {
			Logger.info("program ends");
		} catch (IOException e) {
			e.printStackTrace();
		}
		setVisible(false);
		System.exit(0);
	}

	public String getEmail() {
		return this.loginPanel.getEmail();
	}

	public String getFname() {
		return this.loginPanel.getFname();
	}

	public String getLname() {
		return this.loginPanel.getLname();
	}

	public String getSignUpUsername() {
		return this.loginPanel.getSignUpUsername();
	}

	public String getSignUpPassword() {
		return this.loginPanel.getSignUpPassword();
	}

	public String getSignInUsername() {
		return this.loginPanel.getSignInUsername();
	}

	public String getSignInPassword() {
		return this.loginPanel.getSignInPassword();
	}

	public void showSignInMessage() {
		JOptionPane.showMessageDialog(this, "Hello Welcome twiteer");
		this.loginPanel.setVisible(false);
		this.add(menuPanel);
		this.menuPanel.setVisible(true);
	}

	public void showMessageDialog(String st) {
		JOptionPane.showMessageDialog(this, st);
	}

	public void showSignUpMessage() {
		JOptionPane.showMessageDialog(this, "Hello Welcome twiteer");
		this.loginPanel.setVisible(false);
		this.add(menuPanel);
		this.menuPanel.setVisible(true);
	}

	public void goSettingPanel() {
		this.menuPanel.setVisible(false);
		this.add(settingPanel);
		this.settingPanel.setVisible(true);
	}

	public void showUncorrectPasswordMessage() {
		JOptionPane.showMessageDialog(this, "password is wrong", "Alert", JOptionPane.WARNING_MESSAGE);
	}

	public void showUncorrectedUsernameMessage() {
		JOptionPane.showMessageDialog(this, "There is no account with this username", "Alert",
				JOptionPane.WARNING_MESSAGE);
	}

	public void showUncorrectNameMessage() {
		JOptionPane.showMessageDialog(this, "There is an account with this username", "Alert",
				JOptionPane.WARNING_MESSAGE);
	}

	public void backToLoginPanel() {
		this.menuPanel.setVisible(false);
		this.loginPanel.setVisible(true);
	}

	public void settingToMenuPanel() {
		this.settingPanel.setVisible(false);
		this.menuPanel.setVisible(true);
	}

	public String status() {
		return this.settingPanel.status();
	}

	public String access() {
		return this.settingPanel.access();
	}

	public String lastSeen() {
		return this.settingPanel.lastSeen();
	}

	public void setStatus() {
		this.settingPanel.setStatus();
	}

	public void setAccess() {
		this.settingPanel.setAccess();
	}

	public void setLastSeen() {
		this.settingPanel.setLastSeen();
	}

	public void createCommandLine() {
		new CommandLineFrame();
	}

	public boolean deleteAccountMessage() {
		int a = JOptionPane.showConfirmDialog(this, "Are you sure you want delete account and exit?");
		if (a == JOptionPane.YES_OPTION) {
			return true;
		} else
			return false;
	}

	public void emptyMyTweetsMessage() {
		JOptionPane.showMessageDialog(this, "there is no tweet!");
	}

	public void emptyPopTweetsMessage() {
		JOptionPane.showMessageDialog(this, "there is no tweet!");
	}

	public void emptySavedMessage() {
		JOptionPane.showMessageDialog(this, "there is no message!");
	}

	public void emptyExploreUser() {
		JOptionPane.showMessageDialog(this, "there is no user with this username!");
	}

	public void setUserInformation(User user) {
		explorerPanel.setUserInformation(user);
	}

	public void showUserProfile() {
		explorerPanel.showUserProfile();
	}

	public String getExploreUsername() {
		return explorerPanel.getExploreUsername();
	}

	public void goPagePanel() {
		this.menuPanel.setVisible(false);
		this.add(pagePanel);
		this.pagePanel.setVisible(true);
	}

	public void goExplorerPanel() {
		this.menuPanel.setVisible(false);
		this.add(explorerPanel);
		this.explorerPanel.setVisible(true);
	}

	public void goTimeLinePanel() {
		this.menuPanel.setVisible(false);
		this.add(timeLinePanel);
		this.timeLinePanel.setVisible(true);
	}

	public void goMessagingPanel() {
		this.menuPanel.setVisible(false);
		this.add(messagingPanel);
		this.messagingPanel.setVisible(true);
	}

	public void pageToMenuPanel() {
		this.pagePanel.setVisible(false);
		this.menuPanel.setVisible(true);
	}

	public void explorerToMenuPanel() {
		this.explorerPanel.setVisible(false);
		this.menuPanel.setVisible(true);
	}

	public void messagingToMenuPanel() {
		this.messagingPanel.setVisible(false);
		this.menuPanel.setVisible(true);
	}

	public void timeLineToMenuPanel() {
		this.timeLinePanel.setVisible(false);
		this.menuPanel.setVisible(true);
	}

	public String editFname() {
		String name = JOptionPane.showInputDialog(this, "Enter first name: ");
		if (name == null)
			name = SocialMedia.getCurrent_user().firstname;
		this.pagePanel.editFname(name);
		return name;
	}

	public String editLname() {
		String name = JOptionPane.showInputDialog(this, "Enter last name: ");
		if (name == null)
			name = SocialMedia.getCurrent_user().lastname;
		this.pagePanel.editLname(name);
		return name;
	}

	public String editBio() {
		String name = JOptionPane.showInputDialog(this, "Enter bio: ");
		if (name == null)
			return SocialMedia.getCurrent_user().bio;
		this.pagePanel.editBio(name);
		return name;
	}

	public String editEmail() {
		String name = JOptionPane.showInputDialog(this, "Enter Email: ");
		if (name == null)
			return SocialMedia.getCurrent_user().Email;
		this.pagePanel.editEmail(name);
		return name;
	}

	public String editPhone() {
		String name = JOptionPane.showInputDialog(this, "Enter phone number: ");
		if (name == null)
			return SocialMedia.getCurrent_user().phonenumber;
		this.pagePanel.editPhone(name);
		return name;
	}

	public String editUsername() {
		String name = JOptionPane.showInputDialog(this, "Enter User name: ");
		if (name == null)
			return SocialMedia.getCurrent_user().username;
		this.pagePanel.editUsername(name);
		return name;
	}

	public String editPassword() {
		String name = JOptionPane.showInputDialog(this, "Enter password: ");
		if (name == null)
			return SocialMedia.getCurrent_user().getPassword();
		this.pagePanel.editPassword(name);
		return name;
	}

	public String editBirth() {
		String name = JOptionPane.showInputDialog(this, "Enter Birth date: (##/##/## ");
		this.pagePanel.editBirth(name);
		return name;
	}

	public String addTweet() {
		String name = JOptionPane.showInputDialog(this, "Enter your tweet text: ");
		return name;
	}

	public String forwardUser() {
		String name = JOptionPane.showInputDialog(this, "Enter your chat name: ");
		return name;
	}

	public String addComment() {
		String name = JOptionPane.showInputDialog(this, "Enter your comment text: ");
		return name;
	}

	public String addSaveMesaage() {
		String name = JOptionPane.showInputDialog(this, "Enter your message text: ");
		return name;
	}

	public String createGroup() {
		String name = JOptionPane.showInputDialog(this, "Enter your group name: ");
		return name;
	}

	public void setchatList(ArrayList<String> array) {
		this.messagingPanel.setchatList(array);
	}

	public String addTweetPluse() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(this);
		File file = chooser.getSelectedFile();
		if (file != null)
			return file.getAbsolutePath();
		else
			return "default";
	}

	public String addSaveMessagePlus() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(this);
		File file = chooser.getSelectedFile();
		if (file != null)
			return file.getAbsolutePath();
		else
			return "default";
	}

	public void createMyTweetsPanel() {
		this.pagePanel.createMyTweetsPanel();
	}

	public String setProfile() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(this);
		File file = chooser.getSelectedFile();
		if (file != null)
			return file.getAbsolutePath();
		else {
			return "default";
		}

	}

	public void setProfilePicture() {
		this.pagePanel.setProfilePictue();
	}

	public void setSaveMessage(Message message) {
		messagingPanel.setSaveMessage(message);
	}

	public void showSavedMessages() {
		messagingPanel.showSavedMessages();
	}

	public void createPanels1() {
		if (this.pagePanel == null)
			this.pagePanel = new PagePanel();
		else
			this.pagePanel.reset();
	}

	public void createPanels2() {
		if (this.messagingPanel == null)
			this.messagingPanel = new MessagingPanel();
		else
			this.messagingPanel.reset();

		if (this.settingPanel == null)
			this.settingPanel = new SettingPanel();
		else
			this.settingPanel.reset();
	}

	public void createPanels3() {
		if (this.explorerPanel == null)
			this.explorerPanel = new ExplorerPanel();
		else
			this.explorerPanel.reset();

		if (this.timeLinePanel == null)
			this.timeLinePanel = new TimeLinePanel();
		else
			this.timeLinePanel.reset();
	}

	public void setMyTweet(Tweet tweet) {
		this.pagePanel.setMyTweet(tweet);
	}

	public void showPopTweets() {
		explorerPanel.showPoptweets();
	}

	public void setPopTweets(Tweet tweet, User user) {
		this.explorerPanel.setPopTweetsPanel(tweet, user);
	}

	public void setTweet(Tweet tweet, User user) {
		this.timeLinePanel.setTweet(tweet, user);
	}

	public void showTimeLineTweets() {
		this.timeLinePanel.showTweetsPanel();
	}

	public void setComment(Tweet tweet, User user) {
		this.timeLinePanel.setComment(tweet, user);
	}

	public void showCommentsPanel() {
		this.timeLinePanel.showCommentsPanel();
	}

	public void notShowCommentsPanel() {
		this.timeLinePanel.notShowCommentsPanel();
	}

	public void notShowTweetsPanel() {
		this.timeLinePanel.notShowTweetsPanel();
	}

	public void setRetweetLabel(int i) {
		this.timeLinePanel.setRetweetLabel(i);
	}

	public void setRecommentLabel(int i) {
		this.timeLinePanel.setRecommentLabel(i);
	}

	public void setLikeTweetLabel(int i) {
		this.timeLinePanel.setLikeTweetLabel(i);
	}

	public void setLikeCommentLabel(int i) {
		this.timeLinePanel.setLikeCommentLabel(i);
	}

	public void showNewsPanel() {
		this.pagePanel.showNewsPanel();
	}

	public void showFollowersPanel() {
		this.pagePanel.showFollowersPanel();
	}

	public void showFollowingsPanel() {
		this.pagePanel.showFollowingsPanel();
	}

	public void showRequestsPanel() {
		this.pagePanel.showRequestsPanel();
	}

	public void showBlackListPanel() {
		this.pagePanel.showBlackListPanel();
	}

	public void notShowPanels() {
		this.pagePanel.notShowPanels();
	}

	public void println(String text, Color color) {
		this.pagePanel.println(text, color);
	}

	public void setFollowersPanelUserInfo(User user) {
		this.pagePanel.setFollowersPanelUserInfo(user);
	}

	public void setFollowingsPanelUserInfo(User user) {
		this.pagePanel.setFollowingsPanelUserInfo(user);
	}

	public void setBlackListPanelUserInfo(User user) {
		this.pagePanel.setBlackListPanelUserInfo(user);
	}

	public void setRequestsPanelUserInfo(User user) {
		this.pagePanel.setRequestsPanelUserInfo(user);
	}

	public void showChatsList() {
		this.messagingPanel.showChatsList();
	}

	public void showChatPanel() {
		this.messagingPanel.showChatPanel();
	}

	public void showGroupChatPanel() {
		this.messagingPanel.showGroupChatPanel();
	}

	public void notShowChatPanels() {
		this.messagingPanel.notShowChatPanels();
	}

	public void setChatMessage(Message message, User user) {
		this.messagingPanel.setChatMessage(message, user);
	}

	public void setGroupChatMessage(Message message, User user) {
		this.messagingPanel.setGroupChatMessage(message, user);
	}

	public String getChatName() {
		return this.messagingPanel.getChatName();
	}

	public void setMessageNull() {
		this.messagingPanel.setMessageNull();
	}

	public String chatMessage() {
		String name = JOptionPane.showInputDialog(this, "Enter your message text: ");
		return name;
	}

	public String groupChatMessage() {
		String name = JOptionPane.showInputDialog(this, "Enter your message text: ");
		return name;
	}

	public String addMember() {
		String name = JOptionPane.showInputDialog(this, "Enter username: ");
		return name;
	}

	public String chatMessagePlus() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(this);
		File file = chooser.getSelectedFile();
		if (file != null)
			return file.getAbsolutePath();
		else
			return "default";
	}

	public String groupChatMessagePlus() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(this);
		File file = chooser.getSelectedFile();
		if (file != null)
			return file.getAbsolutePath();
		else
			return "default";
	}

}

class MyThread1 extends Thread {
	@Override
	public void run() {
		super.run();
		MainFrame.getInstance().createPanels1();
	}
}

class MyThread2 extends Thread {
	@Override
	public void run() {
		super.run();
		MainFrame.getInstance().createPanels2();
	}
}

class MyThread3 extends Thread {
	@Override
	public void run() {
		super.run();
		MainFrame.getInstance().createPanels3();
	}
}
