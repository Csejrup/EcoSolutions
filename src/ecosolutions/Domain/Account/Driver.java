package ecosolutions.Domain.Account;

import ecosolutions.persistence.DB;

public class Driver extends Account {

	private String driverName;

	public static boolean login(String userName, String password) {
		DB.selectSQL("SELECT fldPassword from tblDriver WHERE fldUsername = ('" + userName + "')");
		String PW = DB.getQueryData();
		DB.selectSQL("SELECT fldUsername from tblDriver WHERE fldUsername = ('" + userName + "')");
		String UN = DB.getQueryData();
		return userName.equals(UN) && password.equals(PW);
	}

	public void updateStatus() {
		// TODO - implement ecosolutions.Domain.Account.Driver.updateStatus
		throw new UnsupportedOperationException();
	}

}