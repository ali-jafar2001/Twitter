package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import logic.Controller;
import logic.SocialMedia;
import util.SettingConfig;

public class SettingPanel extends JPanel implements ActionListener {
	private SettingConfig config;
	private Image background;
	private JButton back, delete;
	private JLabel status, lastSeen, access;
	private StatusPanel statusPanel;
	private AccessPanel accessPanel;
	private LastSeenPanel lastSeenPanel;

	public void reset() {
		status.setText("Status: " + SocialMedia.getCurrent_user().getStatus());
		lastSeen.setText("Last seen: " + SocialMedia.getCurrent_user().getLastseen().getLevel());
		access.setText("Access: " + SocialMedia.getCurrent_user().getAccesslevel());
	}

	public SettingPanel() {
		this.setLayout(null);
		config = new SettingConfig("SETTING_CONFIG_FILE");
		init();
		this.add(back);
		this.add(delete);
		this.add(access);
		this.add(lastSeen);
		this.add(status);
		this.add(accessPanel);
		this.add(lastSeenPanel);
		this.add(statusPanel);
	}

	private void init() {
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

		delete = new JButton(new ImageIcon(config.getBtImage()));
		delete.setText("Delete Account");
		delete.setBounds(1200, 710, config.getBtWidth(), config.getBtHeight() - 10);
		delete.setHorizontalTextPosition(JButton.CENTER);
		delete.setVerticalTextPosition(JButton.CENTER);
		delete.setForeground(Color.white);
		delete.setFont(f);
		delete.addActionListener(this);

		statusPanel = new StatusPanel();
		statusPanel.setBounds(100, 150, 300, 400);

		status = new JLabel("Status: " + SocialMedia.getCurrent_user().getStatus());
		status.setFont(f);
		status.setForeground(Color.DARK_GRAY);
		status.setBounds(240, 100, config.getBtWidth(), config.getBtHeight() - 10);

		lastSeenPanel = new LastSeenPanel();
		lastSeenPanel.setBounds(550, 150, 300, 400);

		lastSeen = new JLabel("Last seen: " + SocialMedia.getCurrent_user().getLastseen().getLevel());
		lastSeen.setFont(f);
		lastSeen.setForeground(Color.DARK_GRAY);
		lastSeen.setBounds(650, 100, config.getBtWidth() + 30, config.getBtHeight() - 10);

		accessPanel = new AccessPanel();
		accessPanel.setBounds(1000, 150, 300, 400);

		access = new JLabel("Access: " + SocialMedia.getCurrent_user().getAccesslevel());
		access.setFont(f);
		access.setForeground(Color.DARK_GRAY);
		access.setBounds(1140, 100, config.getBtWidth(), config.getBtHeight() - 10);
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
			Controller.getInstance().listen("settingToMenu");
		}
		if (e.getSource() == delete) {
			Controller.getInstance().listen("delete account");
		}
	}

	public String status() {
		return this.statusPanel.isSelected();
	}

	public void setStatus() {
		status.setText("Status: " + SocialMedia.getCurrent_user().getStatus());
	}

	public void setAccess() {
		access.setText("Access: " + SocialMedia.getCurrent_user().getAccesslevel());
	}

	public void setLastSeen() {
		lastSeen.setText("Last seen: " + SocialMedia.getCurrent_user().getLastseen().getLevel());
	}

	public String access() {
		return this.accessPanel.isSelected();
	}

	public String lastSeen() {
		return this.lastSeenPanel.isSelected();
	}
}

class StatusPanel extends JPanel implements ActionListener {
	private JRadioButton active, deactive;
	private ButtonGroup buttonGroup;
	private JButton confirm;
	private SettingConfig config;
	private Image background;

