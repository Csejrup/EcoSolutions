import ecosolutions.persistence.DAO.CustomerDao;
import ecosolutions.presentation.models.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerDaoTest {

    @Test
    public void getCustomerName() {
        String customerName = "12312";
        String properName = CustomerDao.getCustomerName(11);
        assertEquals(customerName,properName);
    }

    @Test
    public void getCustomerPhone() {
        String customerPhone = "sada";
        String properCustomerPhone = CustomerDao.getCustomerPhone(11);
        assertEquals(customerPhone,properCustomerPhone);
    }
    @Test
    public void exist(){
        int customerID = 11;
        assertTrue(CustomerDao.exist(customerID));
    }
}