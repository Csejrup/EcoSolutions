package ecosolutions.Domain.Order;

import ecosolutions.persistence.DB;

public class OrderDetail {

	private int qty;
	private String clothType;
	private String date;

	public int getQty() {
		return this.qty;
	}

	/**
	 * 
	 * @param qty
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}


	/**
	 * 
	 * @param clothType
	 */
	public void setClothType(String clothType) {
		// TODO - implement ecosolutions.Domain.Order.OrderDetail.setClothType
		throw new UnsupportedOperationException();
	}

	public String getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		// TODO - implement ecosolutions.Domain.Order.OrderDetail.setDate
		throw new UnsupportedOperationException();
	}
	public static int getMaxOrderID(){
		DB.selectSQL("SELECT MAX(fldOrderID) FROM tblOrder");
		return Integer.parseInt(DB.getQueryData());
	}
	/*public static void setStatus(int statusID){
		DB.insertSQL("INSERT INTO tblOrder(fldStatusID) VALUES("+statusID+");");
	}public static int getStatus(){
		DB.selectSQL("SELECT fldStatusID FROM tbl");
	}*/
	public static int getMaxStatusID(){
		DB.selectSQL("SELECT MAX(fldStatusID) FROM tblOrderStatus");
		return Integer.parseInt(DB.getQueryData());
	}
	public static int getMaxOrderDescID(){
		DB.selectSQL("SELECT MAX(fldOrderDescID) FROM tblOrderDescription");
		return Integer.parseInt(DB.getQueryData());
	}

}