package util;

import java.io.FileNotFoundException;

public class LoginPanelConfig extends SwingConfig {
	private String bcImage,btImage,obcImage;
	public String getObcImage() {
		return obcImage;
	}

	public void setObcImage(String obcImage) {
		this.obcImage = obcImage;
	}

	private int btWidth , btHeight;
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

	public LoginPanelConfig(String name) {
		super(name);
		init();
	}

	private void init() {
		bcImage = properties.getProperty("bcImage");
		btImage = properties.getProperty("btImage");
		btWidth = properties.readInteger("btWidth");
		btHeight = properties.readInteger("btHeight");
		obcImage = properties.getProperty("obcImage");
	}

	@Override
	protected void setProperties() {
		try {
			this.properties = ConfigLoader.getInstance().getPanelProperties(name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
