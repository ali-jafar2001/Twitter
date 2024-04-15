package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class ConfigLoader {
	private static ConfigLoader loader;
	private String addressName;
	private HashMap<String, Configs> addressess;
	private static String defaultAddress = "src/resource/configfiles/MainConfigFile";
	private HashMap<String, Configs> frameConfigs;
	private HashMap<String, Configs> loginpanelConfigs;
	private HashMap<String, Configs> menupanelConfigs;
	private HashMap<String, Configs> settingpanelConfigs;
	private HashMap<String, Configs> pagePanelConfigs;
	private HashMap<String, Configs> timeLinePanelConfigs;
	private HashMap<String, Configs> explorerPanelConfigs;
	private HashMap<String, Configs> messagingPanelConfigs;
	private HashMap<String, Configs> propeties;

	private ConfigLoader(String address) throws FileNotFoundException {
		initialize(address);
	}

	public static ConfigLoader getInstance() throws FileNotFoundException {
		return getInstance("default");
	}

	private void initialize(String address) throws FileNotFoundException {
		FileReader reader;
		addressName = "RESOURCE_URL";

		frameConfigs = new HashMap<>();
		loginpanelConfigs = new HashMap<>();
		addressess = new HashMap<>();
		menupanelConfigs = new HashMap<>();
		settingpanelConfigs = new HashMap<>();
		pagePanelConfigs = new HashMap<>();
		timeLinePanelConfigs = new HashMap<>();
		explorerPanelConfigs = new HashMap<>();
		messagingPanelConfigs = new HashMap<>();
		propeties = new HashMap<>();
		try {
			Configs addresses = new Configs();

			reader = new FileReader(address);
			addresses.load(reader);
			this.addressess.put(addressName, addresses);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("main config file doesn't exist");
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadProperties();
	}

	private void loadProperties() throws FileNotFoundException {
		Set<Entry<Object, Object>> entries = addressess.get("RESOURCE_URL").entrySet();
		for (Entry entry : entries) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

		for (Entry<Object, Object> entry : entries) {
			String adrs = (String) entry.getValue();
			System.out.println(adrs);
			String key = (String) entry.getKey();
			String lowerCase = key.toLowerCase();
			if (!lowerCase.contains("url")) {
				Configs property = new Configs();
				try {
					File test = new File(adrs);
					System.out.println(test.getAbsolutePath());
					FileReader reader = new FileReader(test);
					property.load(reader);

				} catch (FileNotFoundException e) {
					System.out.println(entry.getKey() + " file doesn't exist");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println(entry.getKey() + " load failed");
					e.printStackTrace();
				}

				if (lowerCase.contains("frame")) {
					System.out.println("frame added : " + key);
					frameConfigs.put(key, property);
				} else if (lowerCase.contains("login")) {
					System.out.println("login_panel added : " + key);
					loginpanelConfigs.put(key, property);
				} else if (lowerCase.contains("menu")) {
					System.out.println("menu_panel added : " + key);
					menupanelConfigs.put(key, property);
				} else if (lowerCase.contains("setting")) {
					System.out.println("setting_panel added : " + key);
					settingpanelConfigs.put(key, property);
				} else if (lowerCase.contains("page")) {
					System.out.println("page_panel added : " + key);
					pagePanelConfigs.put(key, property);
				} else if (lowerCase.contains("timeline")) {
					System.out.println("time line_panel added : " + key);
					timeLinePanelConfigs.put(key, property);
				} else if (lowerCase.contains("explorer")) {
					System.out.println("explorer_panel added : " + key);
					explorerPanelConfigs.put(key, property);
				} else if (lowerCase.contains("messaging")) {
					System.out.println("messaging_panel added : " + key);
					messagingPanelConfigs.put(key, property);
				} else
					propeties.put(key, property);

			}
			System.out.println("loading finished! ");
		}
	}

	public static ConfigLoader getInstance(String address) throws FileNotFoundException {
		if (loader == null) {
			if (address.equals("default")) {
				address = defaultAddress;
			}
			loader = new ConfigLoader(address);
		}
		return loader;
	}

	public String getAddress(String type, String resource_url) {
		return addressess.get(type).getProperty(resource_url);
	}

	public String getAddress(String resource_url) {
		return getAddress(addressName, resource_url);
	}

	protected Configs getFrameProperties(String name) {
		return frameConfigs.get(name);
	}

	protected Configs getPanelProperties(String name) {
		return loginpanelConfigs.get(name);
	}

	protected Configs getMenuPanelProperties(String name) {
		return menupanelConfigs.get(name);
	}

//
	protected Configs getSettingPanelProperties(String name) {
		return settingpanelConfigs.get(name);
	}

	protected Configs getPagePanelProperties(String name) {
		return pagePanelConfigs.get(name);
	}

	protected Configs getTimeLinePanelProperties(String name) {
		return timeLinePanelConfigs.get(name);
	}

	protected Configs getExplorerPanelProperties(String name) {
		return explorerPanelConfigs.get(name);
	}

	protected Configs getMessagingPanelProperties(String name) {
		return messagingPanelConfigs.get(name);
	}
}
