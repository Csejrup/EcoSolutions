package ecosolutions.Account;

import ecosolutions.Account.Account;
import ecosolutions.DataBase.DB;

public class Manager extends Account {

	private String managerName;

	public static boolean login(String userName, String password) {

		DB.selectSQL("SELECT fldPassword from tblLaundryWorker WHERE fldUsername = ('" + userName + "')");
		String PW = DB.getQueryData();
		DB.selectSQL("SELECT fldUsername from tblLaundryWorker WHERE fldUsername = ('" + userName + "')");
		String UN = DB.getQueryData();

		return userName.equals(UN) && password.equals(PW);

		// TODO - implement ecosolutions.Account.Manager.login
	}

	public void viewWorkForce() {
		// TODO - implement ecosolutions.Account.Manager.viewWorkForce
		throw new UnsupportedOperationException();
	}

	public void viewStatistics() {
		// TODO - implement ecosolutions.Account.Manager.viewStatistics
		throw new UnsupportedOperationException();
	}

	public void viewActiveOrders() {
		// TODO - implement ecosolutions.Account.Manager.viewActiveOrders
		throw new UnsupportedOperationException();
	}

}