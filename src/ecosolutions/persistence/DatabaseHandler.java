package ecosolutions.persistence;
import java.io.*;
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
    private static final DatabaseHandler instance = new DatabaseHandler();
    /**
     * Standard Constructor (empty)
     */
    private DatabaseHandler(){ }
    public static DatabaseHandler getInstance(){ return instance; }
    /*
      Another way of creating a database instance,
      is not thread friendly:
      Advantage:
      Faster when handling big classes with many objects
      or program that needs to execute many objects in short time
      Disadvantage:
      creates multiple threads if 1st thread finds getInstance null
      and a 2nd thread does the same, but 1st thread didn't get around,
      thereby 2 instances would have been created.
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
// --Commented out by Inspection START (15-06-2020 00:00):
//    public static ResultSet execQuery(String query) {
//        ResultSet result;
//        try {
//            stmt = conn.createStatement();
//            result = stmt.executeQuery(query);
//        }
//        catch (SQLException ex) {
//            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
//            return null;
//        }
//        return result;
//    }
// --Commented out by Inspection STOP (15-06-2020 00:00)
// --Commented out by Inspection START (15-06-2020 00:00):
//    public boolean execAction(String qu) {
//        try {
//            stmt = conn.createStatement();
//            stmt.execute(qu);
//            return true;
//        }
//        catch (SQLException e) {
//            System.out.println("Exception at execQuery:dataHandler" + e.getLocalizedMessage());
//            return false;
//        }
//    }
// --Commented out by Inspection STOP (15-06-2020 00:00)

// --Commented out by Inspection START (15-06-2020 00:00):
//    /**
//     * Disconnects the program from the database
//     */
//    public void close() {
//        try {
//            conn.close();
//            System.out.println("Connection Closed");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Disconnection Failed");
//        }
//    }
// --Commented out by Inspection STOP (15-06-2020 00:00)
    public Connection getConnection(){
        return conn;
    }
}
