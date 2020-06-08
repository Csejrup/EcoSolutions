package ecosolutions.Domain;

import ecosolutions.persistence.DAO.CustomerDao;
import ecosolutions.presentation.models.Customer;


/**
 * This Class is a service class for CustomerDao and Connects to the View
 */
public class CustomerService  {

	public static void addCustomer(Customer customer){
		var customerDao = new CustomerDao();
		customerDao.save(customer);
	}
	public static boolean isExist(int customerID){
		return CustomerDao.exist(customerID);
	}
	public static String getCustomerName(int customerID){
		return CustomerDao.getCustomerName(customerID);
	}
	public static String getCustomerPhone(int customerID){
		return CustomerDao.getCustomerPhone(customerID);
	}
}