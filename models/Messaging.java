package models;

import java.util.ArrayList;

public class Messaging {
	private ArrayList<Message> savesmessages;
	private ArrayList<Chat> chats;
	private ArrayList<GroupChat> groupChats;

	public ArrayList<GroupChat> getGroupChats() {
		return groupChats;
	}

	public void setGroupChats(ArrayList<GroupChat> groupChats) {
		this.groupChats = groupChats;
	}

	public ArrayList<Message> getSavesmessages() {
		return savesmessages;
	}

	public void setSavesmessages(ArrayList<Message> savesmessages) {
		this.savesmessages = savesmessages;
	}

	public ArrayList<Chat> getChats() {
		return chats;
	}

	public void setChats(ArrayList<Chat> chats) {
		this.chats = chats;
	}

	public Messaging() {
		savesmessages = new ArrayList<Message>();
		chats = new ArrayList<Chat>();
		groupChats = new ArrayList<GroupChat>();
	}

	public Chat findChat(String st) {
		for (Chat c : chats) {
			if (c.getContact().equals(st))
				return c;
		}
		return null;
	}

	public GroupChat findGroupChat(String st) {
		for (GroupChat gc : groupChats) {
			if (gc.getName().equals(st))
				return gc;
		}
		return null;
	}
}
