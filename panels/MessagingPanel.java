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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.w3c.dom.ls.LSInput;

import logic.Controller;
import logic.SocialMedia;
import models.Message;
import models.Tweet;
import models.User;
import util.MessagingConfig;
import util.TimeLineConfig;

public class MessagingPanel extends JPanel implements ActionListener {
	private MessagingConfig config;
	private Image background;
	private JButton back, saveMessages, message, messagePlus, newGroup, chats, select;
	private SaveMessagesPanel saveMessagesPanel;
	private ChatsListPanel chatsList;
	private ChatPanel chatPanel;
	private GroupChatPanel groupChatPanel;

	public void reset() {
		this.saveMessagesPanel.setVisible(false);
		this.chatsList.setVisible(false);
		this.chatPanel.setVisible(false);
		this.groupChatPanel.setVisible(false);
		this.chatsList.reset();
	}

	public MessagingPanel() {
		super();
		this.setLayout(null);
		init();
		this.add(back);
		this.add(saveMessages);
		this.add(saveMessagesPanel);
		this.add(message);
		this.add(messagePlus);
		this.add(chats);
		this.add(newGroup);
		this.add(chatsList);
		this.add(select);
		this.add(chatPanel);
		this.add(groupChatPanel);
	}

	public void setMessageNull() {
		this.chatPanel.setMessageNull();
		this.groupChatPanel.setMessageNull();
	}

	public void setchatList(ArrayList<String> array) {
		this.chatsList.setchatList(array);
	}

	public String getChatName() {
		return this.chatsList.getChatName();
	}

	public void setSaveMessage(Message message) {
		saveMessagesPanel.setSaveMessage(message);
	}

	private void init() {
		config = new MessagingConfig("MESSAGING_CONFIG_FILE");
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

		saveMessages = new JButton(new ImageIcon(config.getBtImage()));
		saveMessages.setBounds(20, 30, config.getBtWidth() + 10, config.getBtHeight() - 10);
		saveMessages.setText("Saved Messages");
		saveMessages.setHorizontalTextPosition(JButton.CENTER);
		saveMessages.setVerticalTextPosition(JButton.CENTER);
		saveMessages.setForeground(Color.white);
		saveMessages.setFont(f);
		saveMessages.addActionListener(this);

		message = new JButton(new ImageIcon(config.getBtImage()));
		message.setBounds(20, 530, config.getBtWidth(), config.getBtHeight() - 10);
		message.setText("Message");
		message.setHorizontalTextPosition(JButton.CENTER);
		message.setVerticalTextPosition(JButton.CENTER);
		message.setForeground(Color.white);
		message.setFont(f);
		message.addActionListener(this);

		messagePlus = new JButton(new ImageIcon(config.getBtImage()));
		messagePlus.setBounds(20, 580, config.getBtWidth(), config.getBtHeight() - 10);
		messagePlus.setText("Message++");
		messagePlus.setHorizontalTextPosition(JButton.CENTER);
		messagePlus.setVerticalTextPosition(JButton.CENTER);
		messagePlus.setForeground(Color.white);
		messagePlus.setFont(f);
		messagePlus.addActionListener(this);

		newGroup = new JButton(new ImageIcon(config.getBtImage()));
		newGroup.setBounds(1190, 710, config.getBtWidth(), config.getBtHeight() - 10);
		newGroup.setText("+Group");
		newGroup.setHorizontalTextPosition(JButton.CENTER);
		newGroup.setVerticalTextPosition(JButton.CENTER);
		newGroup.setForeground(Color.white);
		newGroup.setFont(f);
		newGroup.addActionListener(this);

		chats = new JButton(new ImageIcon(config.getBtImage()));
		chats.setBounds(400, 20, config.getBtWidth(), config.getBtHeight() - 10);
		chats.setText("Chats");
		chats.setHorizontalTextPosition(JButton.CENTER);
		chats.setVerticalTextPosition(JButton.CENTER);
		chats.setForeground(Color.white);
		chats.setFont(f);
		chats.addActionListener(this);

		saveMessagesPanel = new SaveMessagesPanel();
		saveMessagesPanel.setBounds(20, 100, 300, 400);
		saveMessagesPanel.setVisible(false);

		chatsList = new ChatsListPanel();
		chatsList.setBounds(400, 100, 200, 300);
		chatsList.setVisible(false);

		select = new JButton(new ImageIcon(config.getBtImage()));
		select.setBounds(420, 420, config.getBtWidth(), config.getBtHeight() - 10);
		select.setText("Select");
		select.setHorizontalTextPosition(JButton.CENTER);
		select.setVerticalTextPosition(JButton.CENTER);
		select.setForeground(Color.white);
		select.setFont(f);
		select.addActionListener(this);
		select.setVisible(false);

		chatPanel = new ChatPanel();
		chatPanel.setBounds(700, 100, 450, 500);
		chatPanel.setVisible(false);

		groupChatPanel = new GroupChatPanel();
		groupChatPanel.setBounds(700, 100, 450, 500);
		groupChatPanel.setVisible(false);
	}

