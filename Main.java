package Main;

import util.ConfigLoader;


import java.io.FileNotFoundException;
import java.io.IOException;
import panels.MainFrame;


public class Main {

	public static void main(String[] args) throws IOException {
//	new CommandLine();	
		Main main = new Main(args);
	}

	public Main(String[] args) throws FileNotFoundException {
		ConfigLoader urls = ConfigLoader.getInstance(getConfigFile(args));
		MainFrame.start();
	}

	private String getConfigFile(String[] args) {
		String configAddress = "default";
		if (args.length > 1) {
			configAddress = args[1];
		} else {
			if (System.getenv("TWEETER_CONFIG") != null && !System.getenv("TWEETER_CONFIG").isEmpty()) {
				configAddress = System.getenv("TWEETER_CONFIG");
			}
		}
		return configAddress;
	}
}
