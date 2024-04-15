package models;

import java.util.ArrayList;

public class Chat {
	private String contact;
	private ArrayList<Message> messages;

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Chat(String contact) {
		this.contact = contact;
		messages = new ArrayList<Message>();
	}
	@Override
	public String toString() {
		return "direct: " + contact;
	}
}
