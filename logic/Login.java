package logic;

import java.io.IOException;





import models.DateandTime;
import models.User;
import panels.MainFrame;

public class Login {
	private static Login login;
	public static boolean pre = true;

	public static Login getInstance() {
		if (login == null)
			login = new Login();
		return login;
	}

	public void signOut() {
		SocialMedia.getCurrent_user().getLastseen().setDate(new DateandTime());
		SocialMedia.setCurrent_user(null);
		try {
			Logger.info("user left the program");
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainFrame.getInstance().backToLoginPanel();
	}

	public void signUp() {
		String username = MainFrame.getInstance().getSignUpUsername();
		String password = MainFrame.getInstance().getSignUpPassword();
		String email = MainFrame.getInstance().getEmail();
		String fname = MainFrame.getInstance().getFname();
		String lname = MainFrame.getInstance().getLname();
		if (!SocialMedia.isThere(username)) {
			User user = new User(username, password, email, fname, lname);
			Saver.svaeUser(user);
			try {
				Saver.saveName(username);
			} catch (IOException e) {
				e.printStackTrace();
			}
			SocialMedia.setCurrent_user(user);
			try {
				Logger.info("account created successfully");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MainFrame.getInstance().showSignUpMessage();
			MainFrame.getInstance().createPanels();
		} else {
			try {
				Logger.warn("tried to sign up with an illegal usrname");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainFrame.getInstance().showUncorrectNameMessage();
		}
	}

	public void signIn() {
		String username = MainFrame.getInstance().getSignInUsername();
		String password = MainFrame.getInstance().getSignInPassword();
		if (SocialMedia.isThere(username)) {
			User user = SocialMedia.getPerson(username);
			if (user.getPassword().equals(password)) {
				SocialMedia.setCurrent_user(user);
				try {
					Logger.info("user signed in successfully");
				} catch (IOException e) {
					e.printStackTrace();
				}
				MainFrame.getInstance().showSignInMessage();
				MainFrame.getInstance().createPanels();
			} else {
				try {
					Logger.warn("rquest with illegal password occured");
				} catch (IOException e) {
					e.printStackTrace();
				}
				MainFrame.getInstance().showUncorrectPasswordMessage();
			}
		} else {
			try {
				Logger.warn("request with an unknown username occured");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainFrame.getInstance().showUncorrectedUsernameMessage();
		}
	}

//	public static void login(Scanner scanner) throws IOException {
//		while (pre) {
//			ConsoleColors.changeColor("yellow");
//			System.out.println("Already have an account(y/n)?");
//			ConsoleColors.changeColor("blue");
//			String ans = scanner.nextLine();
//			if (ans.equals("y")) {
//				sign_in(scanner);
//			} else if (ans.equals("n")) {
//				sign_up(scanner);
//			} else {
//				ConsoleColors.changeColor("red");
//				System.out.println("unknown command!!!");
//				Logger.warn("Unknown command entered at console");
//			}
//		}
//
//	}
//
//	private static void sign_up(Scanner scanner) throws IOException {
//		ConsoleColors.changeColor("yellow");
//		System.out.println("Enter your Email Address?");
//		ConsoleColors.changeColor("blue");
//		String ans = scanner.nextLine();
//		if (!SocialMedia.isCorrectEmail(ans)) {
//			ConsoleColors.changeColor("red");
//			System.out.println("Email does not exsists!!!");
//			Logger.warn("tried to sign up with an incorrecr Email address");
//		} else {
//			ConsoleColors.changeColor("green");
//			System.out.println("your Email Address is registered successfully");
//			String Email = ans;
//			ConsoleColors.changeColor("yellow");
//			System.out.println("Enter your Username??");
//			ConsoleColors.changeColor("blue");
//			ans = scanner.nextLine();
//			if (!SocialMedia.isThere(ans)) {
//				ConsoleColors.changeColor("green");
//				System.out.println("your Username is registered successfully");
//				String Username = ans;
//				ConsoleColors.changeColor("yellow");
//				System.out.println("Enter your Password??");
//				ConsoleColors.changeColor("blue");
//				ans = scanner.nextLine();
//				ConsoleColors.changeColor("green");
//				System.out.println("your password is registered successfully");
//				String Password = ans;
//				ConsoleColors.changeColor("yellow");
//				System.out.println("Enter your first name??");
//				ConsoleColors.changeColor("blue");
//				ans = scanner.nextLine();
//				ConsoleColors.changeColor("green");
//				System.out.println("your first name is registered successfully");
//				String firstname = ans;
//				ConsoleColors.changeColor("yellow");
//				System.out.println("Enter your last name??");
//				ConsoleColors.changeColor("blue");
//				ans = scanner.nextLine();
//				ConsoleColors.changeColor("green");
//				System.out.println("your last name is registered successfully");
//				String lastname = ans;
//				User person = new User(Username, Password, Email, firstname, lastname);
//				Saver.svaeUser(person);
//				Saver.saveName(Username);
//				ConsoleColors.changeColor("green");
//				System.out.println("your account is registered successfully");
//				SocialMedia.setCurrent_user(person);
//				Logger.info("account created successfully");
//				pre = false;
//				CommandLine.menu = true;
//			} else {
//				ConsoleColors.changeColor("red");
//				Logger.warn("tried to sign up with an illegal usrname");
//				System.out.println("There is an account with this username!!");
//			}
//		}
//	}
//
//	private static void sign_in(Scanner scanner) throws IOException {
//		ConsoleColors.changeColor("yellow");
//		System.out.println("Enter your Username??");
//		ConsoleColors.changeColor("blue");
//		String ans = scanner.nextLine();
//		if (SocialMedia.isThere(ans)) {
//			User temp = SocialMedia.getPerson(ans);
//			ConsoleColors.changeColor("yellow");
//			System.out.println("Enter your password??");
//			ConsoleColors.changeColor("blue");
//			ans = scanner.nextLine();
//			if (ans.equals(temp.getPassword())) {
//				ConsoleColors.changeColor("green");
//				SocialMedia.setCurrent_user(temp);
//				System.out.println("You entered as '" + temp.username + "' successfully");
//				Logger.info("user signed in successfully");
//				pre = false;
//				CommandLine.menu = true;
//			} else {
//				ConsoleColors.changeColor("red");
//				Logger.warn("rquest with illegal password occured");
//				System.out.println("This password is not correct!!");
//			}
//		} else {
//			ConsoleColors.changeColor("red");
//			Logger.warn("request with an unknown username occured");
//			System.out.println("There is no account with this username");
//		}
//	}
}
