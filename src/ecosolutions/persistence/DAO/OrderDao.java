package ecosolutions.persistence.DAO;
import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.presentation.models.Order;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.collections.*;
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
    @Override
    public void save(Order order) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("EXEC insertnewOrder @customerid='"+order.getCustomerID()+"',@orderstatusid='"+order.getOrderStatusID()+ "',@dpid='"+order.getDeliveryPointID()+"',@date='"+order.getDate()+"';");
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }//@customerid int, @orderstatusid int, @dpid int, @date date)
    }
    @Override
    public void update(Order order) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("EXEC  update_orderstatus @status ="+order.getOrderstatus()+", @orderID="+order.getOrderID());
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
    public void update(int id, String status) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("EXEC  update_orderstatus @status ="+status+", @orderID="+id);
            stmt.executeUpdate();

        }catch(Exception e){
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

    public List<Order> checkOrder(int id){
        List<Order> checkorder = new ArrayList<>();
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.createStatement();
            //SQL STATEMENT FOR SELECTING EVERY ORDER RELATED DATA IN MULTIPLE TABLES, CONNECTED THROUGH INNER JOIN AND TBLORDER
            ResultSet rs = stmt.executeQuery("EXEC checkorder @orderID="+ id);
            while(rs.next()){
                var itemtype = rs.getString("fldItemType");
                var qty = rs.getInt("fldQuantity");
                checkorder.add(new Order(itemtype, qty));
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return checkorder;
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
    public static List<Integer> getItemsID(int orderID){
        var conn = DatabaseHandler.getInstance().getConnection();
        List<Integer> itemIDList = new ArrayList<>();
        try{
            var stmt = conn.prepareStatement("SELECT fldItemID FROM tblOrderDescription WHERE fldOrderID = '"+orderID+"';");
            ResultSet s = stmt.executeQuery();
            ResultSetMetaData rsmd = s.getMetaData();
            int collumns = rsmd.getColumnCount();
            while(s.next()){
                for(int i=0; i<collumns;i++){
                    int itemID = Integer.parseInt(s.getString(i+1));
                    itemIDList.add(itemID);
            }}
        }catch (SQLException e){
            e.printStackTrace();
        }
        return itemIDList;
    }
    public static List<String> getItemTypeByID(List<Integer> itemIDList){
        List<String> itemTypes = new ArrayList<>();
        var conn = DatabaseHandler.getInstance().getConnection();
        try {
            for (int i = 0; i < itemIDList.size(); i++) {
                int itemID = itemIDList.get(i);
                var stmt = conn.prepareStatement("SELECT fldItemType FROM tblLaundryItem where fldItemID = '" + itemID + "';");
                ResultSet rs = stmt.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int Collumn = rsmd.getColumnCount();
                while (rs.next()) {
                    String itemType = rs.getString(Collumn);
                    itemTypes.add(i, itemType);
                }}
        }catch (SQLException e){
            e.printStackTrace();
        }
        return itemTypes;
    }
    public static List<Integer> getQuantityByID(List<Integer> itemIDList,int orderID){
        List<Integer> itemQuantity = new ArrayList<>();
        var conn = DatabaseHandler.getInstance().getConnection();
        try {
            for(int i = 0; i<itemIDList.size();i++) {
                int itemID = itemIDList.get(i);
                var stmt = conn.prepareStatement("SELECT fldQuantity FROM tblOrderDescription where fldItemID = '" + itemID + "' AND fldOrderID = '"+orderID+"';");
                ResultSet rs = stmt.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int Collumn = rsmd.getColumnCount();
                while (rs.next()) {
                    int itemQTY = Integer.parseInt(rs.getString(Collumn));
                    itemQuantity.add(i,itemQTY);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return itemQuantity;
    }
    public static int getCustomerIDbyOrderID(int orderID){
        var conn = DatabaseHandler.getInstance().getConnection();
        int custID = 0;
        try {
            var stmt = conn.prepareStatement("SELECT fldCustomerID FROM tblOrder WHERE fldOrderID = '"+orderID+"';");
            ResultSet s = stmt.executeQuery();
            ResultSetMetaData rsmd = s.getMetaData();
            int Collumn = rsmd.getColumnCount();
            custID = Integer.parseInt(s.getString(Collumn));
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return custID;
    }
    public static void issueStatus(int orderID){
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("INSERT INTO tblOrder(fldOrderStatusID) WHERE fldOrderID = '"+orderID+"' VALUE ('7')");
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * This Method is responsible for
     * Fetching DATA from the Database and Adding it to a PieChart
     * @return data
     */
    public static ObservableList<PieChart.Data> getOrderGraphStatistics(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        try {
            var conn = DatabaseHandler.getInstance().getConnection();
            var stmt1 = conn.prepareStatement("SELECT COUNT(*) FROM tblOrder WHERE fldOrderStatusID = 1");
            var stmt2 = conn.prepareStatement("SELECT COUNT(*) FROM tblOrder WHERE fldOrderStatusID = 2");
            var stmt3 = conn.prepareStatement("SELECT COUNT(*) FROM tblOrder WHERE fldOrderStatusID = 3");
            var stmt4 = conn.prepareStatement("SELECT COUNT(*) FROM tblOrder WHERE fldOrderStatusID = 4");
            var stmt5 = conn.prepareStatement("SELECT COUNT(*) FROM tblOrder WHERE fldOrderStatusID = 6");
            var stmt6 = conn.prepareStatement("SELECT COUNT(*) FROM tblOrder WHERE fldOrderStatusID = 7");
            ResultSet rs = stmt1.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Total Confirmed ("+count+")", count));
            }
            rs = stmt2.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Total Under_Way ("+count+")", count));
            }
            rs = stmt3.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Total In_Transit ("+count+")", count));
            }
            rs = stmt4.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Total Cleaning ("+count+")", count));
            }
            rs = stmt5.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Total Delivered ("+count+")", count));
            }
            rs = stmt6.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Total Complete ("+count+")", count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    /**
     * This Method is responsible for
     * Fetching DATA from the Database and Adding it to a PieChart
     * @return data2
     */
    public static ObservableList<PieChart.Data> getEmployeeStatistics(){
        ObservableList<PieChart.Data> data2 = FXCollections.observableArrayList();

        try {
            var conn = DatabaseHandler.getInstance().getConnection();
            var stmt1 = conn.prepareStatement("SELECT COUNT(*) FROM tblEmployee WHERE fldStatusID = 1");
            var stmt2 = conn.prepareStatement("SELECT COUNT(*) FROM tblEmployee WHERE fldStatusID = 2");
            var stmt3 = conn.prepareStatement("SELECT COUNT(*) FROM tblEmployee WHERE fldStatusID = 3");
            ResultSet rs = stmt1.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                data2.add(new PieChart.Data("Total Signed-In ("+count+")" , count));
            }
            rs = stmt2.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                data2.add(new PieChart.Data("Total Signed-Off ("+count+")", count));
            }
            rs = stmt3.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                data2.add(new PieChart.Data("Total On Vacation ("+count+")", count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data2;
    }
    public static ObservableList<javafx.scene.chart.XYChart.Data<Integer, Integer>> getBarChartData(){
        ObservableList<javafx.scene.chart.XYChart.Data<Integer, Integer>> data3 = FXCollections.observableArrayList();
        try {
            var conn = DatabaseHandler.getInstance().getConnection();
            var stmt1 = conn.prepareStatement("SELECT COUNT(*) FROM tblEmployee WHERE fldStatusID = 1");
            var stmt2 = conn.prepareStatement("SELECT COUNT(*) FROM tblEmployee WHERE fldStatusID = 2");
            var stmt3 = conn.prepareStatement("SELECT COUNT(*) FROM tblEmployee WHERE fldStatusID = 3");
            ResultSet rs = stmt1.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                data3.add(new BarChart.Data<>(1,1));
            }
            rs = stmt2.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                data3.add(new BarChart.Data<>(2,2));
            }
            rs = stmt3.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                data3.add(new BarChart.Data<>(3,3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data3;
    }


    private Order exportOrder(ResultSet rs) throws SQLException{
        Order order = new Order();
        order.setOrderID(rs.getInt("fldOrderID"));
        order.setOrderstatus(rs.getString("fldOrderStatus"));
        order.setDeliverypointname(rs.getString("fldDPointName"));
        order.setOrderdate(rs.getString("fldDateofOrder"));
        return order;
    }
}
