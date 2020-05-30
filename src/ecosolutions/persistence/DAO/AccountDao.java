package ecosolutions.persistence.DAO;

import ecosolutions.presentation.models.Account;

import java.util.List;
import java.util.Optional;
//TODO FOR LOGIN IMPLEMENTATION FUNCTIONALITY
public class AccountDao implements Dao<Account> {
    @Override
    public Optional getbyID(int id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void save(Account acc) {

    }

    @Override
    public void update(Account acc) {

    }

    @Override
    public void delete(Account acc) {

    }
}
