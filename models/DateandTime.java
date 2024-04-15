package models;

import java.time.LocalDateTime;

public class DateandTime extends Date {
	private int hour;
	private int second;

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	private int minute;

	public DateandTime(int day, int month, int year) {
		super(day, month, year);
	}

	public DateandTime(int day, int month, int year, int second, int minute, int hour) {
		super(day, month, year);
		this.hour = hour;
		this.second = second;
		this.minute = minute;
	}

	public DateandTime() {
		LocalDateTime datetime = LocalDateTime.now();
		day = datetime.getDayOfMonth();
		month = datetime.getMonthValue();
		year = datetime.getYear();
		second = datetime.getSecond();
		minute = datetime.getMinute();
		hour = datetime.getHour();
	}

	public String getDate() {
		return format(day) + "/" + format(month) + "/" + format(year);
	}

	public String getTime() {
		return format(hour) + ":" + format(minute) + ":" + format(second);
	}

	public String getDateandTime() {
		return getDate() + " - " + getTime();
	}

}
