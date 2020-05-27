package ecosolutions.Domain.Account;

import ecosolutions.persistence.DB;

public class Manager extends Account {


	private String managerName;

	public static boolean login(String employeeID, String password) {
		DB.selectSQL("SELECT fldPassword from tblAccount WHERE fldEmployeeID = ('" + employeeID + "')");
		String PW = DB.getQueryData();
		DB.selectSQL("SELECT fldEmployeeID from tblAccount WHERE fldEmployeeID = ('" + employeeID + "')");
		String UN = DB.getQueryData();
		return employeeID.equals(UN) && password.equals(PW);
	}

	public void viewWorkForce() {
		// TODO - implement ecosolutions.Domain.Account.Manager.viewWorkForce
		throw new UnsupportedOperationException();
	}

	public void viewStatistics() {
		// TODO - implement ecosolutions.Domain.Account.Manager.viewStatistics
		throw new UnsupportedOperationException();
	}

	public void viewActiveOrders() {

		// TODO - implement ecosolutions.Domain.Account.Manager.viewActiveOrders
		throw new UnsupportedOperationException();
	}

}