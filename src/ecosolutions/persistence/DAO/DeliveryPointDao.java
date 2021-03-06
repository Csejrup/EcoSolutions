package ecosolutions.persistence.DAO;

import ecosolutions.persistence.DatabaseHandler;

import java.sql.*;


public class DeliveryPointDao {
    /**
     * METHOD FOR GETTING DELIVERYPOINT ID BASED ON LOGGED IN ACCOUNT
     * @param accountID
     * @return dpID
     */
    public static int getIdByAccID(int accountID){
        var conn = DatabaseHandler.getInstance().getConnection();
        int dpID = 0;
        try {
            var stmt = conn.prepareStatement("SELECT fldDeliveryPointID FROM tblDeliveryPoint WHERE fldAccountID ='" + accountID+"';");
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int Collumn = rsmd.getColumnCount();
            while (rs.next()){
            dpID = Integer.parseInt(rs.getString(Collumn));}
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dpID;

    }
}
