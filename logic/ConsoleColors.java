package logic;



public class ConsoleColors {
	public static void changeColor(String st) {
		switch (st) {
		case "green": {
			System.out.print("\033[0;32m");
			break;
		}
		case "red": {
			System.out.print("\033[0;31m");
			break;
		}
		case "blue": {
			System.out.print("\033[0;34m");
			break;
		}
		case "yellow": {
			System.out.print("\033[0;33m");
			break;
		}
		default: {
			System.out.print("\033[0m");
			break;
		}

		}
	}
}
