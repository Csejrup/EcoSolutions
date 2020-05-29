package ecosolutions.presentation.models;

public class Employee {

    private String role;
    private String status;
    private String firstname;
    private String lastname;
    private int employeeid;
    public Employee(){

    }

    public Employee(String role, String status, String firstname, String lastname, int employeeid) {
        this.role = role;
        this.status = status;
        this.firstname = firstname;
        this.lastname = lastname;
        this.employeeid = employeeid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public int getEmployeeid(){
        return employeeid;
    }
    public void setEmployeeid(int id){
        this.employeeid = id;
    }

    @Override
    public String toString(){
        return "Employee{" +
                "employeeid='" + employeeid + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", status='" + status + '\'' +
                "role='" + role + '\'' + '}';
    }
}
