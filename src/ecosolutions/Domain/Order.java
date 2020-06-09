package ecosolutions.Domain;

import ecosolutions.application.OrderTableView;
import ecosolutions.presentation.controller.deliverypointwindow.CreateOrderController;
import javafx.collections.ObservableList;

/**
 * Class that represents the Model of an Order
 * And contains all the attributes of an Order
 */

//TODO CLEAN UP THIS CLASS
public class Order {

	private String itemtype;
	//VARIABLES//
	private int orderID;
	private int customerID;
	private int qty;
	private int orderDescID;
	private int orderStatusID;
	private int deliveryPointID;
	private float price;

	private String orderstatus;
	private String deliverypointname;
	private String status;
	private String clothtype;
	private String orderdate;
	private String date;
	private ObservableList<OrderTableView> itemz;
	//DEFAULT CONSTRUCTOR//
	public Order(){
	}
	//CONSTRUCTOR//

	public Order(int customerID,int orderStatusID, String date, int deliveryPointID,ObservableList<OrderTableView> items,float price){
		this.customerID = customerID;
		this.orderStatusID = orderStatusID;
		this.date = date;
		this.itemz = items;
		this.price = price;
		this.deliveryPointID = deliveryPointID;
	}
	public Order(int orderID, String orderstatus, String deliverypointname) {
		this.orderID = orderID;
		this.orderstatus = orderstatus;
		this.deliverypointname = deliverypointname;
	}
	public Order(int orderID, String orderstatus) {
		this.orderID = orderID;
		this.orderstatus = orderstatus;
	}

	public Order(String clothtype, int qty) {
		this.clothtype = clothtype;
		this.qty = qty;
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
	public void setOrderDescID(int orderDescID) { this.orderDescID = orderDescID; }
	public void setOrderStatusID(int orderStatusID) { this.orderStatusID = orderStatusID; }
	public void setDate(String date) { this.date = date; }
	public void setDeliveryPointID(int deliveryPointID) { this.deliveryPointID = deliveryPointID; }

	//GETTERS//
	public float getPrice() {
		return price;
	}

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

	public ObservableList<OrderTableView> getOrderDetails(){
		return CreateOrderController.getItems();
	}

	public int getOrderDescID() { return orderDescID; }

	public int getOrderStatusID() { return orderStatusID; }

	public String getDate() { return date;}

	public int getDeliveryPointID() { return deliveryPointID; }

	public ObservableList<OrderTableView> getItemz() {
		return itemz;
	}
	/**
	 * Method to convert DB data to Strings
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return "Order{" +
				"itemtype='" + itemtype + '\'' +
				", orderID=" + orderID +
				", customerID=" + customerID +
				", qty=" + qty +
				", orderstatus='" + orderstatus + '\'' +
				", deliverypointname='" + deliverypointname + '\'' +
				", orderDescID=" + orderDescID +
				", status='" + status + '\'' +
				", clothtype='" + clothtype + '\'' +
				", orderdate='" + orderdate + '\'' +
				", orderStatusID=" + orderStatusID +
				", date='" + date + '\'' +
				", itemz=" + itemz +
				", deliveryPointID=" + deliveryPointID +
				", price=" + price +
				'}';
	}
}