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
import ecosolutions.Domain.Order.OrderTable;
import ecosolutions.persistence.DB;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class shopassistantController extends AbstractController implements Initializable {

    //ADD BASIC CLOTH TYPES
    ObservableList<String> laundryType = FXCollections.observableArrayList("T-Shirt","Jacket","Carpet","Jeans","Suit","Blinds");
    ObservableList<OrderTable> clothTypeOL = FXCollections.observableArrayList();
    ArrayList<String> itemList = OrderTable.getClothTypeList();
    ArrayList<Integer> itemQuantity = OrderTable.getClothQTYList();


    @FXML
    private JFXButton btnLogOut;


    @FXML
    private Pane pane1;

    @FXML
    private JFXListView<String> itemListView = new JFXListView<>();


    @FXML private JFXButton btnConfirm, btnRemove, btnReturn, btnEdit, btnAdd;
    @FXML private TableView<OrderTable> tv;
    @FXML private static TableColumn<List<StringProperty>,String> tcClothType;
    @FXML private static TableColumn<Integer,Integer> tcClothQTY ;

    @FXML private JFXTextField dueTextField, firstnameTextField, phoneNoTextField, lastnameTextField;
    @FXML private TextField qtyTextField;





    @FXML
    void handleAddItem(ActionEvent event) {

    }
    @FXML
    void handleRemove(ActionEvent event) {

    }

    @FXML
    private void handleEditOrder(ActionEvent event) {
        Stage stage = (Stage) btnEdit.getScene().getWindow();
        loadScreen(stage, "orderlistscreen.fxml");
    }

    @FXML
    private void handleReturn(ActionEvent event) {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        loadScreen(stage, "shopassistantscreen.fxml");
    }
    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "loginscreen.fxml");
    }

    @FXML
    void handleOrderConfirm(ActionEvent event) {
        String customerName = firstnameTextField.getText();
        String customerSurName = lastnameTextField.getText();
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
            DB.insertSQL("INSERT INTO tblCustomer(fldCustomerID,fldName,fldSurname,fldPhone) VALUES ('"+customerID+"','"+customerName+"','"+customerSurName+"','"+customerPhone+"');");

            /**
             * LOOP FOR ADDING ITEMS FROM LIST INTO DATABASE
             */
                for (int i=0;i<itemList.size();i++){
                    String clothType = itemList.get(i);
                    int itemQTY = itemQuantity.get(i);
                    DB.insertSQL("INSERT INTO tlbOrderDescription(fldOrderDesID,fldOrderID,fldItemQuantity,fldItemType,fldPrice,fldWeight) VALUES ('"+orderDescID+"','"+orderID+"','"+itemQTY+"','"+clothType+"');");
                }
                // INSERTING PRE-DEFINED STATUS INTO DATABASE
                DB.insertSQL("INSERT INTO tblOrderStatus(fldOrderStatusID,fldOrderStatus) VALUES ('"+statusID+"','"+status+"');");
                itemQuantity.clear();
                itemList.clear();
        }
        /**
         * FOR PRICE
         * DB.selectSQL("SELECT fldPrice FROM tblItem WHERE fldItemType = 'orderType')
         */
    }
    @FXML
    void refreshItems(ActionEvent event){
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
            ObservableList<OrderTable> data = tv.getItems();
            data.add(new OrderTable(orderType,quantityOfCloth));
            clothTypeOL.add(new OrderTable(orderType,quantityOfCloth));
            OrderTable.addClothQTYList(quantityOfCloth);
            OrderTable.addClothTypeList(orderType);


        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("PICK CLOTH TYPE AND INSERT QUANTITY");
            alert.show();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemListView.setItems(laundryType);
    }
}