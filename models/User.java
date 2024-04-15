package models;

import logic.ConsoleColors;

public class User {
	public String firstname;
	public String lastname;
	public String username;
	private String password;
	private Menu menu;
	private AccessLevel accesslevel = AccessLevel.pub;
	private LastSeen lastseen;
	private Status status = Status.active;

	public AccessLevel getAccesslevel() {
		return accesslevel;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setAccesslevel(AccessLevel accesslevel) {
		this.accesslevel = accesslevel;
	}

	public LastSeen getLastseen() {
		return lastseen;
	}

	public void setLastseen(LastSeen lastseen) {
		this.lastseen = lastseen;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Date birthdate;
	public String Email;
	public String phonenumber;
	public String bio;

	public User(String username) {
		this.username = username;
		this.menu = new Menu();
		this.lastseen = new LastSeen();
		this.lastseen.setLevel(LastSeenLevel.everybody);
		this.lastseen.setDate(new DateandTime());
	}

	public User(String username, String password) {
		this(username);
		this.password = password;
	}

	public User(String username, String password, String Email, String firstname, String lastname) {
		this(username, password);
		this.Email = Email;
		this.firstname = firstname;
		this.lastname = lastname;
	}

//	public void ShowProfile() {
//		ConsoleColors.changeColor("green");
//		System.out.println("username: " + this.username);
//		System.out.println("name: " + this.firstname + " " + this.lastname);
//		if (this.accesslevel == AccessLevel.pub)
//			System.out.println("account is public");
//		else
//			System.out.println("account is private");
//		System.out.println("last seen : " + this.getLastseen().getLevel());
//		System.out.println("followers: " + this.getMenu().getPage().getFollowers().size());
//		System.out.println("followings: " + this.getMenu().getPage().getFollowings().size());
//	}

//	public void ShowTweets() {
//		ConsoleColors.changeColor("green");
//		System.out.println("username: " + this.username);
//		System.out.println("name: " + this.firstname + " " + this.lastname);
//		if (this.accesslevel == AccessLevel.pub)
//			System.out.println("account is public");
//		else
//			System.out.println("account is private");
//		System.out.println("followers: " + this.getMenu().getPage().getFollowers().size());
//		System.out.println("followings: " + this.getMenu().getPage().getFollowings().size());
//		for (Tweet tweet : this.getMenu().getPage().getTweets()) {
//			System.out.println(tweet.dateandtime.getTime() + " " + tweet.dateandtime.getDate() + "  " + tweet.message);
//		}
//	}
}
