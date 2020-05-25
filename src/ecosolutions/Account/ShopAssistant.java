package ecosolutions.Account;

import ecosolutions.Account.Account;
import ecosolutions.DataBase.DB;

public class ShopAssistant extends Account {

	private String assistantName;

	public static boolean login(String userName, String password) {

		DB.selectSQL("SELECT fldPassword from tblDriver WHERE fldUsername = ('" + userName + "')");
		String PW = DB.getQueryData();
		DB.selectSQL("SELECT fldUsername from tblDriver WHERE fldUsername = ('" + userName + "')");
		String UN = DB.getQueryData();
		return userName.equals(UN) && password.equals(PW);

		// TODO - implement ecosolutions.Account.ShopAssistant.login
		//throw new UnsupportedOperationException();
	}

	public void managePayment() {
		// TODO - implement ecosolutions.Account.ShopAssistant.managePayment
		throw new UnsupportedOperationException();
	}

	public void setStatus() {
		// TODO - implement ecosolutions.Account.ShopAssistant.setStatus
		throw new UnsupportedOperationException();
	}

}