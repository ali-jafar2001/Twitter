package logic;

import java.io.IOException;

import interfaces.Listener;
import panels.MainFrame;

public class Controller implements Listener {
	private static Controller controller;

	public static Controller getInstance() {
		if (controller == null)
			controller = new Controller();
		return controller;
	}

	@Override
	public void listen(String st) {
		switch (st) {
		case "exit button": {
			MainFrame.getInstance().exitProgram();
			break;
		}
		case "sign in button": {
			MainFrame.getInstance().addSignInPanel();
			break;
		}
		case "sign up button": {
			MainFrame.getInstance().addSignUpPanel();
			break;
		}
		case "Sign in": {
			Login.getInstance().signIn();
			break;
		}
		case "Sign up": {
			Login.getInstance().signUp();
			break;
		}
		case "sign out": {
			Login.getInstance().signOut();
			break;
		}
		case "setting": {
			MainFrame.getInstance().goSettingPanel();
			try {
				Logger.info("went to the setting");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		case "settingToMenu": {
			MainFrame.getInstance().settingToMenuPanel();
			break;
		}
		case "status": {
			SettingLogic.status(MainFrame.getInstance().status());
			MainFrame.getInstance().setStatus();
			break;
		}
		case "lastSeen": {
			SettingLogic.lastSeen(MainFrame.getInstance().lastSeen());
			MainFrame.getInstance().setLastSeen();
			break;
		}
		case "access": {
			SettingLogic.access(MainFrame.getInstance().access());
			MainFrame.getInstance().setAccess();
			break;
		}
		case "command line": {
			MainFrame.getInstance().createCommandLine();
			try {
				Logger.info("went to the command line");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		case "delete account": {
			SettingLogic.deleteAccount(MainFrame.getInstance().deleteAccountMessage());
			break;
		}
		case "page": {
			MainFrame.getInstance().goPagePanel();
			try {
				Logger.info("went to the page");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		case "time line": {
			MainFrame.getInstance().goTimeLinePanel();
			try {
				Logger.info("went to the time line");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		case "explorer": {
			MainFrame.getInstance().goExplorerPanel();
			try {
				Logger.info("went to the time explorer");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		case "messaging": {
			MainFrame.getInstance().goMessagingPanel();
			try {
				Logger.info("went to the time messaging");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		case "pageToMenu": {
			MainFrame.getInstance().pageToMenuPanel();
			break;
		}
		case "explorerToMenu": {
			MainFrame.getInstance().explorerToMenuPanel();
			break;
		}
		case "messagingToMenu": {
			MainFrame.getInstance().messagingToMenuPanel();
			break;
		}
		case "timeLineToMenu": {
			MainFrame.getInstance().timeLineToMenuPanel();
			break;
		}
		case "editFirstName": {
			PageLogic.editFname(MainFrame.getInstance().editFname());
			break;
		}
		case "editLastName": {
			PageLogic.editLname(MainFrame.getInstance().editLname());
			break;
		}
		case "editBio": {
			PageLogic.editBio(MainFrame.getInstance().editBio());
			break;
		}
		case "edit_Email": {
			PageLogic.editEmail(MainFrame.getInstance().editEmail());
			break;
		}
		case "editPhone": {
			PageLogic.editPhone(MainFrame.getInstance().editPhone());
			break;
		}
		case "ediUsername": {
			PageLogic.editUsername(MainFrame.getInstance().editUsername());
			break;
		}
		case "editPassword": {
			PageLogic.editPassword(MainFrame.getInstance().editPassword());
			break;
		}
		case "editBirth": {
			PageLogic.editBirth(MainFrame.getInstance().editBirth());
			break;
		}
		case "setProfile": {
			PageLogic.setProfile(MainFrame.getInstance().setProfile());
			break;
		}
		case "tweet": {
			PageLogic.addTweet(MainFrame.getInstance().addTweet());
			break;
		}
		case "tweetPlus": {
			PageLogic.addTweetPlus(MainFrame.getInstance().addTweetPluse());
			break;
		}
		case "showMyTweets": {
			PageLogic.showMyTweets();
			break;
		}
		case "prevMyTweets": {
			PageLogic.setPrevMyTweet();
			break;
		}
		case "nextMyTweets": {
			PageLogic.setNextMyTweet();
			break;
		}
		case "popTweets": {
			ExplorerLogic.showPopTweets();
			break;
		}
		case "prevPopTweets": {
			ExplorerLogic.setPrevPopTweet();
			break;
		}
		case "nextPopTweets": {
			ExplorerLogic.setNextPopTweet();
			break;
		}
		case "saveMessage": {
			MessagingLogic.addSaveMessage(MainFrame.getInstance().addSaveMesaage());
			break;
		}
		case "saveMessagePlus": {
			MessagingLogic.addSaveMessagePlus(MainFrame.getInstance().addSaveMessagePlus());
			break;
		}
		case "savedMessages": {
			MessagingLogic.showSavedMessages();
			break;
		}
		case "nextSaveMessage": {
			MessagingLogic.setNextSaveMessage();
			break;
		}
		case "prevSaveMessage": {
			MessagingLogic.setPrevSaveMessage();
			break;
		}
		case "searchUser": {
			ExplorerLogic.exploreUser(MainFrame.getInstance().getExploreUsername());
			break;
		}
		case "follow": {
			ExplorerLogic.follow();
			break;
		}
		case "direct": {
			ExplorerLogic.direct();
			break;
		}
		case "block": {
			ExplorerLogic.block();
			break;
		}
		case "timeLineTweets": {
			TimeLineLogic.showTweets();
			break;
		}
		case "timeLinePrevTweet": {
			TimeLineLogic.prevTimeLineTweet();
			break;
		}
		case "timeLineNextTweet": {
			TimeLineLogic.nextTimeLineTweet();
			break;
		}
		case "commentTimeLine": {
			TimeLineLogic.addComment(MainFrame.getInstance().addComment());
			break;
		}
		case "commentsTimeLine": {
			TimeLineLogic.showComments();
			break;
		}
		case "nextTimeLineComment": {
			TimeLineLogic.nextTimeLineComment();
			break;
		}
		case "prevTimeLineComment": {
			TimeLineLogic.prevTimeLineComment();
			break;
		}
		case "saveTweet": {
			TimeLineLogic.saveTweet();
			break;
		}
		case "saveComment": {
			TimeLineLogic.saveComment();
			break;
		}
		case "reTweet": {
			TimeLineLogic.reTweet();
			break;
		}
		case "reComment": {
			TimeLineLogic.reComment();
			break;
		}
		case "likeTweet": {
			TimeLineLogic.likeTweet();
			break;
		}
		case "likeComment": {
			TimeLineLogic.likeComment();
			break;
		}
		case "spamTweet": {
			TimeLineLogic.spamTweet();
			break;
		}
		case "spamComment": {
			TimeLineLogic.spamComment();
			break;
		}
		case "showNewsPanel": {
			PageLogic.showNewsPanel();
			break;
		}
		case "showFollowersPanel": {
			PageLogic.showFollowersPanel();
			break;
		}
		case "prevFollowersPanel": {
			PageLogic.prevFollowersPanel();
			break;
		}
		case "nextFollowersPanel": {
			PageLogic.nextFollowersPanel();
			break;
		}
		case "showFollowingsPanel": {
			PageLogic.showFollowingsPanel();
			break;
		}
		case "nextFollowingsPanel": {
			PageLogic.nextFollowingsPanel();
			break;
		}
		case "prevFollowingsPanel": {
			PageLogic.prevFollowingsPanel();
			break;
		}
		case "showBlackListPanel": {
			PageLogic.showBlackListPanel();
			break;
		}
		case "nextBlackListPanel": {
			PageLogic.nextBlackListPanel();
			break;
		}
		case "prevBlackListPanel": {
			PageLogic.prevBlackListPanel();
			break;
		}
		case "showRequestsPanel": {
			PageLogic.showRequestsPanel();
			break;
		}
		case "nextRequestsPanel": {
			PageLogic.nextRequestsPanel();
			break;
		}
		case "prevRequestsPanel": {
			PageLogic.prevRequestsPanel();
			break;
		}
		case "unFollow": {
			PageLogic.unFollow();
			break;
		}
		case "unBlock": {
			PageLogic.unBlock();
			break;
		}
		case "accept": {
			PageLogic.accept();
			break;
		}
		case "reject": {
			PageLogic.reject();
			break;
		}
		case "createGroup": {
			MessagingLogic.createGroup(MainFrame.getInstance().createGroup());
			break;
		}
		case "showChats": {
			MessagingLogic.showChatsList();
			break;
		}
		case "selectChat": {
			MessagingLogic.selectChat();
			break;
		}
		case "prevChatMessage": {
			MessagingLogic.prevMessageChat();
			break;
		}
		case "nextChatMessage": {
			MessagingLogic.nextMessageChat();
			break;
		}
		case "prevGroupChatMessage": {
			MessagingLogic.prevGroupChatMessage();
			break;
		}
		case "nextGroupChatMessage": {
			MessagingLogic.nextGroupChatMessage();
			break;
		}
		case "messagePlusChat": {
			MessagingLogic.chatMessagePlus(MainFrame.getInstance().chatMessagePlus());
			break;
		}
		case "messageChat": {
			MessagingLogic.chatMessage(MainFrame.getInstance().chatMessage());
			break;
		}
		case "addNewMember": {
			MessagingLogic.addMember(MainFrame.getInstance().addMember());
			break;
		}
		case "groupChatMessage": {
			MessagingLogic.groupChatMessage(MainFrame.getInstance().groupChatMessage());
			break;
		}
		case "groupChatMessagePlus": {
			MessagingLogic.groupChatMessagePlus(MainFrame.getInstance().groupChatMessagePlus());
			break;
		}
		case "chatSaveMessage": {
			MessagingLogic.chatSaveMessage();
			break;
		}
		case "groupChatSaveMessage": {
			MessagingLogic.groupChatSaveMessage();
			break;
		}
		case "groupChatMembers": {
			MessagingLogic.groupChatMembers();
			break;
		}
		case "chatEditMessage": {
			MessagingLogic.editChatMessage();
			break;
		}
		case "groupChatEditMessage": {
			MessagingLogic.editGroupChatMessage();
			break;
		}
		case "chatDeleteMessage": {
			MessagingLogic.deleteChatMessage();
			break;
		}
		case "groupChatDeleteMessage": {
			MessagingLogic.deleteGroupChatMessage();
			break;
		}
		case "chatForwardMessage": {
			MessagingLogic.chatForwardMessage();
			break;
		}
		case "groupChatForwardMessage": {
			MessagingLogic.groupChatForwardMessage();
			break;
		}

		}
		Saver.save();
	}

	public String getTweetText() {
		return MainFrame.getInstance().addTweet();
	}

	public String getSaveMessageText() {
		return MainFrame.getInstance().addSaveMesaage();
	}

	public String getChatMessage() {
		return MainFrame.getInstance().chatMessage();
	}
}
