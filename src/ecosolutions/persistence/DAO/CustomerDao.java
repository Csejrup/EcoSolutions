package ecosolutions.persistence.DAO;
import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.Domain.Customer;
import java.sql.*;
import java.util.*;


/**
 *  DAO Class Responsible for connecting with the Database and fetch a Customer and its information
 *  CRUD - Create, retrieve, update, delete
 */
public class CustomerDao implements Dao<Customer> {
    @Override
    public Optional<Customer> getbyID(int id) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try {
            var stmt = conn.prepareStatement("SELECT fldCustomerID FROM tblCustomer WHERE fldCustomerID =" + id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt("fldCustomerID"));
                return Optional.of(customer);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        var conn = DatabaseHandler.getInstance().getConnection();
        try {
            var stmt = conn.createStatement();
            //SQL STATEMENT FOR SELECTING EVERY ORDER RELATED DATA IN MULTIPLE TABLES, CONNECTED THROUGH INNER JOIN AND TBLORDER
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblCustomer");
            while (rs.next()) {
                Customer customer = exportCustomer(rs);
                customers.add(customer);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    /**
     * Method for Inserting the customer details inside of the database
     * @param customer
     */
    @Override
    public void save(Customer customer) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try {
            var stmt = conn.prepareStatement("INSERT INTO tblCustomer (fldName,fldSurname,fldPhone) VALUES ('" + customer.getFirst_name() + "','" + customer.getLast_name() + "','" + customer.getPhone_No() + "');");
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * METHOD FOR UPDATING CUSTOMER DETAILS BASED ON CUSTOMER ID.
     * @param customer
     */
    @Override
    public void update(Customer customer) {
        var conn = DatabaseHandler.getInstance().getConnection();

        try {
            var stmt = conn.prepareStatement("UPDATE tblCustomer SET fldName=?,fldSurname=?,fldPhone=? WHERE fldCustomerID=?");
            stmt.setString(1, customer.getFirst_name());
            stmt.setString(2, customer.getLast_name());
            stmt.setString(3, customer.getPhone_No());
            stmt.setInt(4, customer.getCustomer_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * METHOD FOR DELETING CUSTOMER FROM DATABASE -- FOR FUTURE IMPLEMENTATION.
     * @param customer
     */
    @Override
    public void delete(Customer customer) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try {
            var stmt = conn.prepareStatement("DELETE FROM tblCustomerID WHERE fldCustomerID=?");
            stmt.setInt(1, customer.getCustomer_id());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that returns true if the customer exist inside of the DataBase
     * @param customerID
     * @return
     */
    public static boolean exist(int customerID){
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            int cusID = 0;
            PreparedStatement stmt = conn.prepareStatement("SELECT fldCustomerID FROM tblCustomer WHERE fldCustomerID = '"+customerID+"';");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                cusID = Integer.parseInt(rs.getString(1));
            }
            if(cusID == customerID){
                return true;
            }
            stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();}
        return false;
    }

    /**
     * Method for getting the name of a Customer by CustomerID
     * @param customerID
     * @return
     */
    public static String getCustomerName(int customerID){
        var conn = DatabaseHandler.getInstance().getConnection();
        String customerName = "";
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT fldName FROM tblCustomer WHERE fldCustomerID = '"+customerID+"';");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                customerName =rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return customerName;
    }
    /**
     * Method for getting the phone number of a customer by CustomerID
     * @param customerID
     * @return
     */
    public static String getCustomerPhone(int customerID){
        var conn = DatabaseHandler.getInstance().getConnection();
        String customerPhone = "";
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT fldPhone FROM tblCustomer WHERE fldCustomerID = '"+customerID+"';");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                customerPhone =rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return customerPhone;
    }
    /**
     * Method for getting Customer Data by OrderID
     * @param orderID
     * @return
     */
    public List<Customer> getCustomerFromOrder(int orderID){
        List<Customer> customers = new ArrayList<>();

        var conn = DatabaseHandler.getInstance().getConnection();
        try {
            var stmt = conn.prepareStatement("EXEC done_SMS @getOrderID=" + orderID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer customer = exportCustomer(rs);
                customers.add(customer);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    private Customer exportCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomer_id(rs.getInt("fldCustomerID"));
        customer.setFirst_name(rs.getString("fldName"));
        customer.setLast_name(rs.getString("fldSurname"));
        customer.setPhone_No(rs.getString("fldPhone"));
        return customer;
    }
    /**
     * METHOD FOR GETTING LASTES CUSTOMER ID.
     */
    public static int getLastCustomerID()  {
        var conn = DatabaseHandler.getInstance().getConnection();
        int lastCustomerID = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT MAX(fldCustomerID) FROM tblCustomer");
            ResultSet s = stmt.executeQuery();
            if(s.next()){
                lastCustomerID =  s.getInt(1);
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lastCustomerID;
    }
}
