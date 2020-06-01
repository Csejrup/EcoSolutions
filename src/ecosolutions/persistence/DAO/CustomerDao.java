package ecosolutions.persistence.DAO;

import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.presentation.models.Customer;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDao implements Dao<Customer> {
    @Override
    public Optional<Customer> getbyID(int id) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("SELECT fldCustomerID FROM tblCustomer WHERE fldCustomerID =" + id);
            //stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt("fldCustomerID"));
                return Optional.of(customer);
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> orders = new ArrayList<>();
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.createStatement();
            //SQL STATEMENT FOR SELECTING EVERY ORDER RELATED DATA IN MULTIPLE TABLES, CONNECTED THROUGH INNER JOIN AND TBLORDER
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblCustomer");
            while(rs.next()){
                Customer customer = exportCustomer(rs);
                orders.add(customer);
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void save(Customer customer) {
        var conn = DatabaseHandler.getInstance().getConnection();

        try {
            var stmt = conn.prepareStatement("INSERT INTO tblCustomer (fldCustomerID) VALUES (?)");

            stmt.setInt(1, customer.getCustomer_id());

            stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public void delete(Customer customer) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("DELETE FROM tblCustomerID WHERE fldCustomerID=?");
            stmt.setInt(1, customer.getCustomer_id());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private Customer exportCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomer_id(rs.getInt("fldCustomerID"));
        customer.setFirst_name(rs.getString("fldName"));
        customer.setLast_name(rs.getString("fldSurname"));
        customer.setPhone_No(rs.getString("fldPhone"));

        return customer;
    }
}
