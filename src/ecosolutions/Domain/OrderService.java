package ecosolutions.Domain;

import ecosolutions.persistence.DB;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class OrderService {

    private int qty;
    private String clothType;
    private String date;


    public static ArrayList<String> clothTypeList;
    public static ArrayList<Integer> clothQTYList;
    private int clothQTY;
    public OrderService(String itemType, int itemQTY){
      clothType = itemType;
      clothQTY = itemQTY;
    }
    public static void addClothTypeList(String element){
        clothTypeList.add(element);
    } public static void addClothQTYList(int element){
        clothQTYList.add(element);

    }
    public static ArrayList<String> getClothTypeList(){
        return clothTypeList;
    }public static ArrayList<Integer> getClothQTYList(){

        return clothQTYList;
    }
    public void setDate(String date) {
        // TODO - implement ecosolutions.Domain.Order.OrderDetail.setDate
        throw new UnsupportedOperationException();
    }
    public static int getMaxOrderID(){
        DB.selectSQL("SELECT MAX(fldOrderID) FROM tblOrder");
        return Integer.parseInt(DB.getQueryData());
    }
    /*public static void setStatus(int statusID){
        DB.insertSQL("INSERT INTO tblOrder(fldStatusID) VALUES("+statusID+");");
    }public static int getStatus(){
        DB.selectSQL("SELECT fldStatusID FROM tbl");
    }*/
    public static int getMaxStatusID(){
        DB.selectSQL("SELECT MAX(fldStatusID) FROM tblOrderStatus");
        return Integer.parseInt(DB.getQueryData());
    }
    public static int getMaxOrderDescID(){
        DB.selectSQL("SELECT MAX(fldOrderDescID) FROM tblOrderDescription");
        return Integer.parseInt(DB.getQueryData());
    }

}
