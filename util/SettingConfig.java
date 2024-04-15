package util;

import java.io.FileNotFoundException;

public class SettingConfig extends SwingConfig {
	private String bcImage, btImage, obcImage;
	private int btWidth, btHeight;

	public int getBtWidth() {
		return btWidth;
	}

	public void setBtWidth(int btWidth) {
		this.btWidth = btWidth;
	}

	public int getBtHeight() {
		return btHeight;
	}

	public void setBtHeight(int btHeight) {
		this.btHeight = btHeight;
	}

	public String getBcImage() {
		return bcImage;
	}

	public void setBcImage(String bcImage) {
		this.bcImage = bcImage;
	}

	public String getBtImage() {
		return btImage;
	}

	public void setBtImage(String btImage) {
		this.btImage = btImage;
	}

	public String getObcImage() {
		return obcImage;
	}

	public void setObcImage(String obcImage) {
		this.obcImage = obcImage;
	}

	public SettingConfig(String name) {
		super(name);
		init();
	}

	private void init() {
		bcImage = properties.getProperty("bcImage");
		btImage = properties.getProperty("btImage");
		obcImage = properties.getProperty("obcImage");
		btWidth = properties.readInteger("btWidth");
		btHeight = properties.readInteger("btHeight");
	}

	@Override
	protected void setProperties() {
		try {
			this.properties = ConfigLoader.getInstance().getSettingPanelProperties(name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
