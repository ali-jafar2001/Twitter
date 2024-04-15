package util;

import java.io.FileNotFoundException;

public class ExplorerConfig extends SwingConfig {
	private String bcImage, btImage, obcImage, likeImage, clockImage, retweetImage;

	public String getLikeImage() {
		return likeImage;
	}

	public void setLikeImage(String likeImage) {
		this.likeImage = likeImage;
	}

	public String getClockImage() {
		return clockImage;
	}

	public void setClockImage(String clockImage) {
		this.clockImage = clockImage;
	}

	public String getRetweetImage() {
		return retweetImage;
	}

	public void setRetweetImage(String retweetImage) {
		this.retweetImage = retweetImage;
	}

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

	public ExplorerConfig(String name) {
		super(name);
		init();
	}

	private void init() {
		bcImage = properties.getProperty("bcImage");
		btImage = properties.getProperty("btImage");
		obcImage = properties.getProperty("obcImage");
		btWidth = properties.readInteger("btWidth");
		btHeight = properties.readInteger("btHeight");
		likeImage = properties.getProperty("likeImage");
		retweetImage = properties.getProperty("retweetImage");
		clockImage = properties.getProperty("clockImage");
	}

	@Override
	protected void setProperties() {
		try {
			this.properties = ConfigLoader.getInstance().getExplorerPanelProperties(name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
