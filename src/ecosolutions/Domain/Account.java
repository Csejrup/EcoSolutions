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
    // --Commented out by Inspection (14-06-2020 23:58):private static int staticaccountID;
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
// --Commented out by Inspection START (14-06-2020 23:58):
//    public static void setStaticAccountID(int staticaccountID) {
//        staticaccountID = staticaccountID;
//    }
// --Commented out by Inspection STOP (14-06-2020 23:58)
    //GETTERS//
    public int getAccount_id() {
        return account_id;
    }
    public String getPw() {
        return pw;
    }
// --Commented out by Inspection START (14-06-2020 23:58):
//    public String getUsername() {
//        return username;
//    }
// --Commented out by Inspection STOP (14-06-2020 23:58)
// --Commented out by Inspection START (14-06-2020 23:58):
//    public static int getStaticAccountID() {
//        return staticaccountID;
//    }
// --Commented out by Inspection STOP (14-06-2020 23:58)
    /**
     * Method to convert DB data to Strings
     *
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
