package ecosolutions.persistence.DAO;

import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.presentation.models.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDao implements Dao<Employee> {
    @Override
     public Optional<Employee> getbyID(int id) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("SELECT fldName,fldSurname FROM tblEmployee WHERE fldEmployeeID=" + id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){


                return Optional.of(exportEmployee(rs));
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();

        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tblEmployee.fldName, tblEmployee.fldSurname, tblEmployee.fldEmployeeID, tblRole.fldRole, tblStatus.fldStatus " +
                    "FROM tblRole INNER JOIN " +
                    "(tblEmployee INNER JOIN tblStatus ON tblStatus.fldStatusID=tblEmployee.fldStatusID) " +
                    "ON tblRole.fldRoleID = tblEmployee.fldRoleID");
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

    }

    @Override
    public void update(Employee employee) {

    }
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
        return employee;
    }
}
