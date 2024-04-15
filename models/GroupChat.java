package models;

import java.util.ArrayList;

public class GroupChat {
	private ArrayList<String> users;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	private ArrayList<Message> messages;

	private GroupChat() {
		messages = new ArrayList<Message>();
		users = new ArrayList<String>();
	}

	public GroupChat(String name) {
		messages = new ArrayList<Message>();
		users = new ArrayList<String>();
		this.name = name;

	}

	@Override
	public String toString() {
		return "group: " + name;
	}

}
