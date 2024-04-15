package models;

public class Request {
	private String username;
	private DateandTime date;
	private Advantage adv;

	public Request(String username) {
		this.username = username;
		this.date = new DateandTime();
		this.adv = Advantage.unknown;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public DateandTime getDate() {
		return date;
	}

	public void setDate(DateandTime date) {
		this.date = date;
	}

	public Advantage getAdv() {
		return adv;
	}

	public void setAdv(Advantage adv) {
		this.adv = adv;
	}
}


 
