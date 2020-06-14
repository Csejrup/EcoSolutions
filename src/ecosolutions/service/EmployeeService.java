package ecosolutions.service;

import ecosolutions.persistence.DAO.EmployeeDao;
import ecosolutions.Domain.Employee;

import java.util.List;

/**
 *  This Class is a service class for EmployeeDao and Connects to the View
 */
public class EmployeeService {
    public static List<Employee> getEmployees(){
        EmployeeDao employeedao = new EmployeeDao();
        return employeedao.getAll();
    }
}
