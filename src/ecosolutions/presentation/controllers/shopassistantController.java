package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import ecosolutions.Domain.Account.Account;
import ecosolutions.Domain.Account.Customer;
import ecosolutions.Domain.DeliveryPoint.DeliveryPoint;
import ecosolutions.Domain.Order.Order;
import ecosolutions.Domain.Order.OrderDetail;
import ecosolutions.persistence.DB;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class shopassistantController extends AbstractController{
    //ADD BASIC CLOTH TYPES
    ObservableList<String> laundryType = FXCollections.observableArrayList("T-Shirt","Jacket","Carpet","Jeans","Suit","Blinds");
    private List<String>  itemList = new ArrayList<>();
    private List<Integer> itemQuantity = new ArrayList<>();
    @FXML
    private JFXButton btnLogOut;

    @FXML
    private AnchorPane rootpane;

    @FXML
    private Pane pane1;
    @FXML
    private JFXButton btnRefresh;

    @FXML
    private JFXListView<String> itemListView = new JFXListView<>();


    @FXML
    private JFXButton btnConfirm;

    @FXML
    private JFXTextField dueTextField;

    @FXML
    private JFXTextField nameTextfield;

    @FXML
    private JFXTextField phoneNoTextField;

    @FXML
    private TextField qtyTextField;

    @FXML
    void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "loginscreen.fxml");
    }

    @FXML
    void handleOrderConfirm(ActionEvent event) {
        String customerName = nameTextfield.getText();
        int orderID = OrderDetail.getMaxOrderID()+1;
        int customerID = Customer.getCustomerID()+1;
        int deliveryPointID = Account.getDeliveryPoint();
        //String customerSurname = surnameTextField.getText()
        String customerPhone = phoneNoTextField.getText();
        //AVOIDING ZERO BY ADDING 1 TO list.
        int quantityOfItemsInOrder = itemQuantity.size();
        //Assigning new ListID for fldListID.
        //GETTING last statusID
        int statusID = OrderDetail.getMaxStatusID()+1;
        String status = "REGISTERED";
        int orderDescID = OrderDetail.getMaxOrderDescID()+1;
        Date now = new Date();
        SimpleDateFormat sdp = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdp.format(now);



        if(customerName!=null&&customerPhone!=null&&itemList.size()!=0&&itemQuantity.size()!=0) {
            DB.insertSQL("INSERT INTO tblOrder(fldOrderID,fldCustomerID,fldOrderDesID,fldOrderStatusID,fldDeliveryPointID,fldDateofOrder) VALUES ('"+orderID+"','"+customerID+"','"+orderDescID+"','"+statusID+"','"+deliveryPointID+"','"+date+"');");
            /**
             * LOOP FOR ADDING ITEMS FROM LIST INTO DATABASE
             */
                for (int i=0;i<itemList.size();i++){
                    String itemType = itemList.get(i);
                    int itemQTY = itemQuantity.get(i);
                    DB.insertSQL("INSERT INTO tlbOrderDescription(fldOrderDesID,fldOrderID,fldItemQuantity,fldItemType,fldPrice,fldWeight) VALUES ('"+orderDescID+"','"+orderID+"','"+itemQTY+"','"+itemType+"');");
                }
                // INSERTING PRE-DEFINED STATUS INTO DATABASE
                DB.insertSQL("INSERT INTO tblOrderStatus(fldOrderStatusID,fldOrderStatus) VALUES ('"+statusID+"','"+status+"');");
        }
        /**
         * FOR PRICE
         * DB.selectSQL("SELECT fldPrice FROM tblItem WHERE fldItemType = 'orderType')
         */
    }
    @FXML
    void refreshItems(ActionEvent event){
        itemListView.setItems(laundryType);
    }

    /**
     * ADDING ITEM TO THE 'BASKET'
     * @param event
     */
    @FXML
    void addToBasket(ActionEvent event){

        String orderType = itemListView.getSelectionModel().getSelectedItem();
        int quantityOfCloth = Integer.parseInt(qtyTextField.getText());
        if(orderType!=null&&quantityOfCloth>0){
            itemList.add(orderType);
            itemQuantity.add(quantityOfCloth);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("PICK CLOTH TYPE AND INSERT QUANTITY");
            alert.show();
        }





    }


}