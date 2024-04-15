package models;


public class Menu {
	private Expelorer expelorer;
	private Messaging messaging;
	private Page page;
	private Setting setting;
	private TimeLine timeline;

	public Menu() {
		this.expelorer = new Expelorer();
		this.messaging = new Messaging();
		this.page = new Page();
		this.setting = new Setting();
		this.timeline = new TimeLine();
	}

	public Expelorer getExpelorer() {
		return expelorer;
	}

	public void setExpelorer(Expelorer expelorer) {
		this.expelorer = expelorer;
	}

	public Messaging getMessaging() {
		return messaging;
	}

	public void setMessaging(Messaging messaging) {
		this.messaging = messaging;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public TimeLine getTimeline() {
		return timeline;
	}

	public void setTimeline(TimeLine timeline) {
		this.timeline = timeline;
	}

}
