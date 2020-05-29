package ecosolutions.persistence.DAO;

public class DaoFactory {


    public static CentralDao getCentralDao(){
        return new CentralDao();
    }

    public static CustomerDao getCustomerDao(){
        return new CustomerDao();

    }
    public static EmployeeDao getEmployeeDao(){
        return new EmployeeDao();

    }

    public static OrderDao getOrderDao(){
        return new OrderDao();
    }
}
