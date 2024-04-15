package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.DateandTime;

public class Logger {
	private static File file = new File("src\\resource\\log\\log.txt");
	private static FileWriter filewriter;
	private static Scanner scanner;
	private static PrintWriter printwriter;

	public static void info(String st) throws IOException {
		String username = "Unknown";
		if (SocialMedia.getCurrent_user() != null)
			username = SocialMedia.getCurrent_user().username;
		write();
		printwriter.print("[info] ");
		DateandTime date = new DateandTime();
		printwriter.print(date.getTime() + " " + date.getDate());
		printwriter.print(" User." + username + " ");
		printwriter.print(":" + st);
		printwriter.println();
		printwriter.close();
	}

	public static void warn(String st) throws IOException {
		String username = "Unknown";
		if (SocialMedia.getCurrent_user() != null)
			username = SocialMedia.getCurrent_user().username;
		write();
		printwriter.print("[warn] ");
		DateandTime date = new DateandTime();
		printwriter.print(date.getTime() + " " + date.getDate());
		printwriter.print(" User." + username + " ");
		printwriter.print(":" + st);
		printwriter.println();
		printwriter.close();
	}

	private static void write() throws IOException {
		scanner = new Scanner(file);
		List<String> words = new ArrayList<>();
		while (scanner.hasNextLine()) {
			words.add(scanner.nextLine());
		}
		scanner.close();
		filewriter = new FileWriter(file);
		printwriter = new PrintWriter(filewriter);
		for (String st : words) {
			printwriter.println(st);
		}
	}
}