	public void showChatPanel() {
		chatPanel.setVisible(true);
		groupChatPanel.setVisible(false);
	}

	public void showGroupChatPanel() {
		chatPanel.setVisible(false);
		groupChatPanel.setVisible(true);
	}

	public void notShowChatPanels() {
		chatPanel.setVisible(false);
		groupChatPanel.setVisible(false);
	}

	public void setChatMessage(Message message, User user) {
		this.chatPanel.setMessage(message, user);
	}

	public void setGroupChatMessage(Message message, User user) {
		this.groupChatPanel.setMessage(message, user);
	}

	public void showChatsList() {
		this.select.setVisible(true);
		this.chatsList.setVisible(true);
	}

	public void showSavedMessages() {
		saveMessagesPanel.setVisible(true);
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
			Controller.getInstance().listen("messagingToMenu");
		} else if (e.getSource() == message) {
			Controller.getInstance().listen("saveMessage");
		} else if (e.getSource() == messagePlus) {
			Controller.getInstance().listen("saveMessagePlus");
		} else if (e.getSource() == saveMessages) {
			Controller.getInstance().listen("savedMessages");
		} else if (e.getSource() == newGroup) {
			Controller.getInstance().listen("createGroup");
		} else if (e.getSource() == chats) {
			Controller.getInstance().listen("showChats");
		} else if (e.getSource() == select) {
			Controller.getInstance().listen("selectChat");
		}
	}

}

class SaveMessagesPanel extends JPanel implements ActionListener {
	private MessagingConfig config;
	private Image background;
	private JButton prev, next;
	private JLabel text;

	public SaveMessagesPanel() {
		super();
		this.setLayout(null);
		init();
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		this.add(next);
		this.add(prev);
		this.add(text);
	}

	private void init() {
		config = new MessagingConfig("MESSAGING_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		Font f = new Font("serif", Font.PLAIN, 18);

		prev = new JButton(new ImageIcon(config.getBtImage()));
		prev.setBounds(30, 280, 100, 30);
		prev.setText("previous");
		prev.setHorizontalTextPosition(JButton.CENTER);
		prev.setVerticalTextPosition(JButton.CENTER);
		prev.setForeground(Color.white);
		prev.setFont(f);
		prev.addActionListener(this);

		next = new JButton(new ImageIcon(config.getBtImage()));
		next.setText("next");
		next.setBounds(140, 280, 100, 30);
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
			Controller.getInstance().listen("nextSaveMessage");
		} else if (e.getSource() == prev) {
			Controller.getInstance().listen("prevSaveMessage");
		}
	}

	public void setSaveMessage(Message message) {
		text.setText(message.getMessage());
		if (message.getImageAddress().equals("default")) {
			text.setIcon(null);
			text.setBounds(10, 240, 270, 20);
		} else {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(message.getImageAddress()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			text.setBounds(10, 10, 270, 250);
			Image img2 = img.getScaledInstance(text.getWidth(), text.getHeight() - 20, Image.SCALE_SMOOTH);
			text.setIcon(new ImageIcon(img2));
		}
	}

}

class ChatsListPanel extends JPanel {
	private MessagingConfig config;
	private Image background;
	private JList<String> list;
	private JScrollPane scrollPane;
	private DefaultListModel<String> listModel;

