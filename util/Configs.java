package util;

import java.util.Properties;

public class Configs extends Properties {
	public int readInteger(String name) {
		return Integer.parseInt(this.getProperty(name));
	}

	public boolean readBoolean(String name) {
		return Boolean.parseBoolean(this.getProperty(name));
	}
}
