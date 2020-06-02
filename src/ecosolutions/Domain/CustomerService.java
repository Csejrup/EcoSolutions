package ecosolutions.Domain;

import ecosolutions.persistence.DAO.CustomerDao;
import ecosolutions.persistence.DB;
import ecosolutions.presentation.models.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CustomerService extends AccountService {

/*	public static int getCustomerID(){
		DB.selectSQL("SELECT MAX(fldCustomerID) FROM tblCustomer");
		return Integer.parseInt(DB.getQueryData());

	}*/
	private static boolean doesNotExist(Customer customer){
		var customerDao = new CustomerDao();
		for(Customer c : customerDao.getAll()){
			if(c.equals(customer)){
				return false;
			}
		}
		return true;
	}
	public static Optional<Customer> getCustomer(int id){
		var customerDao = new CustomerDao();
		return customerDao.getbyID(id);
	}
	public static void addCustomer(Customer customer){
		var customerDao = new CustomerDao();
		customerDao.save(customer);
	}
	public static void updateCustomer(Customer customer){
		var customerDao = new CustomerDao();
		customerDao.update(customer);
	}
	public static void deleteCustomer(Customer customer){
		var customerDao = new CustomerDao();
		customerDao.delete(customer);
	}
	public static List<Customer> getCustomers(){
		var customerDao = new CustomerDao();
		return customerDao.getAll();
	}
	public static int getCustomerID() {
		var customerDao = new CustomerDao();
		return customerDao.getCustomerID();
	}
}