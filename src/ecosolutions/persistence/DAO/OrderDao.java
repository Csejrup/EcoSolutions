package ecosolutions.persistence.DAO;
import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.presentation.models.Order;

import java.sql.*;
import java.util.*;

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
            stmt.executeUpdate();
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

    public List<Order> laundryworkerGetStatus(){
        List<Order> lworders = new ArrayList<>();
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.createStatement();
            //SQL STATEMENT FOR SELECTING EVERY ORDER RELATED DATA IN MULTIPLE TABLES, CONNECTED THROUGH INNER JOIN AND TBLORDER
            ResultSet rs = stmt.executeQuery("EXEC getorderstatus");
            while(rs.next()){
                var id = rs.getInt("fldOrderID");
                var status = rs.getString("fldOrderStatus");
                lworders.add(new Order(id, status));
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return lworders;

    }
    public List<Order> driverGetStatus(){
        List<Order> driverorders = new ArrayList<>();
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.createStatement();
            //SQL STATEMENT FOR SELECTING EVERY ORDER RELATED DATA IN MULTIPLE TABLES, CONNECTED THROUGH INNER JOIN AND TBLORDER
            ResultSet rs = stmt.executeQuery("EXEC orderstatus_forDriver");
            while(rs.next()){
                var id = rs.getInt("fldOrderID");
                var status = rs.getString("fldOrderStatus");
                var dpname = rs.getString("fldDPointName");
                driverorders.add(new Order(id, status,dpname));
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return driverorders;
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
                int clothQTY = order.getItemz().get(i).getClothQty();
                int itemID = order.getItemz().get(i).getItemID();
            var stmt = conn.prepareStatement("INSERT INTO tblOrderDescription(fldOrderID,fldItemID,fldQuantity) VALUES ('"+getLastOrderID()+"','"+itemID+"','"+clothQTY+"');");
            stmt.executeUpdate();
            stmt.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
   /* public static void addCustomerID(int customerID) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
           *//* java.util.Date now = new Date();
            SimpleDateFormat sdp = new SimpleDateFormat("yyyy/MM/dd");
            String date = sdp.format(now);*//*
            var stmt = conn.prepareStatement("INSERT INTO tblOrder(fldCustomerID) VALUES('"+customerID+"');");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }*/

    public static int getLastOrderID()  {
        var conn = DatabaseHandler.getInstance().getConnection();
        int lastOrderID = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT MAX(fldOrderID) FROM tblOrder");
            ResultSet s = stmt.executeQuery();
            if(s.next()){

            lastOrderID =  s.getInt(1);
            }
            stmt.close();
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
