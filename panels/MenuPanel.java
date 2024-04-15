package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import logic.Controller;
import util.MenuConfig;

public class MenuPanel extends JPanel implements ActionListener {
	private MenuConfig config;
	private Image background, birdImage, pmImage;
	private int btWidth, btHeight;
	private JButton exit;
	private JButton sign_out;
	private JButton setting;
	private JButton timeLine;
	private JButton messaging;
	private JButton page;
	private JButton explorer;
	private JButton commandLine;

	public MenuPanel() {
		super();
		config = new MenuConfig("MENU_CONFIG_FILE");
		init();
		this.setLayout(null);
		this.add(commandLine);
		this.add(sign_out);
		this.add(exit);
		this.add(explorer);
		this.add(messaging);
		this.add(page);
		this.add(setting);
		this.add(timeLine);
		setting.addActionListener(this);
	}

	private void init() {
		background = Toolkit.getDefaultToolkit().getImage(config.getBcImage());
		birdImage = Toolkit.getDefaultToolkit().getImage(config.getBirdImage());
		pmImage = Toolkit.getDefaultToolkit().getImage(config.getPmImage());
		Font f = new Font("serif", Font.PLAIN, 20);
		btWidth = config.getBtWidth();
		btHeight = config.getBtHeight();

		exit = new JButton(new ImageIcon(config.getBtImage()));
		exit.setHorizontalTextPosition(JButton.CENTER);
		exit.setVerticalTextPosition(JButton.CENTER);
		exit.setForeground(Color.white);
		exit.setFont(f);
		exit.setText("EXIT");
		exit.setBounds(20, 690, btWidth - 50, btHeight);
		exit.addActionListener(this);

		sign_out = new JButton(new ImageIcon(config.getBtImage()));
		sign_out.setHorizontalTextPosition(JButton.CENTER);
		sign_out.setVerticalTextPosition(JButton.CENTER);
		sign_out.setForeground(Color.white);
		sign_out.setFont(f);
		sign_out.setText("SIGN OUT");
		sign_out.setBounds(550, 580, btWidth, btHeight);
		sign_out.addActionListener(this);

		setting = new JButton(new ImageIcon(config.getBtImage()));
		setting.setHorizontalTextPosition(JButton.CENTER);
		setting.setVerticalTextPosition(JButton.CENTER);
		setting.setForeground(Color.white);
		setting.setFont(f);
		setting.setText("SETTING");
		setting.setBounds(550, 520, btWidth, btHeight);
		setting.addActionListener(this);

		timeLine = new JButton(new ImageIcon(config.getBtImage()));
		timeLine.setHorizontalTextPosition(JButton.CENTER);
		timeLine.setVerticalTextPosition(JButton.CENTER);
		timeLine.setForeground(Color.white);
		timeLine.setFont(f);
		timeLine.setText("TIME LINE");
		timeLine.setBounds(550, 340, btWidth, btHeight);
		timeLine.addActionListener(this);

		messaging = new JButton(new ImageIcon(config.getBtImage()));
		messaging.setHorizontalTextPosition(JButton.CENTER);
		messaging.setVerticalTextPosition(JButton.CENTER);
		messaging.setForeground(Color.white);
		messaging.setFont(f);
		messaging.setText("MESSAGING");
		messaging.setBounds(550, 280, btWidth, btHeight);
		messaging.addActionListener(this);

		page = new JButton(new ImageIcon(config.getBtImage()));
		page.setHorizontalTextPosition(JButton.CENTER);
		page.setVerticalTextPosition(JButton.CENTER);
		page.setForeground(Color.white);
		page.setFont(f);
		page.setText("PAGE");
		page.setBounds(550, 220, btWidth, btHeight);
		page.addActionListener(this);

		explorer = new JButton(new ImageIcon(config.getBtImage()));
		explorer.setHorizontalTextPosition(JButton.CENTER);
		explorer.setVerticalTextPosition(JButton.CENTER);
		explorer.setForeground(Color.white);
		explorer.setFont(f);
		explorer.setText("EXPLORER");
		explorer.setBounds(550, 400, btWidth, btHeight);
		explorer.addActionListener(this);

		commandLine = new JButton(new ImageIcon(config.getBtImage()));
		commandLine.setHorizontalTextPosition(JButton.CENTER);
		commandLine.setVerticalTextPosition(JButton.CENTER);
		commandLine.setForeground(Color.white);
		commandLine.setFont(f);
		commandLine.setText("COMMAND LINE");
		commandLine.setBounds(550, 460, btWidth, btHeight);
		commandLine.addActionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(background, -300, -300, this);
		g2D.drawImage(birdImage, 150, 0, this);
		g2D.drawImage(pmImage, 1050, 500, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			Controller.getInstance().listen("exit button");
		}
		if (e.getSource() == sign_out) {
			Controller.getInstance().listen("sign out");
		}
		if (e.getSource() == setting) {
			Controller.getInstance().listen("setting");
		}
		if (e.getSource() == commandLine) {
			Controller.getInstance().listen("command line");
		}
		if (e.getSource() == page) {
			Controller.getInstance().listen("page");
		}
		if (e.getSource() == timeLine) {
			Controller.getInstance().listen("time line");
		}
		if (e.getSource() == explorer) {
			Controller.getInstance().listen("explorer");
		}
		if (e.getSource() == messaging) {
			Controller.getInstance().listen("messaging");
		}
	}
}
