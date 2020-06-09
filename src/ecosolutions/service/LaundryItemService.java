package ecosolutions.service;

import ecosolutions.persistence.DAO.LaundryItemDao;


import javafx.collections.ObservableList;

/**
 * This Class is a service class for LaundryItemDao and Connects to the View
 */
public class LaundryItemService {

	public static ObservableList<String> getItemTypes(){
		return LaundryItemDao.getLaundryTypes();
	}
	public static int getID(String clothType){
		return LaundryItemDao.getItemID(clothType);

	}
	public static float getPrice(String clothType){
		return LaundryItemDao.getPrice(clothType);
	}

}