package Test;

import ecosolutions.persistence.DatabaseHandler;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.sql.*;


public class testDB {

    /**
     * Method that tests database connection
     * Creates a new table, and inserts new data, drops the table at the end
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void runDBTest() throws SQLException {
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
