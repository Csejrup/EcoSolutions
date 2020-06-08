package ecosolutions.presentation.models;
/**
 *
 */
public class DeliveryPoint {

	private int dpID;
	private String location;


	public DeliveryPoint(){

	}
	public DeliveryPoint(String location,int dpID) {
		this.location = location;
		this.dpID = dpID;
	}
	public int getDpID(){
		return dpID;
	}

	public void setDpID(int dpID){
		this.dpID = dpID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}