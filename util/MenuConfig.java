package util;

import java.io.FileNotFoundException;

public class MenuConfig extends SwingConfig {
	private String bcImage, btImage, birdImage, pmImage;
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

	public String getPmImage() {
		return pmImage;
	}

	public void setPmImage(String pmImage) {
		this.pmImage = pmImage;
	}

	public String getBirdImage() {
		return birdImage;
	}

	public void setBirdImage(String birdImage) {
		this.birdImage = birdImage;
	}

	public String getBtImage() {
		return btImage;
	}

	public void setBtImage(String btImage) {
		this.btImage = btImage;
	}

	public String getBcImage() {
		return bcImage;
	}

	public void setBcImage(String bcImage) {
		this.bcImage = bcImage;
	}

	public MenuConfig(String name) {
		super(name);
		init();
	}

	private void init() {
		bcImage = properties.getProperty("bcImage");
		btImage = properties.getProperty("btImage");
		birdImage = properties.getProperty("birdImage");
		pmImage = properties.getProperty("pmImage");
		btWidth = properties.readInteger("btWidth");
		btHeight = properties.readInteger("btHeight");
	}

	@Override
	protected void setProperties() {
		try {
			this.properties = ConfigLoader.getInstance().getMenuPanelProperties(name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
