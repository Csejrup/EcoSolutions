package ecosolutions.service;
import ecosolutions.persistence.DAO.AccountDao;
import ecosolutions.Domain.Account;
import java.util.*;


/**
 * This Class represents the Service for AccountDao,
 * Further on connected to the domain objects -> Controllers
 *
 */
public class AccountService {
	/**
	 * This class fetches the Username from tblaccount through accountdao
	 * @param username
	 * @return username
	 */
	public static Optional<Account> getAccount(String username){
		var accountDao = new AccountDao();
		return accountDao.getByUsername(username);
	}
}