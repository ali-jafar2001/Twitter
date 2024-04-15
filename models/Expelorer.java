package models;


import java.util.ArrayList;

public class Expelorer {
	private ArrayList<Tweet> populars;

	public ArrayList<Tweet> getPopulars() {
		return populars;
	}

	public void setPopulars(ArrayList<Tweet> populars) {
		this.populars = populars;
	}

	public Expelorer() {
		populars = new ArrayList<Tweet>();
	}

}