	public ChatsListPanel() {
		super();
		this.setLayout(null);
		init();
		this.add(scrollPane);
	}

	public String getChatName() {
		return list.getSelectedValue();
	}

	private void init() {
		config = new MessagingConfig("MESSAGING_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());

		listModel = new DefaultListModel<String>();
		list = new JList<String>();
		list.setModel(listModel);
		list.setFont(new Font("serif", Font.PLAIN, 20));

		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(0, 0, 200, 300);
	}

	public void setchatList(ArrayList<String> array) {
		listModel.removeAllElements();
		for (String st : array) {
			listModel.addElement(st);
		}
	}

	public void reset() {
		listModel.removeAllElements();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, this);
	}
}

class ChatPanel extends JPanel implements ActionListener {
	private MessagingConfig config;
	private Image background;
	private JButton prev, next, edit, message, messagePlus, save, delete, forward;
	private JLabel text, profile, lastSeen, time;

	public ChatPanel() {
		super();
		this.setLayout(null);
		init();
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		this.add(next);
		this.add(prev);
		this.add(text);
		this.add(profile);
		this.add(message);
		this.add(messagePlus);
		this.add(delete);
		this.add(forward);
		this.add(save);
		this.add(edit);
		this.add(lastSeen);
		this.add(time);
	}

	private void init() {
		config = new MessagingConfig("MESSAGING_CONFIG_FILE");
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

		message = new JButton(new ImageIcon(config.getBtImage()));
		message.setText("Message");
		message.setBounds(300, 20, 130, 40);
		message.setHorizontalTextPosition(JButton.CENTER);
		message.setVerticalTextPosition(JButton.CENTER);
		message.setForeground(Color.white);
		message.setFont(f);
		message.addActionListener(this);

		messagePlus = new JButton(new ImageIcon(config.getBtImage()));
		messagePlus.setText("Message++");
		messagePlus.setBounds(300, 70, 130, 40);
		messagePlus.setHorizontalTextPosition(JButton.CENTER);
		messagePlus.setVerticalTextPosition(JButton.CENTER);
		messagePlus.setForeground(Color.white);
		messagePlus.setFont(f);
		messagePlus.addActionListener(this);

		delete = new JButton(new ImageIcon(config.getBtImage()));
		delete.setText("Delete");
		delete.setBounds(300, 120, 130, 40);
		delete.setHorizontalTextPosition(JButton.CENTER);
		delete.setVerticalTextPosition(JButton.CENTER);
		delete.setForeground(Color.white);
		delete.setFont(f);
		delete.addActionListener(this);

		forward = new JButton(new ImageIcon(config.getBtImage()));
		forward.setText("Forward");
		forward.setBounds(300, 170, 130, 40);
		forward.setHorizontalTextPosition(JButton.CENTER);
		forward.setVerticalTextPosition(JButton.CENTER);
		forward.setForeground(Color.white);
		forward.setFont(f);
		forward.addActionListener(this);

		edit = new JButton(new ImageIcon(config.getBtImage()));
		edit.setText("Edit");
		edit.setBounds(270, 220, 130, 40);
		edit.setHorizontalTextPosition(JButton.CENTER);
		edit.setVerticalTextPosition(JButton.CENTER);
		edit.setForeground(Color.white);
		edit.setFont(f);
		edit.addActionListener(this);

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

		lastSeen = new JLabel();
		lastSeen.setFont(new Font("serif", Font.PLAIN, 18));
		lastSeen.setText("last seen:");
		lastSeen.setForeground(Color.white);
		lastSeen.setHorizontalTextPosition(JLabel.RIGHT);
		lastSeen.setVerticalTextPosition(JLabel.CENTER);
		lastSeen.setBounds(20, 320, 250, 40);
		lastSeen.setIconTextGap(0);
		lastSeen.setBorder(BorderFactory.createLineBorder(Color.white));

		time = new JLabel();
		time.setFont(f);
		time.setForeground(Color.white);
		time.setHorizontalTextPosition(JLabel.RIGHT);
		time.setVerticalTextPosition(JLabel.CENTER);
		time.setBounds(20, 370, 220, 30);
		time.setIconTextGap(0);
		time.setBorder(BorderFactory.createLineBorder(Color.white));
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(config.getClockImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image img2 = img.getScaledInstance(time.getWidth() - 170, time.getHeight(), Image.SCALE_SMOOTH);
		time.setIcon(new ImageIcon(img2));

	}

	public void setMessage(Message message, User user) {
		text.setText(message.getMessage());
		time.setText(message.getDateandtime().getDateandTime());
		if (message.getImageAddress().equals("default")) {
			text.setIcon(null);
			text.setBounds(10, 190, 270, 20);
		} else {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(message.getImageAddress()));
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
		if (user.username.equals(SocialMedia.getCurrent_user().username))
			lastSeen.setText("last seen: online");
		else
			lastSeen.setText("last seen: " + user.getLastseen().getDate().getDateandTime());
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
			Controller.getInstance().listen("nextChatMessage");
		} else if (e.getSource() == prev) {
			Controller.getInstance().listen("prevChatMessage");
		} else if (e.getSource() == message) {
			Controller.getInstance().listen("messageChat");
		} else if (e.getSource() == messagePlus) {
			Controller.getInstance().listen("messagePlusChat");
		} else if (e.getSource() == save) {
			Controller.getInstance().listen("chatSaveMessage");
		} else if (e.getSource() == edit) {
			Controller.getInstance().listen("chatEditMessage");
		} else if (e.getSource() == delete) {
			Controller.getInstance().listen("chatDeleteMessage");
		} else if (e.getSource() == forward) {
			Controller.getInstance().listen("chatForwardMessage");
		}
	}

