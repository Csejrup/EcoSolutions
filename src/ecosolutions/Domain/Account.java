package ecosolutions.Domain;
/**
 * This Class Represents the Model of an Account
 * And contains all the attributes of an Account
 */
public class Account {
    //VARIABLES//
    private int account_id;
    private String pw;
    private String username;
    private static int staticaccountID;
    //DEFAULT CONSTRUCTOR//
    public Account(){
    }
    //SETTERS//
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public static void setStaticAccountID(int staticaccountID) {
        staticaccountID = staticaccountID;
    }
    //GETTERS//
    public int getAccount_id() {
        return account_id;
    }
    public String getPw() {
        return pw;
    }
    public String getUsername() {
        return username;
    }
    public static int getStaticAccountID() {
        return staticaccountID;
    }
    /**
     * Method to convert DB data to Strings
     * @return
     */
    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", pw='" + pw + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
