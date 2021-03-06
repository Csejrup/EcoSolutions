package ecosolutions.Domain;
/**
 * This Class Represents the Model of an Customer
 * And contains all the attributes of an Customer
 */
public class Customer {
    //VARIABLES//
    private String first_name;
    private String phone_No;
    private String last_name;
    private int customer_id;

    //DEFAULT CONSTRUCTOR//
    public Customer() {

    }

    public Customer(String first_name, String phone_No, String last_name, int customer_id) {
         this.first_name = first_name;
         this.phone_No = phone_No;
         this.last_name = last_name;
         this.customer_id = customer_id;
    }
    public Customer(String first_name, String last_name, String phone_No) {

        this.first_name = first_name;
        this.phone_No = phone_No;
        this.last_name = last_name;
    }
    //SETTERS//
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public void setPhone_No(String phone_No) {
        this.phone_No = phone_No;
    }


    //GETTERS//
    public String getPhone_No() {
        return phone_No;
    }
    public String getLast_name() {
        return last_name;
    }
    public int getCustomer_id() { return customer_id;}
    public String getFirst_name() { return first_name;}



    /**
     * Method to convert DB data to Strings
     *
     */
    @Override
    public String toString() {
        return "Customer{" +
                "first_name='" + first_name + '\'' +
                ", phone_No='" + phone_No + '\'' +
                ", last_name='" + last_name + '\'' +
                ", customer_id=" + customer_id +
                '}';
    }
}