	public void setMessageNull() {
		text.setText("");
		time.setText("");
		text.setIcon(null);
		profile.setText("");
		profile.setIcon(null);
		lastSeen.setText("");
	}
}

class GroupChatPanel extends JPanel implements ActionListener {
	private MessagingConfig config;
	private Image background;
	private JButton prev, next, edit, message, messagePlus, save, delete, forward, addMember, users;
	private JLabel text, profile, lastSeen, time;

	public GroupChatPanel() {
		super();
		this.setLayout(null);
		init();
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		this.add(next);
		this.add(prev);
		this.add(text);
		this.add(profile);
		this.add(message);
		this.add(messagePlus);
		this.add(delete);
		this.add(forward);
		this.add(save);
		this.add(edit);
		this.add(addMember);
		this.add(lastSeen);
		this.add(time);
		this.add(users);
	}

	private void init() {
		config = new MessagingConfig("MESSAGING_CONFIG_FILE");
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

		message = new JButton(new ImageIcon(config.getBtImage()));
		message.setText("Message");
		message.setBounds(300, 20, 130, 40);
		message.setHorizontalTextPosition(JButton.CENTER);
		message.setVerticalTextPosition(JButton.CENTER);
		message.setForeground(Color.white);
		message.setFont(f);
		message.addActionListener(this);

		messagePlus = new JButton(new ImageIcon(config.getBtImage()));
		messagePlus.setText("Message++");
		messagePlus.setBounds(300, 70, 130, 40);
		messagePlus.setHorizontalTextPosition(JButton.CENTER);
		messagePlus.setVerticalTextPosition(JButton.CENTER);
		messagePlus.setForeground(Color.white);
		messagePlus.setFont(f);
		messagePlus.addActionListener(this);

		delete = new JButton(new ImageIcon(config.getBtImage()));
		delete.setText("Delete");
		delete.setBounds(300, 120, 130, 40);
		delete.setHorizontalTextPosition(JButton.CENTER);
		delete.setVerticalTextPosition(JButton.CENTER);
		delete.setForeground(Color.white);
		delete.setFont(f);
		delete.addActionListener(this);

		forward = new JButton(new ImageIcon(config.getBtImage()));
		forward.setText("Forward");
		forward.setBounds(300, 170, 130, 40);
		forward.setHorizontalTextPosition(JButton.CENTER);
		forward.setVerticalTextPosition(JButton.CENTER);
		forward.setForeground(Color.white);
		forward.setFont(f);
		forward.addActionListener(this);

		edit = new JButton(new ImageIcon(config.getBtImage()));
		edit.setText("Edit");
		edit.setBounds(280, 220, 130, 40);
		edit.setHorizontalTextPosition(JButton.CENTER);
		edit.setVerticalTextPosition(JButton.CENTER);
		edit.setForeground(Color.white);
		edit.setFont(f);
		edit.addActionListener(this);

		save = new JButton(new ImageIcon(config.getBtImage()));
		save.setText("Save");
		save.setBounds(280, 270, 130, 40);
		save.setHorizontalTextPosition(JButton.CENTER);
		save.setVerticalTextPosition(JButton.CENTER);
		save.setForeground(Color.white);
		save.setFont(f);
		save.addActionListener(this);

		addMember = new JButton(new ImageIcon(config.getBtImage()));
		addMember.setText("Add member");
		addMember.setBounds(280, 320, 130, 40);
		addMember.setHorizontalTextPosition(JButton.CENTER);
		addMember.setVerticalTextPosition(JButton.CENTER);
		addMember.setForeground(Color.white);
		addMember.setFont(f);
		addMember.addActionListener(this);

		users = new JButton(new ImageIcon(config.getBtImage()));
		users.setText("Members");
		users.setBounds(280, 370, 130, 40);
		users.setHorizontalTextPosition(JButton.CENTER);
		users.setVerticalTextPosition(JButton.CENTER);
		users.setForeground(Color.white);
		users.setFont(f);
		users.addActionListener(this);

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

		lastSeen = new JLabel();
		lastSeen.setFont(new Font("serif", Font.PLAIN, 18));
		lastSeen.setText("last seen:");
		lastSeen.setForeground(Color.white);
		lastSeen.setHorizontalTextPosition(JLabel.RIGHT);
		lastSeen.setVerticalTextPosition(JLabel.CENTER);
		lastSeen.setBounds(20, 320, 250, 40);
		lastSeen.setIconTextGap(0);
		lastSeen.setBorder(BorderFactory.createLineBorder(Color.white));

		time = new JLabel();
		time.setFont(f);
		time.setForeground(Color.white);
		time.setHorizontalTextPosition(JLabel.RIGHT);
		time.setVerticalTextPosition(JLabel.CENTER);
		time.setBounds(20, 370, 220, 30);
		time.setIconTextGap(0);
		time.setBorder(BorderFactory.createLineBorder(Color.white));
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(config.getClockImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image img2 = img.getScaledInstance(time.getWidth() - 170, time.getHeight(), Image.SCALE_SMOOTH);
		time.setIcon(new ImageIcon(img2));

	}

	public void setMessageNull() {
		text.setText("");
		time.setText("");
		text.setIcon(null);
		profile.setText("");
		profile.setIcon(null);
		lastSeen.setText("");
	}

	public void setMessage(Message message, User user) {
		text.setText(message.getMessage());
		time.setText(message.getDateandtime().getDateandTime());
		if (message.getImageAddress().equals("default")) {
			text.setIcon(null);
			text.setBounds(10, 190, 270, 20);
		} else {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(message.getImageAddress()));
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
		if (user.username.equals(SocialMedia.getCurrent_user().username))
			lastSeen.setText("last seen: online");
		else
			lastSeen.setText("last seen: " + user.getLastseen().getDate().getDateandTime());
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
			Controller.getInstance().listen("nextGroupChatMessage");
		} else if (e.getSource() == prev) {
			Controller.getInstance().listen("prevGroupChatMessage");
		} else if (e.getSource() == addMember) {
			Controller.getInstance().listen("addNewMember");
		} else if (e.getSource() == message) {
			Controller.getInstance().listen("groupChatMessage");
		} else if (e.getSource() == messagePlus) {
			Controller.getInstance().listen("groupChatMessagePlus");
		} else if (e.getSource() == save) {
			Controller.getInstance().listen("groupChatSaveMessage");
		} else if (e.getSource() == users) {
			Controller.getInstance().listen("groupChatMembers");
		} else if (e.getSource() == edit) {
			Controller.getInstance().listen("groupChatEditMessage");
		} else if (e.getSource() == delete) {
			Controller.getInstance().listen("groupChatDeleteMessage");
		} else if (e.getSource() == forward) {
			Controller.getInstance().listen("groupChatForwardMessage");
		}
	}

}
