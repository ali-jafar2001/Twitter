package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import logic.Controller;
import util.LoginPanelConfig;

public class LoginPanel extends JPanel implements ActionListener {
	private Sign_IN_Panel sign_in_panel;
	private Sign_Up_Panel sign_up_panel;
	private JButton sign_up;
	private LoginPanelConfig config;
	private JButton sign_in;
	private JButton exit;
	private Image background;

	LoginPanel() {
		config = new LoginPanelConfig("LOGIN_CONFIG_FILE");
		init();
		this.add(sign_in);
		this.add(sign_up);
		this.add(exit);
		this.setLayout(null);
	}

	private void init() {
		Font f = new Font("serif", Font.PLAIN, 18);
		sign_in = new JButton(new ImageIcon(config.getBtImage()));
		sign_in.setBounds(200, 100, config.getBtWidth(), config.getBtHeight() - 10);
		sign_in.setText("SIGN IN");
		sign_in.setHorizontalTextPosition(JButton.CENTER);
		sign_in.setVerticalTextPosition(JButton.CENTER);
		sign_in.setForeground(Color.white);
		sign_in.setFont(f);
		sign_in.addActionListener(this);

		sign_up = new JButton(new ImageIcon(config.getBtImage()));
		sign_up.setText("SIGN UP");
		sign_up.setHorizontalTextPosition(JButton.CENTER);
		sign_up.setVerticalTextPosition(JButton.CENTER);
		sign_up.setForeground(Color.white);
		sign_up.setBounds(200, 160, config.getBtWidth(), config.getBtHeight() - 10);
		sign_up.setFont(f);
		sign_up.addActionListener(this);

		exit = new JButton(new ImageIcon(config.getBtImage()));
		exit.setText("EXIT");
		exit.setHorizontalTextPosition(JButton.CENTER);
		exit.setVerticalTextPosition(JButton.CENTER);
		exit.setForeground(Color.white);
		exit.setBounds(20, 690, config.getBtWidth(), config.getBtHeight() - 10);
		exit.setFont(f);
		exit.addActionListener(this);

		sign_in_panel = new Sign_IN_Panel();
		sign_up_panel = new Sign_Up_Panel();
		sign_in_panel.setBounds(350, 270, 400, 300);
		sign_up_panel.setBounds(350, 270, 400, 300);
		sign_up_panel.setVisible(false);
		sign_in_panel.setVisible(false);
		this.add(sign_in_panel);
		this.add(sign_up_panel);

		background = Toolkit.getDefaultToolkit().getImage(config.getBcImage());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(background, -300, -300, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			Controller.getInstance().listen("exit button");
		} else if (e.getSource() == sign_in) {
			Controller.getInstance().listen("sign in button");
		} else if (e.getSource() == sign_up) {
			Controller.getInstance().listen("sign up button");
		}
	}

	public void addSignInPanel() {
		sign_up_panel.setVisible(false);
		sign_in_panel.setVisible(true);
	}

	public void addSignUpPanel() {
		sign_in_panel.setVisible(false);
		sign_up_panel.setVisible(true);
	}

	public String getEmail() {
		return this.sign_up_panel.getEmail();
	}

	public String getFname() {
		return this.sign_up_panel.getFname();
	}

	public String getLname() {
		return this.sign_up_panel.getLname();
	}

	public String getSignUpUsername() {
		return this.sign_up_panel.getUsername();
	}

	public String getSignUpPassword() {
		return this.sign_up_panel.getPassword();
	}

	public String getSignInUsername() {
		return this.sign_in_panel.getUsername();
	}

	public String getSignInPassword() {
		return this.sign_in_panel.getPassword();
	}

}

class Sign_IN_Panel extends JPanel implements ActionListener {
	private LoginPanelConfig config;
	private JLabel usernameText;
	private JLabel passwordText;
	private JTextField username;
	private JPasswordField password;
	private JButton confirm;
	private Image bcImage;

	public String getUsername() {
		return username.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(bcImage, 0, 0, this);
	}

	public Sign_IN_Panel() {
		super();
		config = new LoginPanelConfig("LOGIN_CONFIG_FILE");
		this.setLayout(null);
		this.setSize(new Dimension(200, 300));
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		bcImage = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		init();
		this.add(confirm);
		this.add(password);
		this.add(passwordText);
		this.add(username);
		this.add(usernameText);
	}

