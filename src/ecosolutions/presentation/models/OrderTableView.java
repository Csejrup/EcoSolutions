package ecosolutions.presentation.models;

import ecosolutions.Singleton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderTableView {
    private SimpleStringProperty clothType;
    private int clothQty;
    private int itemID;

    public OrderTableView(String itemType, int itemQTY, int itemID){
        clothType = new SimpleStringProperty(itemType);
        clothQty =  itemQTY;
        this.itemID = itemID;
    }

    public String getClothType() {
        return clothType.get();
    }

    public SimpleStringProperty clothTypeProperty() {
        return clothType;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setClothType(String clothType) {
        this.clothType.set(clothType);
    }

    public int getClothQty() {
        return clothQty;
    }

    public void setClothQty(int clothQty) {
        this.clothQty = clothQty;
    }
}
