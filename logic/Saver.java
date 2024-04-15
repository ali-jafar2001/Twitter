package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import models.User;

public class Saver {
	private static File file = new File("src\\resource\\data\\users.txt");

	public static void deleteUser(User user) {
		String username = user.username;
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine())
				list.add(scanner.nextLine());
			for (String st : list) {
				if (!st.equals(username))
					list2.add(st);
			}
			scanner.close();
			FileWriter filewriter = new FileWriter(file);
			PrintWriter printwriter = new PrintWriter(filewriter);
			for (String st : list2)
				printwriter.println(st);
			filewriter.close();
			File f = new File("src\\resource\\data\\" + username + ".txt");
			f.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void svaeUser(User person) {
//		File file = new File("src\\data\\users.txt");
		try {
			File f = new File("src\\resource\\data\\" + person.username + ".txt");
			if (!f.exists())
				f.createNewFile();

			SocialMedia.getUsers().add(person);
			FileWriter filewriter = new FileWriter(f);
			PrintWriter printwriter = new PrintWriter(filewriter);
//			printwriter.println("  [");
			Gson gson = new Gson();
//			for (User p : SocialMedia.getUsers()) {
			String jasonuser = gson.toJson(person);
			printwriter.println(jasonuser);
//			}
//			printwriter.println("  ]");
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save() {
//		File file = new File("src\\data\\users.txt");
		try {
			for (User user : SocialMedia.getUsers()) {
				File f = new File("src\\resource\\data\\" + user.username + ".txt");
				FileWriter filewriter = new FileWriter(f);
				PrintWriter printwriter = new PrintWriter(filewriter);
				Gson gson = new Gson();
				String jasonuser = gson.toJson(user);
				printwriter.println(jasonuser);
				filewriter.close();
			}
//			FileWriter filewriter = new FileWriter(file);
//			PrintWriter printwriter = new PrintWriter(filewriter);
//			printwriter.println("  [");
//			Gson gson = new Gson();
//			for (User p : SocialMedia.getUsers()) {
//				String jasonuser = gson.toJson(p);
//				printwriter.println("   " + jasonuser);
//			}
//			printwriter.println("  ]");
//			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void saveName(String name) throws IOException {
		Scanner scanner = new Scanner(file);
		Gson gson = new Gson();
		ArrayList<String> list = new ArrayList<String>();
		while (scanner.hasNextLine()) {
			String st = scanner.nextLine();
			list.add(st);
		}
		list.add(name);
		scanner.close();
		FileWriter filewriter = new FileWriter(file);
		PrintWriter printwriter = new PrintWriter(filewriter);
		for (String st : list) {
			printwriter.println(st);
		}
		filewriter.close();
	}

	public static void loadUsers() {
//		File file = new File("src\\data\\users.txt");
		try {
			Scanner scanner = new Scanner(file);
			Gson gson = new Gson();
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<User> list = new ArrayList<User>();
			while (scanner.hasNextLine()) {
				String st = scanner.nextLine();
				names.add(st);
//				if (!(st.equals("  [") || st.equals("  ]"))) {
//					User p = gson.fromJson(st, User.class);
//					if (p != null)
//						list.add(p);
//				}
			}
			for (String st : names) {
				File f = new File("src\\resource\\data\\" + st + ".txt");
				String s = "";
				scanner = new Scanner(f);
				while (scanner.hasNextLine()) {
					s = s + scanner.nextLine();
				}
				User p = gson.fromJson(s, User.class);
				list.add(p);
			}
			SocialMedia.setUsers(list);
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
