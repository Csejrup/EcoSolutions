package ecosolutions.presentation.models;

public class Employee {

    private int roleid;
    private int statusid;
    private String firstname;

    public Employee(int roleid, int statusid, String firstname, String lastname) {
        this.roleid = roleid;
        this.statusid = statusid;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
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

    private String lastname;

}
