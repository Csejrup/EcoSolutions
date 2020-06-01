package ecosolutions.presentation.models;

/**
 * Class that represents the Model of an Order
 * And contains all the attributes of an Order
 */

//TODO CLEAN UP THIS CLASS
public class Order {
	//VARIABLES//
	private int orderID;
	private int customerID;
	private int qty;
	private String orderstatus;
	private String deliverypointname;

	private String status;
	private String clothtype;
	private String orderdate;

	//DEFAULT CONSTRUCTOR//
	public Order(){
	}
	//CONSTRUCTOR//
	public Order(int orderID, int customerID, int qty, String orderstatus, String clothtype, String orderdate, String deliverypointname) {
		this.orderID = orderID;
		this.customerID = customerID;
		this.qty = qty;
		this.status = orderstatus;
		this.clothtype = clothtype;
		this.orderdate = orderdate;
		this.deliverypointname = deliverypointname;
	}
	//SETTERS//
	public void setDeliverypointname(String deliverypointname) {
		this.deliverypointname = deliverypointname;
	}
	public void setOrderstatus(String orderstatus){
		this.orderstatus = orderstatus;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setClothtype(String clothtype) {
		this.clothtype = clothtype;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	//GETTERS//
	public String getDeliverypointname() {
		return deliverypointname;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public int getOrderID() {
		return orderID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public int getQty() {
		return qty;
	}

	public String getStatus() {
		return status;
	}

	public String getClothtype() {
		return clothtype;
	}

	public String getOrderdate() {
		return orderdate;
	}
	/**
	 * Method to convert DB data to Strings
	 * @return
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