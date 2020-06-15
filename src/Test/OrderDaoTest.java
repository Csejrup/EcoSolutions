package Test;

import ecosolutions.persistence.DAO.OrderDao;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A Test class that tests the lastest orderID and compares it with an expected ID
 */
public class OrderDaoTest {

    @Test
    public void getLastOrderID() {
        int expectedID = 6;
        assertEquals(expectedID, OrderDao.getLastOrderID());
    }
}