package ecosolutions.presentation.models;
/**
 * This Class Represents the Model of an Account
 * And contains all the attributes of an Account
 */
public class Account {

    //VARIABLES//
    private int account_id;
    private static int staticAccountID;
    private String pw;
    private String username;

    //DEFAULT CONSTRUCTOR//
    public Account(){

    }
    //CONSTRUCTOR//
    public Account(int account_id, String pw, String username) {
        this.account_id = account_id;
        this.pw = pw;
        this.username = username;
        staticAccountID = account_id;
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


    //GETTERS//
    public int getAccount_id() {
        return account_id;
    }
    public static int getStaticAccountID() {
        return staticAccountID;
    }

    public String getPw() {
        return pw;
    }

    public String getUsername() {
        return username;
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
