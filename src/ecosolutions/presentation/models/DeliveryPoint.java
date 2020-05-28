package ecosolutions.presentation.models;

public class DeliveryPoint {

	private String location;

	public DeliveryPoint(String location, int amountofOrders) {
		this.location = location;
		AmountofOrders = amountofOrders;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAmountofOrders() {
		return AmountofOrders;
	}

	public void setAmountofOrders(int amountofOrders) {
		AmountofOrders = amountofOrders;
	}

	private int AmountofOrders;

}