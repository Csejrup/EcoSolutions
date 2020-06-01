package ecosolutions.persistence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Database Class with singleton pattern
 */
public final class DatabaseHandler {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static String port;
    private static String database;
    private static String user;
    private static String password;

    static {
        Properties props = new Properties();

        String fileName = "db.properties";
        fileName = "db.properties";
        InputStream input;
        try{
            input = new FileInputStream(fileName);
            props.load(input);
            port = props.getProperty("port","1433");
            database = props.getProperty("databaseName");
            user=props.getProperty("userName", "sa");
            password=props.getProperty("password");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Database Ready");

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    static {
        createConnection();
    }
    /**
     *  SINGLETON PATTERN
     *  Creates one instance of the Database
     */
    private static DatabaseHandler instance = new DatabaseHandler();
    /**
     * Standard Constructor (empty)
     */
    private DatabaseHandler(){ }
    public static DatabaseHandler getInstance(){ return instance; }
    /**
     * Another way of creating a database instance,
     * is not thread friendly:
     * Advantage:
     * Faster when handling big classes with many objects
     * or program that needs to execute many objects in short time
     * Disadvantage:
     * creates multiple threads if 1st thread finds getInstance null
     * and a 2nd thread does the same, but 1st thread didn't get around,
     * thereby 2 instances would have been created.
     */
    /*
    private static DatabaseHandler instance;
    public static DatabaseHandler getInstance(){
        if(instance == null){
            instance = new DatabaseHandler();
        }
        return instance;
    }
     */
    /**
     * Connects the program to the database
     */
    private static void createConnection() {
        try{
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:"+port+";databaseName="+database,user,password);
            System.out.println("Database Connection Established");
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Database connection failed");
        }
    }
    public static ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        }
        catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        finally {
        }
        return result;
    }
    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        }
        catch (SQLException e) {
            System.out.println("Exception at execQuery:dataHandler" + e.getLocalizedMessage());
            return false;
        }
        finally {
        }
    }

    public static ObservableList<PieChart.Data> getOrderGraphStatistics(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        try {

            String query1 = "SELECT COUNT(*) FROM tblOrder WHERE fldOrderStatusID = 1";
            String query2 = "SELECT COUNT(*) FROM tblOrder WHERE fldOrderStatusID = 2";
            String query3 = "SELECT COUNT(*) FROM tblOrder WHERE fldOrderStatusID = 3";
            String query4 = "SELECT COUNT(*) FROM tblOrder WHERE fldOrderStatusID = 4";
            ResultSet rs = execQuery(query1);
            if(rs.next()){
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Total Pending ("+count+")", count));
            }
            rs = execQuery(query2);
            if(rs.next()){
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Total Ready ("+count+")", count));
            }
            rs = execQuery(query3);
            if(rs.next()){
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Total Cleaning ("+count+")", count));
            }
            rs = execQuery(query4);
            if(rs.next()){
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Total Complete ("+count+")", count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    public static ObservableList<PieChart.Data> getEmployeeStatistics(){
        ObservableList<PieChart.Data> data2 = FXCollections.observableArrayList();
        try {

            String query1 = "SELECT COUNT(*) FROM tblEmployee WHERE fldStatusID = 1";
            String query2 = "SELECT COUNT(*) FROM tblEmployee WHERE fldStatusID = 2";
            String query3 = "SELECT COUNT(*) FROM tblEmployee WHERE fldStatusID = 3";
            ResultSet rs = execQuery(query1);
            if(rs.next()){
                int count = rs.getInt(1);
                data2.add(new PieChart.Data("Total Signed-In ("+count+")" , count));
            }
            rs = execQuery(query2);
            if(rs.next()){
                int count = rs.getInt(1);
                data2.add(new PieChart.Data("Total Signed-Off ("+count+")", count));
            }
            rs = execQuery(query3);
            if(rs.next()){
                int count = rs.getInt(1);
                data2.add(new PieChart.Data("Total On Vacation ("+count+")", count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data2;
    }
    /**
     * Disconnects the program from the database
     * @throws Exception
     */
    public void close() throws Exception{
        conn.close();
    }
    public Connection getConnection(){
        return conn;
    }
}
