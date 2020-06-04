package Test;

import ecosolutions.persistence.DatabaseHandler;

import java.io.IOException;
import java.sql.*;

public class testDB {

    public static void main(String[] args) {
        try{
            runDBTest();
        }catch(SQLException | IOException e){
            e.printStackTrace();
        }
    }
    public static void runDBTest() throws SQLException, IOException {
        var conn = DatabaseHandler.getInstance().getConnection();

        try {
            var stmt = conn.createStatement();
            stmt.execute("CREATE TABLE tblTESTDB (Message CHAR(40))");
            stmt.execute("INSERT INTO tblTESTDB VALUES ('Hello, Corona Free World!')");
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblTESTDB");
            rs.next();
            System.out.println(rs.getString(1));
            stmt.execute("DROP TABLE tblTESTDB");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
