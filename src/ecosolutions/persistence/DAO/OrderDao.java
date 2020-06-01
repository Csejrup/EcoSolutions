package ecosolutions.persistence.DAO;
import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.presentation.models.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO Class Responsible for connecting with the Database and fetch table Order (tblOrder)
 * CRUD - Create, retrieve, update, delete
 */
public class OrderDao implements Dao<Order>{
    @Override
    public Optional<Order> getbyID(int id)
    {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("SELECT fldOrderID FROM tblOrder WHERE fldOrderID =" + id);
            //stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Order order = new Order();
                order.setOrderID(rs.getInt("fldOrderID"));
                return Optional.of(order);
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.createStatement();
            //SQL STATEMENT FOR SELECTING EVERY ORDER RELATED DATA IN MULTIPLE TABLES, CONNECTED THROUGH INNER JOIN AND TBLORDER
            ResultSet rs = stmt.executeQuery("SELECT tblOrder.fldOrderID, tblOrderStatus.fldOrderStatus, tblDeliveryPoint.fldDPointName " +
                    "FROM tblDeliveryPoint INNER JOIN " +
                    "(tblOrder INNER JOIN tblOrderStatus ON tblOrderStatus.fldOrderStatusID = tblOrder.fldOrderStatusID) " +
                    "ON tblDeliveryPoint.fldDeliveryPointID = tblOrder.fldDeliveryPointID");
            while(rs.next()){
                Order order = exportOrder(rs);
                orders.add(order);
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return orders;
    }
    //TODO CREATE THIS
    @Override
    public void save(Order order) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("");
            stmt.setInt(1, order.getOrderID());
            stmt.setInt(2, order.getOrderID());
            stmt.setInt(3, order.getOrderID());
            stmt.setInt(4, order.getOrderID());
            stmt.setInt(5, order.getOrderID());
            stmt.setInt(6, order.getOrderID());

            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //TODO COMPLETE THIS FOR UPDATING AN ORDER IN DATABASE IN ITS RIGHT PLACES
    @Override
    public void update(Order order) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("EXEC  update_orderstatus @status ="+order.getOrderstatus()+", @orderID="+order.getOrderID());
            //ResultSet rs = stmt.executeQuery();
            stmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Order order) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("DELETE FROM tblOrder WHERE fldOrder =?");
            stmt.setInt(1, order.getOrderID());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private Order exportOrder(ResultSet rs) throws SQLException{
        Order order = new Order();
        order.setOrderID(rs.getInt("fldOrderID"));
      //  order.setCustomerID(rs.getInt("fldCustomerID"));
        //order.setQty(rs.getInt("fldOrderID"));
      //  order.setOrderdate(rs.getString("fldDateofOrder"));
       // order.setStatus(rs.getString("fldOrderStatus"));
        order.setOrderstatus(rs.getString("fldOrderStatus"));
        order.setDeliverypointname(rs.getString("fldDPointName"));
        return order;
    }

}
