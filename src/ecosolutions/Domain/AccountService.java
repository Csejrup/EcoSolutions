package ecosolutions.Domain;
import ecosolutions.persistence.DAO.AccountDao;
import ecosolutions.presentation.models.Account;
import java.util.*;


/**
 * This Class represents the Service for AccountDao,
 * Further on connected to the models -> Controllers
 *
 */
public class AccountService {
	/**
	 * This class fetches the Username from tblaccount through accountdao
	 * @param username
	 * @return
	 */
	public static Optional<Account> getAccount(String username){
		var accountDao = new AccountDao();
		return accountDao.getByUsername(username);
	}
}