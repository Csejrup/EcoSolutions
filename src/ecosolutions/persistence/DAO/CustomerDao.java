package ecosolutions.persistence.DAO;

import ecosolutions.presentation.models.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerDao implements Dao<Customer> {
    @Override
    public Optional<Customer> getbyID(int id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }
}
