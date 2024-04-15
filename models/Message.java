package models;

public class Message {
	protected String message;
	protected String owner;
	protected DateandTime dateandtime;
	private boolean isDisplayed;
	private String imageAddress = "default";

	public String getImageAddress() {
		return imageAddress;
	}

	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}

	public boolean isDisplayed() {
		return isDisplayed;
	}

	public void setDisplayed(boolean isDisplayed) {
		this.isDisplayed = isDisplayed;
	}

	public Message(String message) {
		this.message = message;
		this.dateandtime = new DateandTime();
	}

	public Message(String message, String owner) {
		this(message);
		this.owner = owner;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public DateandTime getDateandtime() {
		return dateandtime;
	}

	public void setDateandtime(DateandTime dateandtime) {
		this.dateandtime = dateandtime;
	}
}
