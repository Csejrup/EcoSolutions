package ecosolutions.presentation.models;

public class Order {

	private int orderID;
	private int customerID;
	private int qty;

	public Order(int orderID, int customerID, int qty, String status, String clothtype, String orderdate) {
		this.orderID = orderID;
		this.customerID = customerID;
		this.qty = qty;
		this.status = status;
		this.clothtype = clothtype;
		this.orderdate = orderdate;
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

}