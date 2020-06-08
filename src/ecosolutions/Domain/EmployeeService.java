package ecosolutions.Domain;

import ecosolutions.persistence.DAO.EmployeeDao;
import ecosolutions.presentation.models.Employee;

import java.util.List;
import java.util.Optional;

/**
 *  This Class is a service class for EmployeeDao and Connects to the View
 */
public class EmployeeService {
    public static List<Employee> getEmployees(){
        EmployeeDao employeedao = new EmployeeDao();
        return employeedao.getAll();
    }
}
