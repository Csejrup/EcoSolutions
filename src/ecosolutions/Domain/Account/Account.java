package ecosolutions.Domain.Account;

import ecosolutions.persistence.DB;

public abstract class Account {

	private int AccountID;
	private String Password;
	private String registerDate;
	private String LoginStatus;

	public static boolean verifyLogin(String userName, String password) {
		DB.selectSQL("SELECT fldUsername FROM tblAccount WHERE fldUserName = '"+userName+"';");
		String checkFirstLetter = DB.getQueryData();
		if(checkFirstLetter.startsWith("m")) {
			return Manager.login(userName, password);
		}
		if(checkFirstLetter.startsWith("d")){
			return Driver.login(userName,password);
		}
		if(checkFirstLetter.startsWith("s")){
			return ShopAssistant.login(userName,password);
		}
		else return false;
	}
}