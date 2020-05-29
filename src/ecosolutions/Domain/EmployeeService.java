package ecosolutions.Domain;

import ecosolutions.persistence.DAO.EmployeeDao;
import ecosolutions.presentation.models.Employee;

import java.util.List;

public class EmployeeService {

    private static boolean doesNotExist(Employee employee){
        EmployeeDao employeedao = new EmployeeDao();
        for(Employee e : employeedao.getAll()){
            if(e.equals(employee)){
                return false;
            }
        }
        return true;
    }
    public static void getEmployee(int id){
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.getbyID(id);
    }
    public static void addEmployee(Employee employee){
        EmployeeDao employeedao = new EmployeeDao();
        if(doesNotExist(employee)){
            employeedao.save(employee);
        }
        employeedao.save(employee);
    }
    public static void updateEmployee(Employee employee){
        EmployeeDao employeedao = new EmployeeDao();
        employeedao.update(employee);
    }
    public static void deleteEmployee(int employee){
        EmployeeDao employeedao = new EmployeeDao();
        employeedao.delete(employee);
    }
    public static List<Employee> getEmployees(){
        EmployeeDao employeedao = new EmployeeDao();
        return employeedao.getAll();
    }
}
