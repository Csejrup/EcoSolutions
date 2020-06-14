package ecosolutions.persistence.DAO;
import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.Domain.Account;

import java.sql.*;
import java.util.*;

/**
 * DAO Class Responsible for connecting with the Database and fetch an Account and its information
 * CRUD - Create, retrieve, update, delete
 */
public class AccountDao {
    /**
     * Gets Account From DataBase based on Username
     * @param username
     * @return
     */
    public Optional<Account> getByUsername(String username){
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblAccount WHERE fldUsername ='" + username +"'");
            if(rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("fldUsername"));
                acc.setAccount_id(rs.getInt("fldAccountID"));
                acc.setPw(rs.getString("fldPassword"));
                return Optional.of(acc);
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}





