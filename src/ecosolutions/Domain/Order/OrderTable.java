package ecosolutions.Domain.Order;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class OrderTable {
    public static ArrayList<String> clothTypeList;
    public static ArrayList<Integer> clothQTYList;
    private String clothType;
    private int clothQTY;
    public OrderTable(String itemType,int itemQTY){
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


}
