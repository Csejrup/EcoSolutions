package Test;

import ecosolutions.persistence.DAO.OrderDao;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderDaoTest {
    @Test
    public void save() {
    }

    @Test
    public void getLastOrderID() {
        int expectedID = 1;
        assertEquals(expectedID, OrderDao.getLastOrderID());
    }
}