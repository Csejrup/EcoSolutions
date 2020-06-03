package ecosolutions.persistence.DAO;
import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.presentation.controllers.DeliveryPointController;
import ecosolutions.presentation.models.Order;
import ecosolutions.presentation.models.OrderTableView;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * DAO Class Responsible for connecting with the Database and fetch an Order and its information
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
            ResultSet rs = stmt.executeQuery("EXEC orderdataSelect");
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

            var stmt = conn.prepareStatement("INSERT INTO tblOrder(fldCustomerID,fldOrderStatusID," +
                    "fldDateofOrder) VALUES('"+order.getCustomerID()+"','"+order.getOrderStatusID()+ "','"+order.getDate()+"');");
            stmt.executeQuery();

           /* var stmt = conn.prepareStatement("");
            stmt.setInt(1, order.getOrderID());
            stmt.setInt(2, order.getOrderID());
            stmt.setInt(3, order.getOrderID());
            stmt.setInt(4, order.getOrderID());
            stmt.setInt(5, order.getOrderID());
            stmt.setInt(6, order.getOrderID());*/

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
            var stmt = conn.prepareStatement("DELETE FROM tblOrder WHERE fldOrderID =?");
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

    /**
     * HANDLING ORDER DESCRIPTION
     * @param order
     * @throws SQLException
     */
    public void addOrderDetails(Order order){
        var conn = DatabaseHandler.getInstance().getConnection();
        try {
            for(int i = 0; i<order.getItemz().size();i++){
                String clothType = order.getItemz().get(i).getClothType();
                int clothQTY = order.getItemz().get(i).getClothQty();
            var stmt = conn.prepareStatement("INSERT INTO tblOrderDescription(fldOrderID,fldItemQuantity,fldItemType,fldPrice,fldWeight) VALUES ('"+getLastOrderID()+"','"+clothType+"','"+clothQTY+"','"+order.getPrice()+"','"+order.getWeight()+"');");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void addCustomerID(int customerID) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("INSERT INTO tblOrder(fldCustomerID) VALUES('"+customerID+"');");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static int getLastOrderID()  {
        var conn = DatabaseHandler.getInstance().getConnection();
        int lastOrderID = 0;
        try {
            var stmt = conn.prepareStatement("SELECT MAX(fldOrderID) FROM tblOrder");
            ResultSet s = stmt.executeQuery();
            ResultSetMetaData rsmd = s.getMetaData();
            int Collumn = rsmd.getColumnCount();
            lastOrderID =  Integer.parseInt(s.getString(Collumn));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lastOrderID;
    }
    public static int getLastCustomerID() throws SQLException {
        var conn = DatabaseHandler.getInstance().getConnection();
        var stmt = conn.prepareStatement("SELECT MAX(fldCustomerID) FROM tblCustomer");
        ResultSet s = stmt.executeQuery();
        ResultSetMetaData rsmd = s.getMetaData();
        int Collumn = rsmd.getColumnCount();
        return Integer.parseInt(s.getString(Collumn));
    }
    public static int getLastDescID() throws SQLException {
        var conn = DatabaseHandler.getInstance().getConnection();
        var stmt = conn.prepareStatement("SELECT MAX(fldOrderDesID) FROM tblOrderDescription");
        ResultSet s = stmt.executeQuery();
        ResultSetMetaData rsmd = s.getMetaData();
        int Collumn = rsmd.getColumnCount();
        return Integer.parseInt(s.getString(Collumn));
    }



}
