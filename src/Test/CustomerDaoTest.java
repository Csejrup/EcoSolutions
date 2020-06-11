package Test;
import junit.framework.*;

import ecosolutions.persistence.DAO.CustomerDao;
import org.junit.Test;
import static org.junit.Assert.*;
public class CustomerDaoTest {
    @Test
    public void getCustomerName(){
        String customerName = "Hans";
        String realName = CustomerDao.getCustomerName(11);
        assertEquals(customerName,realName);
    }

    @Test
    public void getCustomerPhone() {
        String customerPhone = "45674323";
        String properCustomerPhone = CustomerDao.getCustomerPhone(11);
        assertEquals(customerPhone,properCustomerPhone);
    }
    @Test
    public void exist(){
        int customerID = 11;
        assertTrue(CustomerDao.exist(customerID));
    }
}