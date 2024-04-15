package models;

public class LastSeen {
	private LastSeenLevel level;
	private DateandTime date;
	public LastSeenLevel getLevel() {
		return level;
	}
	public void setLevel(LastSeenLevel level) {
		this.level = level;
	}
	public DateandTime getDate() {
		return date;
	}
	public void setDate(DateandTime date) {
		this.date = date;
	}
	
}
