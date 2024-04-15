package logic;

import java.io.IOException;

import models.AccessLevel;
import models.LastSeenLevel;
import models.Status;

public class SettingLogic {
	public static void status(String st) {
		if (st.equals("inActive")) {
			changeStatus(Status.inactive);
			try {
				Logger.info("account is inactive now");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (st.equals("active")) {
			changeStatus(Status.active);
			try {
				Logger.info("account has been activated");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Saver.save();
	}

	public static void access(String st) {
		if (st.equals("public")) {
			chaneAccessLevel(AccessLevel.pub);
			try {
				Logger.warn("access level has been changed to public");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (st.equals("private")) {
			chaneAccessLevel(AccessLevel.pri);
			try {
				Logger.warn("access level has been changed to private");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Saver.save();
	}

	public static void lastSeen(String st) {
		if (st.equals("everyBody")) {
			changeLastseen(LastSeenLevel.everybody);
			try {
				Logger.warn("last seen privacy has been changed to every body");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (st.equals("noBody")) {
			changeLastseen(LastSeenLevel.nobody);
			try {
				Logger.warn("last seen privacy has been changed to  nobody");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (st.equals("contacts")) {
			changeLastseen(LastSeenLevel.contacts);
			try {
				Logger.warn("last seen privacy has been changed to contacts");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Saver.save();
	}

	private static void changeStatus(Status level) {
		SocialMedia.getCurrent_user().setStatus(level);
	}

	private static void changeLastseen(LastSeenLevel level) {
		SocialMedia.getCurrent_user().getLastseen().setLevel(level);
	}

	private static void chaneAccessLevel(AccessLevel level) {
		SocialMedia.getCurrent_user().setAccesslevel(level);
	}

	public static void deleteAccount(boolean prem) {
		if (prem) {
			try {
				Logger.warn("deleted account");
				Saver.deleteUser(SocialMedia.getCurrent_user());
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