	private void init() {
		Font font = new Font("serif", Font.PLAIN, 18);
		confirm = new JButton(new ImageIcon(config.getBtImage()));
		confirm.setFont(font);
		confirm.setText("Confirm");
		confirm.setBounds(70, 110, 110, 30);
		confirm.setHorizontalTextPosition(JButton.CENTER);
		confirm.setVerticalTextPosition(JButton.CENTER);
		confirm.setForeground(Color.white);
		confirm.addActionListener(this);

		password = new JPasswordField();
		password.setBounds(110, 65, 110, 30);

		username = new JTextField();
		username.setFont(font);
		username.setBounds(110, 25, 110, 30);

		usernameText = new JLabel("Username:");
		usernameText.setFont(font);
		usernameText.setBounds(20, 20, 110, 30);
		usernameText.setForeground(Color.white);

		passwordText = new JLabel("Password:");
		passwordText.setFont(font);
		passwordText.setBounds(20, 60, 110, 30);
		passwordText.setForeground(Color.white);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirm) {
			Controller.getInstance().listen("Sign in");
			setEmpty();
		}
	}
	private void setEmpty() {
		username.setText("");
		password.setText("");
	}
}

class Sign_Up_Panel extends JPanel implements ActionListener {
	private LoginPanelConfig config;
	private JLabel usernameText, passwordText, emailText, fnameText, LnameText;
	private JTextField username, email, fname, lname;
	private JPasswordField password;
	private JButton confirm;
	private Image bcImage;

	public String getUsername() {
		return username.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	public String getEmail() {
		return email.getText();
	}

	public String getLname() {
		return lname.getText();
	}

	public String getFname() {
		return fname.getText();
	}

	public Sign_Up_Panel() {
		super();
		config = new LoginPanelConfig("LOGIN_CONFIG_FILE");
		this.setLayout(null);
		this.setSize(new Dimension(200, 300));
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		bcImage = Toolkit.getDefaultToolkit().getImage(config.getObcImage());
		init();
		this.add(LnameText);
		this.add(confirm);
		this.add(email);
		this.add(emailText);
		this.add(fname);
		this.add(fnameText);
		this.add(lname);
		this.add(password);
		this.add(passwordText);
		this.add(username);
		this.add(usernameText);

	}

	private void init() {
		Font font = new Font("serif", Font.PLAIN, 18);
		confirm = new JButton(new ImageIcon(config.getBtImage()));
		confirm.setFont(font);
		confirm.setText("Confirm");
		confirm.setBounds(70, 230, 110, 30);
		confirm.setHorizontalTextPosition(JButton.CENTER);
		confirm.setVerticalTextPosition(JButton.CENTER);
		confirm.setForeground(Color.white);
		confirm.addActionListener(this);

		password = new JPasswordField();
		password.setBounds(110, 105, 110, 30);

		username = new JTextField();
		username.setFont(font);
		username.setBounds(110, 65, 110, 30);

		usernameText = new JLabel("Username:");
		usernameText.setFont(font);
		usernameText.setBounds(20, 60, 110, 30);
		usernameText.setForeground(Color.white);

		passwordText = new JLabel("Password:");
		passwordText.setFont(font);
		passwordText.setBounds(20, 100, 110, 30);
		passwordText.setForeground(Color.white);

		email = new JTextField();
		email.setFont(font);
		email.setBounds(110, 25, 110, 30);

		fname = new JTextField();
		fname.setFont(font);
		fname.setBounds(110, 145, 110, 30);

		lname = new JTextField();
		lname.setFont(font);
		lname.setBounds(110, 185, 110, 30);

		emailText = new JLabel("Email:");
		emailText.setFont(font);
		emailText.setBounds(20, 20, 110, 30);
		emailText.setForeground(Color.white);

		fnameText = new JLabel("First name:");
		fnameText.setFont(font);
		fnameText.setBounds(20, 140, 110, 30);
		fnameText.setForeground(Color.white);

		LnameText = new JLabel("Last name:");
		LnameText.setFont(font);
		LnameText.setBounds(20, 180, 110, 30);
		LnameText.setForeground(Color.white);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(bcImage, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirm) {
			Controller.getInstance().listen("Sign up");
			setEmpty();
		}
	}
	private void setEmpty() {
		username.setText("");
		password.setText("");
		email.setText("");
		fname.setText("");
		lname.setText("");
	}

}