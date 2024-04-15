package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import javax.swing.JScrollPane;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import logic.ConsoleColors;

public class CommandLineFrame {

	private JFrame frame;
	private JTextPane console;
	private JTextField input;
	private JScrollPane scrollpane;
	private StyledDocument document;
	private List<String> recent_used;
	private int recent_used_id = 0;
	private int recent_used_maximum = 10;

	public CommandLineFrame() {
		recent_used = new ArrayList<String>();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		InitFrame();
		InitConsole();
		InitInput();
		InitScrollPane();
		addComponents();
		ListenAction();
		ListenKeyAction();
	}

	private void ListenKeyAction() {
		input.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP: {
					if (recent_used_id < (recent_used_maximum - 1) && recent_used_id < (recent_used.size() - 1)) {
						recent_used_id++;
					}
					input.setText(recent_used.get(recent_used.size() - 1 - recent_used_id));
					break;
				}
				case KeyEvent.VK_DOWN: {
					if (recent_used_id > 0) {
						recent_used_id--;
					}
					input.setText(recent_used.get(recent_used.size() - 1 - recent_used_id));
					break;
				}
				}
			}
		});

	}

	private void ListenAction() {
		input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = input.getText();
				if (text.length() > 1) {
					recent_used.add(text);
					recent_used_id = 0;
					println(text, Color.white);
					try {
						doCommand(text);
					} catch (IOException e) {
						e.printStackTrace();
					}
					ScrollBottom();
					input.selectAll();
				}
			}

		});
	}

	private void println(String text, Color color) {
		print(text + "\n", color);
	}

	private void clear() {
		try {
			document.remove(0, document.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	private void ScrollBottom() {
		console.setCaretPosition(console.getDocument().getLength());
	}

	private void doCommand(String st) throws IOException {
		switch (st) {
		case "clear": {
			clear();
			break;
		}
		case "help": {
			Help();
			break;
		}
		default: {
			println("'" + st + "'" + " is not recgnized as a command", Color.red);
		}
		}
	}

	private void Help() {
		println("use these commands to continue", Color.yellow);
		println("'page': go to your own page and manage your tweetes", Color.green);
		println("'timeline': see the other tweets", Color.green);
		println("'explorer': see other users ", Color.green);
		println("'messaging': manage your chats ", Color.green);
		println("'setting': manage your own profile and privacy settings", Color.green);
		println("'help': show all valid commands", Color.green);
	}

	private void InitFrame() {
		frame = new JFrame("console");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void InitConsole() {
		console = new JTextPane();
		console.setEditable(false);
		console.setFont(new Font("Courier New", Font.PLAIN, 15));
		console.setOpaque(false);
		document = console.getStyledDocument();
	}

	private void InitInput() {
		input = new JTextField();
		input.setEditable(true);
		input.setForeground(Color.white);
		input.setCaretColor(Color.white);
		input.setOpaque(false);

	}

	private void InitScrollPane() {
		scrollpane = new JScrollPane(console);
		scrollpane.setBorder(null);
		scrollpane.setOpaque(false);
		scrollpane.getViewport().setOpaque(false);
	}

	private void addComponents() {
		frame.add(input, BorderLayout.SOUTH);
		frame.add(scrollpane, BorderLayout.CENTER);
		frame.getContentPane().setBackground(Color.black);
		frame.setSize(700, 450);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
