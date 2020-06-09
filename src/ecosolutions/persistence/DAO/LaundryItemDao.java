package ecosolutions.persistence.DAO;
import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.Domain.DeliveryPoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO Class Responsible for connecting with the Database and fetch an orders laundry items and its information
 * CRUD - Create, retrieve, update, delete
 */
public class LaundryItemDao implements Dao<DeliveryPoint>{
    @Override
    public Optional<DeliveryPoint> getbyID(int id) {
        //EMPTY
        return null;
    }
    @Override
    public List<DeliveryPoint> getAll() {
        List<DeliveryPoint> deliveryPoints = new ArrayList<>();

        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.createStatement();
            //SQL STATEMENT FOR SELECTING EVERY ORDER RELATED DATA IN MULTIPLE TABLES, CONNECTED THROUGH INNER JOIN AND TBLORDER
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblDeliveryPoint");
            while(rs.next()){
                DeliveryPoint deliveryPoint = exportDeliveryPoint(rs);
                deliveryPoints.add(deliveryPoint);
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return deliveryPoints;
    }
    @Override
    public void save(DeliveryPoint deliveryPoint) {
        //EMPTY
    }

    @Override
    public void update(DeliveryPoint deliveryPoint) {
        //EMPTY
    }

    @Override
    public void delete(DeliveryPoint deliveryPoint) {
        //EMPTY
    }
    public static ObservableList<String> getLaundryTypes(){
        ObservableList<String> laundryTypeItems = FXCollections.observableArrayList();
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("SELECT fldItemType FROM tblLaundryItem");
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int collumns = rsmd.getColumnCount();
            while(rs.next()){
            for(int i = 0; i<collumns;i++){
                String itemType = rs.getString(i+1);
                laundryTypeItems.add(i,itemType);
            }}
            stmt.close();
            rs.close();
            }
        catch (SQLException e){
            e.printStackTrace();
        }
        return laundryTypeItems;
    }
    public static int getItemID(String itemType){
        var conn = DatabaseHandler.getInstance().getConnection();
        int itemID = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT fldItemID FROM tblLaundryItem WHERE fldItemType = '"+itemType+"';");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
            itemID = Integer.parseInt(rs.getString(1));
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return itemID;
    }
    public static float getPrice(String itemType){
        var conn = DatabaseHandler.getInstance().getConnection();
        float itemPrice = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT fldPrice FROM tblLaundryItem WHERE fldItemType= '"+itemType+"';");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                itemPrice = Float.parseFloat(rs.getString(1));
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return itemPrice;
    }
    private DeliveryPoint exportDeliveryPoint(ResultSet rs){
        DeliveryPoint deliveryPoint = new DeliveryPoint();
        return deliveryPoint;
    }
}
