package ecosolutions.presentation.models;

public class Customer {
    private String customerName;
    private String phone_No;

    public Customer(String customerName, String phone_No) {
        this.customerName = customerName;
        this.phone_No = phone_No;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone_No() {
        return phone_No;
    }

    public void setPhone_No(String phone_No) {
        this.phone_No = phone_No;
    }
}
