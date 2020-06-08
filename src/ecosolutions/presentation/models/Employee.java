package ecosolutions.presentation.models;

/**
 * This Class Represents the Model of an Employee
 * And contains all the attributes of an Employee
 */
public class Employee {

    //VARIABLES//
    private String role;
    private String status;
    private String firstname;
    private String lastname;
    private int employeeid;
    private String phone_no;
    //DEFAULT CONSTRUCTOR//
    public Employee(){
    }
    public Employee(String firstname, String lastname, int employeeid, String phone_no) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.employeeid = employeeid;
        this.phone_no = phone_no;
    }
    //SETTERS//
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setEmployeeid(int id){
        this.employeeid = id;
    }
    //GETTERS//
    public String getPhone_no() {
        return phone_no;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public String getLastname() {
        return lastname;
    }

    public int getEmployeeid(){
        return employeeid;
    }

    /**
     * Method to convert DB data to Strings
     * @return
     */
    @Override
    public String toString(){
        return "Employee{" +
                "employeeid='" + employeeid + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", status='" + status + '\'' +
                ", phone_no='" + phone_no + '\'' +
                "role='" + role + '\'' + '}';
    }
}
