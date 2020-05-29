package ecosolutions.presentation.models;

import javafx.util.StringConverter;

public class Order {

	private int orderID;
	private int customerID;
	private int qty;
	private String orderstatus;


	private String deliverypointname;
	public Order(){
	}
	public Order(int orderID, int customerID, int qty, String orderstatus, String clothtype, String orderdate, String deliverypointname) {
		this.orderID = orderID;
		this.customerID = customerID;
		this.qty = qty;
		this.status = orderstatus;
		this.clothtype = clothtype;
		this.orderdate = orderdate;
		this.deliverypointname = deliverypointname;
	}
	public String getDeliverypointname() {
		return deliverypointname;
	}

	public void setDeliverypointname(String deliverypointname) {
		this.deliverypointname = deliverypointname;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus){
		this.orderstatus = orderstatus;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClothtype() {
		return clothtype;
	}

	public void setClothtype(String clothtype) {
		this.clothtype = clothtype;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	private String status;
	private String clothtype;
	private String orderdate;


	public void createOrderSlip() {
		// TODO - implement ecosolutions.presentation.models.Order.createOrderSlip
		throw new UnsupportedOperationException();
	}
	/*
	@Override
	public String toString() {
		return "Order{" +
				"orderID=" + orderID +
				", customerID=" + customerID +
				", qty=" + qty +
				", orderstatus='" + orderstatus + '\'' +
				", deliverypointname='" + deliverypointname + '\'' +
				", clothtype='" + clothtype + '\'' +
				", orderdate='" + orderdate + '\'' +
				'}';
	}

	 */

	@Override
	public String toString() {
		return "Order{" +
				"orderID=" + orderID +
				", orderstatus='" + orderstatus + '\'' +
				", deliverypointname='" + deliverypointname + '\'' +
				'}';
	}
}