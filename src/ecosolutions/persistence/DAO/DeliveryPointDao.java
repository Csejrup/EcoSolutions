package ecosolutions.persistence.DAO;
import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.presentation.models.DeliveryPoint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryPointDao implements Dao<DeliveryPoint>{

    @Override
    public Optional<DeliveryPoint> getbyID(int id) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("SELECT fldDeliveryPointID FROM tblDeliveryPoint WHERE fldDeliveryPointID ="+ id);
            //stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                DeliveryPoint deliveryPoint = new DeliveryPoint();
                deliveryPoint.setDpID(rs.getInt("fldDeliveryPointID"));
                return Optional.of(deliveryPoint);
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
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

    }

    @Override
    public void update(DeliveryPoint deliveryPoint) {

    }

    @Override
    public void delete(DeliveryPoint deliveryPoint) {

    }
    private DeliveryPoint exportDeliveryPoint(ResultSet rs) throws SQLException{
        DeliveryPoint deliveryPoint = new DeliveryPoint();

        return deliveryPoint;

    }
}
