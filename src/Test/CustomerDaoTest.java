package Test;

import ecosolutions.persistence.DAO.CustomerDao;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerDaoTest {

    @Test
    public void getCustomerName() {
        String customerName = "Tony";
        String properName = CustomerDao.getCustomerName(6);
        assertEquals(customerName,properName);
    }

    @Test
    public void getCustomerPhone() {
        String customerPhone = "+45687543687";
        String properCustomerPhone = CustomerDao.getCustomerPhone(6);
        assertEquals(customerPhone,properCustomerPhone);
    }
    @Test
    public void exist(){
        int customerID = 6;
        assertTrue(CustomerDao.exist(customerID));
    }
}