package ecosolutions.persistence;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DB {
        private static final String _URL = "jdbc:sqlserver://localhost:1433;databaseName=";
        private static String userName;
        private static String password;
        private static String databaseName;
        private static Connection con;
        private static ResultSet resultSet;
        private static PreparedStatement preparedStatement;
        private static boolean PendingData = false;
        private static ResultSetMetaData resultSetMetaData;
        private static int queryColumns;
        private static String queryValue;
        private static List<String> queryList = new ArrayList<>();
        private static final String NOMOREDATA = "|ND|";

        /**
         LOADING THE DRIVER INTO MEMORY
         */
        static {
                Properties props = new Properties();
                String fileName = "db.properties";
                InputStream input;
                try{
                    input = new FileInputStream(fileName);
                    props.load(input);
                    databaseName = props.getProperty("databaseName");
                    userName=props.getProperty("userName", "sa");
                    password=props.getProperty("password");
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                System.out.println("Driver Ready");

            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Driver Failed");
            }
        }
        /**
         CONNECT WITH DATABASE
         */
        public static void connect()  {
            try {
                con = DriverManager.getConnection(_URL+databaseName,userName,password);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Connection Failed"); }
        }
        /**
         CLOSE CONNECTION WITH DATABASE
         */
        public static void Disconnect(){
            try {
                con.close();
            }catch (Exception e ) {
                e.printStackTrace();
            }
        }
        /**
         FUNCTION FOR HANDLING SQL 'SELECT'
         @param sqlQuery
         */
        public static void selectSQL(String sqlQuery)  {
            //CLEARING THE LIST FOR REPEATEDLY USAGE
            queryList.clear();
            try{
                while(!PendingData) {
                    connect();
                    preparedStatement = con.prepareStatement(sqlQuery);
                    resultSet = preparedStatement.executeQuery();
                    resultSetMetaData = resultSet.getMetaData();
                    queryColumns = resultSetMetaData.getColumnCount();
                    System.out.println("Query Executed");
                    PendingData = true;
                }
            }catch (Exception e ){
                e.printStackTrace();
            }
        }/**
         OTHER SQL QUERIES
        @param sqlQuery
         */
        public static void insertSQL(String sqlQuery){otherThanSelect(sqlQuery);}
        public static void updateSQL(String sqlQuery){otherThanSelect(sqlQuery);}
        public static void deleteSQL(String sqlQuery){otherThanSelect(sqlQuery);}

        public static void otherThanSelect(String sqlQuery){
            try{
                while(!PendingData){
                    connect();
                    preparedStatement =  con.prepareStatement(sqlQuery);
                    preparedStatement.execute();
                    preparedStatement.close();
                    PendingData =true;
                    Disconnect();
                }
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                Disconnect();
            }
        }
        /**
         FOR GETTING ONE RESULT FROM SELECT_SQL STATEMENT.
         @return queryValue
         */
        //PROBLEM WITH 'IF THERE IS PREVIOUS SELECT AND NEXT ONE FIELD IS NOT INTO DATABASE SYSTEM TAKES PREVIOSE SQL STATEMENT AND SHOWS IT === TO REPAIR.
        public static String getQueryData(){
            try {
                while (PendingData){
                    while (resultSet.next())
                        queryValue = resultSet.getString(queryColumns);
                    PendingData = false;
                    resultSet.close();
                    preparedStatement.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Disconnect();
            }
            return queryValue;
        }
        /**
         FOR GETTING MORE THAN ONE RESULT FROM SELECT_SQL STATEMENT //AUTOMATICALLY CREATES LIST OF STRING.
         @return queryList
         */
        public static List<String> getSqlList(){
            try{
                while (PendingData){
                    while (resultSet.next())
                        for(int i = 0; i<queryColumns;i++){
                            queryValue = resultSet.getString(i + 1);
                            queryList.add(i,queryValue);
                        }
                    PendingData = false;
                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {try {
                Disconnect();
                resultSet.close();
                preparedStatement.close();
            }catch (Exception e){
                e.printStackTrace();
            }

            }
            return queryList;
        }



        /**
         * FOR CLEARING PENIDING DATA
         */
        private static void cleardata() {
            do {
                String data = getQueryData();
                if (data.equals(NOMOREDATA)) {
                    break;
                }
            } while (true);
        }

    }
