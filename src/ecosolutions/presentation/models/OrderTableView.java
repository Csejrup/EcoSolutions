package ecosolutions.presentation.models;

import java.util.ArrayList;
import java.util.List;

public class OrderTableView {
    ArrayList<String> clothType;
    ArrayList<Integer> clothQty;

    public OrderTableView(){}
    public OrderTableView(ArrayList<String>itemType,ArrayList<Integer> itemQTY){
        clothType = itemType;
        clothQty = itemQTY;
    }

    public ArrayList<String> getClothTypeList(){
        return clothType;
    }
    public ArrayList<Integer> getClothQtyList(){
        return clothQty;
    }
}
