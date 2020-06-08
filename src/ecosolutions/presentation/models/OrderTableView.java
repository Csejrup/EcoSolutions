package ecosolutions.presentation.models;


import javafx.beans.property.SimpleStringProperty;

public class OrderTableView {
    private SimpleStringProperty clothType;
    private int clothQty;
    private int itemID;
    private float itemPrice;

    public OrderTableView(String itemType, int itemQTY, int itemID, float itemPrice){
        clothType = new SimpleStringProperty(itemType);
        clothQty =  itemQTY;
        this.itemID = itemID;
        this.itemPrice = itemPrice;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
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
