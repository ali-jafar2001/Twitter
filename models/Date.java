package models;

import java.time.LocalDate;

public class Date {
	protected int day;
	protected int year;
	protected int month;

	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public Date() {
		LocalDate date = LocalDate.now();
		day = date.getDayOfMonth();
		month = date.getMonthValue();
		year = date.getYear();
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return format(day) + "/" + format(month) + "/" + format(year);
	}

	protected String format(int value) {
		if (value > 9)
			return Integer.toString(value);
		return "0" + Integer.toString(value);
	}
}
