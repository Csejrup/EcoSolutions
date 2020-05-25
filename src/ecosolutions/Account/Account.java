package ecosolutions.Account;

import ecosolutions.DataBase.DB;

public abstract class Account {

	private int AccountID;
	private String Password;
	private String registerDate;
	private String LoginStatus;

	public static boolean verifyLogin(String userName, String password) {
		// TODO - implement ecosolutions.Account.Account.verifyLogin
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

		//throw new UnsupportedOperationException();
	}
