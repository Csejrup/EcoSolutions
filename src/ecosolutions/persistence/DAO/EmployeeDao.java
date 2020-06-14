package ecosolutions.persistence.DAO;

import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.Domain.Employee;

import java.sql.*;
import java.util.*;

/**
 * DAO Class Responsible for connecting with the Database and fetch an Employee and its information
 * CRUD - Create, retrieve, update, delete
 */
public class EmployeeDao implements Dao<Employee> {
    /**
     * METHOD FOR GETTING EMPLOYEE DETAILS BASED ON EMPLOYEE ID
     */
    @Override
     public Optional<Employee> getbyID(int id) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("SELECT fldEmployeeID,fldName,fldSurname,fldPhone_no FROM tblEmployee WHERE fldEmployeeID=" + id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                var employ_id = rs.getInt("fldEmployeeID");
                var firstname = rs.getString("fldName");
                var surname = rs.getString("fldSurname");
                var phone_no = rs.getString("fldPhone_no");
                System.out.println(employ_id);
                Employee employee = new Employee(firstname, surname, employ_id,phone_no);
                return Optional.of(exportEmployee(rs));
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("EXEC fetchemployeeData");
            while(rs.next()){
                Employee employee = exportEmployee(rs);
                employees.add(employee);
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return employees;
    }
    @Override
    public void save(Employee employee) {
        //EMPTY
    }

    /**
     * Method that updates employee Details in Database
     * @param employee
     */
    @Override
    public void update(Employee employee) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try {
            var stmt = conn.prepareStatement("UPDATE tblCustomerID SET fldName=?,fldSurname=?,fldPhone=? WHERE fldCustomerID=?");
            stmt.setString(1, employee.getFirstname());
            stmt.setString(2, employee.getLastname());
            stmt.setString(3, employee.getPhone_no());
            stmt.setString(4, employee.getPhone_no());
            stmt.setString(3, employee.getPhone_no());
            stmt.setInt(4, employee.getEmployeeid());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for deleting Employee from DataBase
     * @param employee
     */
    @Override
    public void delete(Employee employee) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("DELETE FROM tblEmployee WHERE fldEmployeeID =?");
            stmt.setInt(1, employee.getEmployeeid());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private Employee exportEmployee(ResultSet rs) throws SQLException{
        Employee employee = new Employee();
        employee.setFirstname(rs.getString("fldName"));
        employee.setLastname(rs.getString("fldSurname"));
        employee.setStatus(rs.getString("fldStatus"));
        employee.setRole(rs.getString("fldRole"));
        employee.setEmployeeid(rs.getInt("fldEmployeeID"));
        employee.setPhone_no(rs.getString("fldPhone_no"));
        return employee;
    }
}
