package ecosolutions.Domain.Account;

import ecosolutions.persistence.DB;

public abstract class Account {

	private static int accountID;
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

			DB.selectSQL("SELECT fldAccountID FROM tblAccount WHERE fldUserName = '"+userName+"';");
			accountID = Integer.parseInt(DB.getQueryData());
			return ShopAssistant.login(userName,password);
		}
		else return false;

	}
	public static int getDeliveryPoint(){
		DB.selectSQL("SELECT fldDeliveryPointID FROM tblDeliveryPoint WHERE fldAccountID = '"+accountID+"';");
		return Integer.parseInt(DB.getQueryData());
	}

}