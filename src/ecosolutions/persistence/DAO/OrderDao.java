package ecosolutions.persistence.DAO;

import ecosolutions.persistence.DB;
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
                /*
                var _ID = rs.getInt("fldOrderID");
               // String orderID = Integer.toString(_ID);
               // Order order = new Order(orderID);
                Order order = new Order(_ID);
                return Optional.of(order);

                 */
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();

        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.createStatement();
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

    @Override
    public void save(Order order) {

    }

    @Override
    public void update(Order order) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("");
            ResultSet rs = stmt.executeQuery();

        }catch(Exception e){
            e.printStackTrace();

        }
    }

    @Override
    public void delete(int id) {

    }
    /*


    private Order extractOrder(ResultSet rs) throws SQLException{
        Order order = new Order();
        order.setOrderID(rs.getInt("fldOrderID"));
        order.setQty(rs.getInt("fldQuantity"));
        return order;
    }

     */

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
