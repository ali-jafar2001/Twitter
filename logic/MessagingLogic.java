package logic;

import java.io.IOException;

import java.util.ArrayList;

import models.Chat;
import models.GroupChat;
import models.Message;
import models.User;
import panels.MainFrame;

public class MessagingLogic {
	private static int i = 0;
	private static int j = 0;
	private static int iChat = -1, iGroupChat = -1;

	public static void addSaveMessage(String st) {
		if (st != null && !st.equals("")) {
			Message message = new Message(st);
			SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().add(message);
			try {
				Logger.info("added new message to save messages: " + message.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void addSaveMessagePlus(String st) {
		if (!(st.equals("default") || (!st.contains(".png") && !st.contains(".jpg")))) {
			String text = Controller.getInstance().getSaveMessageText();
			if (text != null && !text.equals("")) {
				Message message = new Message(text);
				message.setImageAddress(st);
				SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().add(message);
				try {
					Logger.info("added new message to save messages: " + message.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void showSavedMessages() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().size() == 0) {
			MainFrame.getInstance().emptySavedMessage();
		} else {
			MainFrame.getInstance().showSavedMessages();
			MainFrame.getInstance()
					.setSaveMessage(SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().get(0));
		}
		try {
			Logger.info("watched saved messages");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setNextSaveMessage() {
		i++;
		if (i == SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().size())
			i = 0;
		MainFrame.getInstance()
				.setSaveMessage(SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().get(i));
		try {
			Logger.info("watched save message: "
					+ SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().get(i).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setPrevSaveMessage() {
		i--;
		if (i == -1)
			i = SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().size() - 1;
		MainFrame.getInstance()
				.setSaveMessage(SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().get(i));
		try {
			Logger.info("watched save message: "
					+ SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().get(i).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createGroup(String st) {
		if (st != null && !st.equals("")) {
			GroupChat gc = new GroupChat(st);
			SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().add(gc);
			MainFrame.getInstance().showMessageDialog("group has been created successfully ");
			try {
				Logger.info("created a group name: " + st);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void showChatsList() {
		ArrayList<String> list = new ArrayList<String>();
		for (Chat chat : SocialMedia.getCurrent_user().getMenu().getMessaging().getChats())
			list.add(chat.toString());
		for (GroupChat gc : SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats())
			list.add(gc.toString());
		if (list.size() == 0)
			MainFrame.getInstance().showMessageDialog("there is no chat");
		else {
			MainFrame.getInstance().setchatList(list);
			MainFrame.getInstance().showChatsList();
		}
		try {
			Logger.info("watched chats list");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void selectChat() {
		String st = MainFrame.getInstance().getChatName();
		if (st == null)
			MainFrame.getInstance().showMessageDialog("you must select a chat first");
		else {
			j = -1;
			iChat = -1;
			iGroupChat = -1;
			st = getExactName(st);
			boolean prem = isGroupChat(st);
			if (prem)
				setGrupChat(st);
			else
				setChat(st);
		}
	}

	private static void setChat(String st) {
		for (int i = 0; i < SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().size(); i++) {
			if (SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(i).getContact().equals(st))
				iChat = i;
		}
		MainFrame.getInstance().setMessageNull();
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().size() != 0) {
			j = SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().size() - 1;
			MainFrame.getInstance().setChatMessage(
					SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().get(j),
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat)
							.getMessages().get(j).getOwner()));
		}
		try {
			Logger.info("watched chat with " + st);
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainFrame.getInstance().showChatPanel();
	}

	public static void nextMessageChat() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().size() != 0) {
			j++;
			if (j == SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().size())
				j = 0;
			MainFrame.getInstance().setChatMessage(
					SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().get(j),
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat)
							.getMessages().get(j).getOwner()));
			try {
				Logger.info("watched chat with message: " + SocialMedia.getCurrent_user().getMenu().getMessaging()
						.getChats().get(iChat).getMessages().get(j).getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void prevMessageChat() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().size() != 0) {
			j--;
			if (j == -1)
				j = SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().size()
						- 1;
			MainFrame.getInstance().setChatMessage(
					SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().get(j),
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat)
							.getMessages().get(j).getOwner()));
			try {
				Logger.info("watched chat with message: " + SocialMedia.getCurrent_user().getMenu().getMessaging()
						.getChats().get(iChat).getMessages().get(j).getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void setGrupChat(String st) {
		for (int i = 0; i < SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().size(); i++) {
			if (SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(i).getName().equals(st))
				iGroupChat = i;
		}
		MainFrame.getInstance().setMessageNull();
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
				.size() != 0) {
			j = SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
					.size() - 1;
			MainFrame.getInstance().setGroupChatMessage(
					SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
							.get(j),
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
							.get(iGroupChat).getMessages().get(j).getOwner()));
		}
		MainFrame.getInstance().showGroupChatPanel();
		try {
			Logger.info("watched group: " + st);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void nextGroupChatMessage() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
				.size() != 0) {
			j++;
			if (j == SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
					.getMessages().size())
				j = 0;
			MainFrame.getInstance().setGroupChatMessage(
					SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
							.get(j),
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
							.get(iGroupChat).getMessages().get(j).getOwner()));

			try {
				Logger.info("watched group: " + SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
						.get(iGroupChat).getMessages().get(j).getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void prevGroupChatMessage() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
				.size() != 0) {
			j--;
			if (j == -1)
				j = SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
						.size() - 1;
			MainFrame.getInstance().setGroupChatMessage(
					SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
							.get(j),
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
							.get(iGroupChat).getMessages().get(j).getOwner()));

			try {
				Logger.info("watched group: " + SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
						.get(iGroupChat).getMessages().get(j).getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static boolean isGroupChat(String st) {
		for (GroupChat gc : SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats())
			if (gc.getName().equals(st))
				return true;
		return false;
	}

	private static boolean isChat(String st) {
		for (Chat c : SocialMedia.getCurrent_user().getMenu().getMessaging().getChats()) {
			if (c.getContact().equals(st))
				return true;
		}
		return false;
	}

	private static String getExactName(String st) {
		if (st.contains("direct: "))
			return st.substring(8);
		if (st.contains("group: "))
			return st.substring(7);
		return null;
	}

	public static void chatMessage(String st) {
		if (st != null && !st.equals("")) {
			Message m = new Message(st);
			m.setOwner(SocialMedia.getCurrent_user().username);
			SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().add(m);
			SocialMedia
					.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getContact())
					.getMenu().getMessaging().findChat(SocialMedia.getCurrent_user().username).getMessages().add(m);
			j = SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().size() - 1;
			MainFrame.getInstance().setChatMessage(
					SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().get(j),
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat)
							.getMessages().get(j).getOwner()));
			try {
				Logger.info("sent message: " + st + " to "
						+ SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getContact());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void groupChatMessage(String st) {
		if (st != null && !st.equals("")) {
			Message m = new Message(st);
			m.setOwner(SocialMedia.getCurrent_user().username);
			SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages().add(m);
			for (String str : SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
					.getUsers()) {
				SocialMedia.findUser(str).getMenu().getMessaging().findGroupChat(SocialMedia.getCurrent_user().getMenu()
						.getMessaging().getGroupChats().get(iGroupChat).getName()).getMessages().add(m);
			}
			j = SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
					.size() - 1;
			MainFrame.getInstance().setGroupChatMessage(
					SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
							.get(j),
					SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
							.get(iGroupChat).getMessages().get(j).getOwner()));
			try {
				Logger.info("sent message: " + st + " to group: " + SocialMedia.getCurrent_user().getMenu()
						.getMessaging().getGroupChats().get(iGroupChat).getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void groupChatMessagePlus(String st) {
		if (!(st.equals("default") || (!st.contains(".png") && !st.contains(".jpg")))) {
			String text = Controller.getInstance().getChatMessage();
			if (text != null && !text.equals("")) {
				Message m = new Message(text);
				m.setOwner(SocialMedia.getCurrent_user().username);
				m.setImageAddress(st);
				SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
						.add(m);
				for (String str : SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
						.getUsers()) {
					SocialMedia
							.findUser(str).getMenu().getMessaging().findGroupChat(SocialMedia.getCurrent_user()
									.getMenu().getMessaging().getGroupChats().get(iGroupChat).getName())
							.getMessages().add(m);
				}

				j = SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
						.size() - 1;
				MainFrame.getInstance().setGroupChatMessage(
						SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
								.getMessages().get(j),
						SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
								.get(iGroupChat).getMessages().get(j).getOwner()));
				try {
					Logger.info("sent message: " + text + " to group: " + SocialMedia.getCurrent_user().getMenu()
							.getMessaging().getGroupChats().get(iGroupChat).getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void chatMessagePlus(String st) {
		if (!(st.equals("default") || (!st.contains(".png") && !st.contains(".jpg")))) {
			String text = Controller.getInstance().getChatMessage();
			if (text != null && !text.equals("")) {
				Message m = new Message(text);
				m.setOwner(SocialMedia.getCurrent_user().username);
				m.setImageAddress(st);
				SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().add(m);
				SocialMedia
						.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat)
								.getContact())
						.getMenu().getMessaging().findChat(SocialMedia.getCurrent_user().username).getMessages().add(m);
				j = SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().size()
						- 1;
				MainFrame.getInstance().setChatMessage(
						SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages()
								.get(j),
						SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getChats()
								.get(iChat).getMessages().get(j).getOwner()));
				try {
					Logger.info("sent message: " + text + " to " + SocialMedia.getCurrent_user().getMenu()
							.getMessaging().getChats().get(iChat).getContact());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void addMember(String st) {
		if (st != null && !st.equals("")) {
			User user = SocialMedia.findUser(st);
			if (user == null)
				MainFrame.getInstance().showMessageDialog("there is no user with this user name");
			else {
				boolean pre = true;
				for (String str : SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
						.getUsers()) {
					if (str.equals(st)) {
						MainFrame.getInstance().showMessageDialog("user already exists in group");
						pre = false;
						break;
					}
				}
				if (pre) {
					GroupChat gc = new GroupChat(SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
							.get(iGroupChat).getName());
					ArrayList<Message> temp = new ArrayList<Message>();
					for (Message m : SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
							.get(iGroupChat).getMessages())
						temp.add(m);
					gc.setMessages(temp);
					ArrayList<String> users = new ArrayList<String>();
					for (String str : SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
							.get(iGroupChat).getUsers())
						users.add(str);
					gc.setUsers(users);
					gc.getUsers().add(SocialMedia.getCurrent_user().username);
					SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getUsers()
							.add(st);
					user.getMenu().getMessaging().getGroupChats().add(gc);
					MainFrame.getInstance().showMessageDialog(st + " has been added to group");
					try {
						Logger.info("added " + st + SocialMedia.getCurrent_user().getMenu().getMessaging()
								.getGroupChats().get(iGroupChat).getName());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	public static void chatSaveMessage() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().size() != 0) {
			SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().add(
					SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().get(j));
			MainFrame.getInstance().showMessageDialog("message saved in your save messges");
			try {
				Logger.info("saved message: " + SocialMedia.getCurrent_user().getMenu().getMessaging().getChats()
						.get(iChat).getMessages().get(j).getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			MainFrame.getInstance().showMessageDialog("there is no message");
	}

	public static void groupChatSaveMessage() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
				.size() != 0) {
			SocialMedia.getCurrent_user().getMenu().getMessaging().getSavesmessages().add(SocialMedia.getCurrent_user()
					.getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages().get(j));
			MainFrame.getInstance().showMessageDialog("message saved in your save messges");
			try {
				Logger.info("saved message: " + SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
						.get(iGroupChat).getMessages().get(j).getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			MainFrame.getInstance().showMessageDialog("there is no message");

	}

	public static void groupChatMembers() {
		String st = "1: " + SocialMedia.getCurrent_user().username + "\n";
		int i = 2;
		for (String str : SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
				.getUsers()) {
			st = st + String.valueOf(i) + ": " + str + "\n";
			i++;
		}
		MainFrame.getInstance().showMessageDialog(st);
	}

	public static void editChatMessage() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().size() != 0) {
			if (SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().get(j)
					.getOwner().equals(SocialMedia.getCurrent_user().username)) {
				String text = Controller.getInstance().getChatMessage();
				SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().get(j)
						.setMessage(text);
				SocialMedia
						.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat)
								.getContact())
						.getMenu().getMessaging().findChat(SocialMedia.getCurrent_user().username).getMessages().get(j)
						.setMessage(text);
				MainFrame.getInstance().setChatMessage(
						SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages()
								.get(j),
						SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getChats()
								.get(iChat).getMessages().get(j).getOwner()));
				MainFrame.getInstance().showMessageDialog("your message text has been changed");
				try {
					Logger.info("edited a message to " + text);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else
				MainFrame.getInstance().showMessageDialog("you can not edit your contact's message");
		} else {
			MainFrame.getInstance().showMessageDialog("there is no message");
		}
	}

	public static void editGroupChatMessage() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
				.size() != 0) {
			if (SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
					.get(j).getOwner().equals(SocialMedia.getCurrent_user().username)) {
				String text = Controller.getInstance().getChatMessage();
				SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
						.get(j).setMessage(text);
				for (String st : SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
						.getUsers()) {
					SocialMedia
							.findUser(st).getMenu().getMessaging().findGroupChat(SocialMedia.getCurrent_user().getMenu()
									.getMessaging().getGroupChats().get(iGroupChat).getName())
							.getMessages().get(j).setMessage(text);
				}
				MainFrame.getInstance().setGroupChatMessage(
						SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
								.getMessages().get(j),
						SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
								.get(iGroupChat).getMessages().get(j).getOwner()));
				MainFrame.getInstance().showMessageDialog("your message text has been changed");
				try {
					Logger.info("edited a message to " + text);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else
				MainFrame.getInstance().showMessageDialog("you can not edit your contact's message");
		} else
			MainFrame.getInstance().showMessageDialog("there is no message");
	}

	public static void deleteChatMessage() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().size() != 0) {
			if (SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().get(j)
					.getOwner().equals(SocialMedia.getCurrent_user().username)) {
				Message m = SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages()
						.get(j);
				SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().remove(m);
				SocialMedia
						.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat)
								.getContact())
						.getMenu().getMessaging().findChat(SocialMedia.getCurrent_user().username).getMessages()
						.remove(m);
				MainFrame.getInstance().setMessageNull();
				if (SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages()
						.size() != 0) {
					j = SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages()
							.size() - 1;
					MainFrame.getInstance().setChatMessage(
							SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages()
									.get(j),
							SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getChats()
									.get(iChat).getMessages().get(j).getOwner()));
				}
				MainFrame.getInstance().showMessageDialog("message has been deleted");
				try {
					Logger.info("deleted message: " + m.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else
				MainFrame.getInstance().showMessageDialog("you can not delete your contact's message");
		} else
			MainFrame.getInstance().showMessageDialog("there is no message");
	}

	public static void deleteGroupChatMessage() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
				.size() != 0) {
			if (SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
					.get(j).getOwner().equals(SocialMedia.getCurrent_user().username)) {
				Message m = SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
						.getMessages().get(j);
				SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
						.remove(m);
				for (String st : SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
						.getUsers()) {
					SocialMedia.findUser(st).getMenu().getMessaging().findGroupChat(SocialMedia.getCurrent_user()
							.getMenu().getMessaging().getGroupChats().get(iGroupChat).getName()).getMessages()
							.remove(m);
				}
				MainFrame.getInstance().setMessageNull();
				if (SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
						.size() != 0) {
					j = SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
							.getMessages().size() - 1;
					MainFrame.getInstance().setGroupChatMessage(
							SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
									.getMessages().get(j),
							SocialMedia.findUser(SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats()
									.get(iGroupChat).getMessages().get(j).getOwner()));
				}
				MainFrame.getInstance().showMessageDialog("message has been deleted");
				try {
					Logger.info("deleted message: " + m.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else
				MainFrame.getInstance().showMessageDialog("you can not delete your contact's message");
		} else
			MainFrame.getInstance().showMessageDialog("there is no message");
	}

	public static void chatForwardMessage() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages().size() != 0) {
			String text = MainFrame.getInstance().forwardUser();
			Message message = SocialMedia.getCurrent_user().getMenu().getMessaging().getChats().get(iChat).getMessages()
					.get(j);
			if (isChat(text)) {
				Chat chat = null;
				for (Chat c : SocialMedia.getCurrent_user().getMenu().getMessaging().getChats())
					if (c.getContact().equals(text))
						chat = c;
				forwardToChat(chat, message);
			} else if (isGroupChat(text)) {
				GroupChat gc = null;
				for (GroupChat g : SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats())
					if (g.getName().equals(text))
						gc = g;
				forwardToGroupChat(gc, message);
			} else {
				MainFrame.getInstance().showMessageDialog("wrong chat name");
			}

		} else
			MainFrame.getInstance().showMessageDialog("there is no message");
	}

	private static void forwardToGroupChat(GroupChat gc, Message message) {
		gc.getMessages().add(message);
		for (String st : gc.getUsers())
			SocialMedia.findUser(st).getMenu().getMessaging().findGroupChat(gc.getName()).getMessages().add(message);
		MainFrame.getInstance().showMessageDialog("message has been forwarded");
		try {
			Logger.info("forwarded message: " + message.getMessage() + " to chat: " + gc.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void forwardToChat(Chat chat, Message message) {
		chat.getMessages().add(message);
		SocialMedia.findUser(chat.getContact()).getMenu().getMessaging()
				.findChat(SocialMedia.getCurrent_user().username).getMessages().add(message);
		MainFrame.getInstance().showMessageDialog("message has been forwarded");
		try {
			Logger.info("forwarded message: " + message.getMessage() + " to chat: " + chat.getContact());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void groupChatForwardMessage() {
		if (SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat).getMessages()
				.size() != 0) {
			String text = MainFrame.getInstance().forwardUser();
			Message message = SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats().get(iGroupChat)
					.getMessages().get(j);
			if (isChat(text)) {
				Chat chat = null;
				for (Chat c : SocialMedia.getCurrent_user().getMenu().getMessaging().getChats())
					if (c.getContact().equals(text))
						chat = c;
				forwardToChat(chat, message);
			} else if (isGroupChat(text)) {
				GroupChat gc = null;
				for (GroupChat g : SocialMedia.getCurrent_user().getMenu().getMessaging().getGroupChats())
					if (g.getName().equals(text))
						gc = g;
				forwardToGroupChat(gc, message);
			} else {
				MainFrame.getInstance().showMessageDialog("wrong chat name");
			}
		} else
			MainFrame.getInstance().showMessageDialog("there is no message");
	}
}
