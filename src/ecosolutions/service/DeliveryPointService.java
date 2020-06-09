package ecosolutions.service;

import ecosolutions.persistence.DAO.DeliveryPointDao;

public class DeliveryPointService {

    public static int getDpID(int accountID){
        return DeliveryPointDao.getIdByAccID(accountID);

    }
}
