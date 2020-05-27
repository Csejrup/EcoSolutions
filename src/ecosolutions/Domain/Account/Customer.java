package ecosolutions.Domain.Account;

import ecosolutions.persistence.DB;

public class Customer extends Account {

	private String customerName;
	private String phone_No;

	public void register() {
		// TODO - implement ecosolutions.Domain.Account.Customer.register
		throw new UnsupportedOperationException();
	}

	public void login() {
		// TODO - implement ecosolutions.Domain.Account.Customer.login
		throw new UnsupportedOperationException();
	}

	public void updateProfile() {
		// TODO - implement ecosolutions.Domain.Account.Customer.updateProfile
		throw new UnsupportedOperationException();
	}
	public static int getCustomerID(){
		DB.selectSQL("SELECT MAX(fldCustomerID) FROM tblCustomer");
		return Integer.parseInt(DB.getQueryData());

	}

}