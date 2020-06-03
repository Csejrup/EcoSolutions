package ecosolutions.Domain;

import ecosolutions.persistence.DAO.DeliveryPointDao;
import ecosolutions.persistence.DB;
import ecosolutions.presentation.models.OrderTableView;
import javafx.collections.ObservableList;

public class DeliveryPointService extends AccountService {


	private String assistantName;

	public static boolean login(String employeeID, String password) {
		DB.selectSQL("SELECT fldPassword from tblAccount WHERE fldEmployeeID = ('" + employeeID + "')");
		String PW = DB.getQueryData();
		DB.selectSQL("SELECT fldEmployeeID from tblAccount WHERE fldEmployeeID = ('" + employeeID + "')");
		String UN = DB.getQueryData();
		return employeeID.equals(UN) && password.equals(PW);
	}

	public void managePayment() {
		// TODO - implement ecosolutions.Domain.Account.ShopAssistant.managePayment
		throw new UnsupportedOperationException();
	}

	public void setStatus() {
		// TODO - implement ecosolutions.Domain.Account.ShopAssistant.setStatus
		throw new UnsupportedOperationException();
	}
	public static ObservableList<String> getItemTypes(){

		return DeliveryPointDao.getLaundryTypes();
	}

	public static int getID(String clothType){
		return DeliveryPointDao.getItemID(clothType);

	}

}