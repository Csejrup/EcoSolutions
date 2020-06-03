package ecosolutions.persistence.DAO;

import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.presentation.controllers.LoginController;
import ecosolutions.presentation.models.Account;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//TODO FOR LOGIN IMPLEMENTATION FUNCTIONALITY
public class AccountDao implements Dao<Account> {
    @Override
    public Optional<Account> getbyID(int id)
    {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("SELECT fldAccountID FROM tblAccount WHERE fldAccountID =" + id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                var acc = new Account();
                acc.setAccount_id(rs.getInt("fldAccountID"));
                return Optional.of(acc);
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.createStatement();
            //SQL STATEMENT FOR SELECTING EVERY ORDER RELATED DATA IN MULTIPLE TABLES, CONNECTED THROUGH INNER JOIN AND TBLORDER
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblAccount");
            while(rs.next()){
                Account acc = exportAccount(rs);
                accounts.add(acc);
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }
    //TODO CREATE THIS
    @Override
    public void save(Account acc) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("");
            stmt.setInt(1, acc.getAccount_id());
            stmt.setString(2, acc.getPw());
            stmt.setString(3, acc.getUsername());
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //TODO COMPLETE THIS FOR UPDATING AN ORDER IN DATABASE IN ITS RIGHT PLACES
    @Override
    public void update(Account acc) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("UPDATE tblAccount WHERE fldAccountID =?");
            //ResultSet rs = stmt.executeQuery();
            stmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Account acc) {
        var conn = DatabaseHandler.getInstance().getConnection();
        try{
            var stmt = conn.prepareStatement("DELETE FROM tblOrder WHERE fldOrderID =?");
            stmt.setInt(1, acc.getAccount_id());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private Account exportAccount(ResultSet rs) throws SQLException{
        var account = new Account();
        account.setAccount_id(rs.getInt("fldAccountID"));
        account.setPw(rs.getString("fldPassword"));
        account.setUsername(rs.getString("fldUsername"));
        return account;
    }
    /*
    private static int getAccountID() throws SQLException {
        var conn = DatabaseHandler.getInstance().getConnection();
        int userName = Integer.parseInt(LoginController.userName);
        var stmt = conn.prepareStatement("SELECT fldAccountID FROM tblAccount WHERE fldUsername = '"+userName+"';");
        ResultSet s = stmt.executeQuery();
        ResultSetMetaData rsmd = s.getMetaData();
        int Collumn = rsmd.getColumnCount();
        return Integer.parseInt(s.getString(Collumn));
    }
    public static int getDeliveryPointID() throws SQLException{
        var conn = DatabaseHandler.getInstance().getConnection();
        int accountID = getAccountID();
        var stmt = conn.prepareStatement("SELECT fldDeliveryPointID FROM tblDeliveryPoint WHERE fldAccountID = '"+accountID+"';");
        ResultSet s = stmt.executeQuery();
        ResultSetMetaData rsmd = s.getMetaData();
        int Collumn = rsmd.getColumnCount();
        return Integer.parseInt(s.getString(Collumn));
    }
    */
}