	public StatusPanel() {
		super();
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		config = new SettingConfig("SETTING_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		init();
		this.add(active);
		this.add(confirm);
		this.add(deactive);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(background, 0, 0, this);
	}

	private void init() {
		Font f = new Font("serif", Font.PLAIN, 18);
		confirm = new JButton(new ImageIcon(config.getBtImage()));
		confirm.setText("Confirm");
		confirm.setBounds(100, 350, 100, 30);
		confirm.setHorizontalTextPosition(JButton.CENTER);
		confirm.setVerticalTextPosition(JButton.CENTER);
		confirm.setForeground(Color.white);
		confirm.setFont(f);
		confirm.addActionListener(this);

		deactive = new JRadioButton();
		deactive.setText("inActive");
		deactive.setBounds(20, 20, 120, 30);
		deactive.setFont(f);
		deactive.setForeground(Color.white);
		deactive.setBackground(Color.DARK_GRAY);

		active = new JRadioButton();
		active.setText("Active");
		active.setBounds(20, 120, 120, 30);
		active.setFont(f);
		active.setForeground(Color.white);
		active.setBackground(Color.DARK_GRAY);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(active);
		buttonGroup.add(deactive);
	}

	public String isSelected() {
		String st = "active";
		if (deactive.isSelected())
			st = "inActive";
		return st;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirm) {
			Controller.getInstance().listen("status");
		}
	}
}

class LastSeenPanel extends JPanel implements ActionListener {
	private JRadioButton noBody, everyBody, contacts;
	private ButtonGroup buttonGroup;
	private JButton confirm;
	private SettingConfig config;
	private Image background;

	public LastSeenPanel() {
		super();
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		config = new SettingConfig("SETTING_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		init();
		this.add(confirm);
		this.add(contacts);
		this.add(everyBody);
		this.add(noBody);
	}

	private void init() {
		Font f = new Font("serif", Font.PLAIN, 18);
		confirm = new JButton(new ImageIcon(config.getBtImage()));
		confirm.setText("Confirm");
		confirm.setBounds(100, 350, 100, 30);
		confirm.setHorizontalTextPosition(JButton.CENTER);
		confirm.setVerticalTextPosition(JButton.CENTER);
		confirm.setForeground(Color.white);
		confirm.setFont(f);
		confirm.addActionListener(this);

		everyBody = new JRadioButton();
		everyBody.setText("everyBody");
		everyBody.setBounds(20, 20, 120, 30);
		everyBody.setFont(f);
		everyBody.setForeground(Color.white);
		everyBody.setBackground(Color.DARK_GRAY);

		contacts = new JRadioButton();
		contacts.setText("contacts");
		contacts.setBounds(20, 120, 120, 30);
		contacts.setFont(f);
		contacts.setForeground(Color.white);
		contacts.setBackground(Color.DARK_GRAY);

		noBody = new JRadioButton();
		noBody.setText("noBody");
		noBody.setBounds(20, 220, 120, 30);
		noBody.setFont(f);
		noBody.setForeground(Color.white);
		noBody.setBackground(Color.DARK_GRAY);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(noBody);
		buttonGroup.add(everyBody);
		buttonGroup.add(contacts);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(background, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirm) {
			Controller.getInstance().listen("lastSeen");
		}
	}

	public String isSelected() {
		String st = "everyBody";
		if (noBody.isSelected())
			st = "noBody";
		if (contacts.isSelected())
			st = "contacts";
		return st;
	}
}

class AccessPanel extends JPanel implements ActionListener {
	private JRadioButton pub, pri;
	private ButtonGroup buttonGroup;
	private JButton confirm;
	private SettingConfig config;
	private Image background;

	public AccessPanel() {
		super();
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		config = new SettingConfig("SETTING_CONFIG_FILE");
		background = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		init();
		this.add(confirm);
		this.add(pri);
		this.add(pub);
	}

	private void init() {
		Font f = new Font("serif", Font.PLAIN, 18);
		confirm = new JButton(new ImageIcon(config.getBtImage()));
		confirm.setText("Confirm");
		confirm.setBounds(100, 350, 100, 30);
		confirm.setHorizontalTextPosition(JButton.CENTER);
		confirm.setVerticalTextPosition(JButton.CENTER);
		confirm.setForeground(Color.white);
		confirm.setFont(f);
		confirm.addActionListener(this);

		pub = new JRadioButton();
		pub.setText("public");
		pub.setBounds(20, 20, 120, 30);
		pub.setFont(f);
		pub.setForeground(Color.white);
		pub.setBackground(Color.DARK_GRAY);

		pri = new JRadioButton();
		pri.setText("private");
		pri.setBounds(20, 120, 120, 30);
		pri.setFont(f);
		pri.setForeground(Color.white);
		pri.setBackground(Color.DARK_GRAY);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(pub);
		buttonGroup.add(pri);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(background, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirm) {
			Controller.getInstance().listen("access");
		}
	}

	public String isSelected() {
		String st = "public";
		if (pri.isSelected())
			st = "private";
		return st;
	}
}
