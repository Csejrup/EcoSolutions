package ecosolutions.Domain;

import ecosolutions.persistence.DB;

public class DriverService extends AccountService {


	public static boolean login(String employeeID, String password) {
		DB.selectSQL("SELECT fldPassword from tblAccount WHERE fldEmployeeID = ('" + employeeID + "')");
		String PW = DB.getQueryData();
		DB.selectSQL("SELECT fldEmployeeID from tblAccount WHERE fldEmployeeID = ('" + employeeID + "')");
		String UN = DB.getQueryData();
		return employeeID.equals(UN) && password.equals(PW);
	}

}