package ecosolutions.Domain;

import ecosolutions.persistence.DAO.AccountDao;
import ecosolutions.persistence.DB;
import ecosolutions.presentation.models.Account;

import java.util.List;
import java.util.Optional;

public abstract class AccountService {

	private static int accountID;

	public static boolean verifyLogin(String userName, String password) {
		DB.selectSQL("SELECT fldUsername FROM tblAccount WHERE fldUserName = '"+userName+"';");
		String checkFirstLetter = DB.getQueryData();

		if(checkFirstLetter.startsWith("m")) {
			return ManagerService.login(userName, password);
		}
		if(checkFirstLetter.startsWith("d")){
			return DriverService.login(userName,password);
		}
		if(checkFirstLetter.startsWith("s")){

			DB.selectSQL("SELECT fldAccountID FROM tblAccount WHERE fldUserName = '"+userName+"';");
			accountID = Integer.parseInt(DB.getQueryData());
			return DeliveryPointService.login(userName,password);
		}
		else return false;

	}
	public static int getDeliveryPoint(){
		DB.selectSQL("SELECT fldDeliveryPointID FROM tblDeliveryPoint WHERE fldAccountID = '"+accountID+"';");
		return Integer.parseInt(DB.getQueryData());
	}
	private static boolean doesNotExist(Account acc){
		var accountDao = new AccountDao();
		for(Account a : accountDao.getAll()){
			if(a.equals(acc)){
				return false;
			}
		}
		return true;
	}
	public static Optional<Account> getAccount(int id){
		var accountDao = new AccountDao();
		return accountDao.getbyID(id);
	}
	public static void addAccount(Account acc){
		var accountDao = new AccountDao();
		if(doesNotExist(acc)){
			accountDao.save(acc);
		}
		accountDao.save(acc);
	}
	public static void updateAccount(Account acc){
		var accountDao = new AccountDao();
		accountDao.update(acc);
	}
	public static void deleteAccount(Account acc){
		var accountDao = new AccountDao();
		accountDao.delete(acc);
	}
	public static List<Account> getAccounts(){
		var accountDao = new AccountDao();
		return accountDao.getAll();
	}
}