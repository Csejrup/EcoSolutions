package ecosolutions.presentation.controllers;

import com.jfoenix.controls.*;
import ecosolutions.Domain.CustomerService;
import ecosolutions.Domain.DeliveryPointService;
import ecosolutions.Domain.OrderService;

import ecosolutions.presentation.models.Customer;
import ecosolutions.presentation.models.Order;
import ecosolutions.presentation.models.OrderTableView;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class DeliveryPointController extends AbstractController implements Initializable {

    @FXML private JFXListView<String> itemListView = new JFXListView<>();
    @FXML private JFXButton btnConfirm, btnRemove, btnreturn, btnEdit, btnAdd;
    @FXML private JFXTextField dueTextField, firstnameTextField, phoneNoTextField, lastnameTextField;
    @FXML private TextField qtyTextField, cusidTextField;

    //ADD BASIC CLOTH TYPES
    public static ObservableList<OrderTableView> items = FXCollections.observableArrayList();

    //GLOBAL VARIABLES FOR TABLEVIEW
    public static String orderType;
    public static int orderQTY, orderID;
    public static float itemPrice;
    public static float totalAmount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemListView.setItems(DeliveryPointService.getItemTypes());
        dueTextField.setText(String.valueOf(totalAmount));
    }

    @FXML
    void handleOrderConfirm(ActionEvent event) {
        String customerName = firstnameTextField.getText();
        String customerSurName = lastnameTextField.getText();
        String customerPhoneNr = phoneNoTextField.getText();

        int orderStatusID = 1;
        CustomerService.addCustomer(new Customer(customerName,customerSurName,customerPhoneNr));
        int customerID = CustomerService.getCustomerID();
        int orderID = OrderService.getLastOrderID();
        java.util.Date now = new Date();
        SimpleDateFormat sdp = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdp.format(now);
        float price = 12.3F;
        float weigth = 10F;
        //TODO PRICE AND WEIGHT HERE - DONE
        //TODO ERROR WHILE INSERTING CUSTOMERID CAUSED BY INSERTING DATA INTO FK FIELD
        Order newOrder = new Order(customerID,orderStatusID,date, items,price,weigth);

            if(customerName.isEmpty()||customerSurName.isEmpty()||customerPhoneNr.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("INSERT CUSTOMER DETAILS");
                alert.show();
            }else if(items.size()==0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("ORDER IS EMPTY, PICK CLOTH TYPE AND QUANTITY.");
                alert.show();
            }else{
                OrderService.addOrder(newOrder);
                OrderService.addOrderDetails(newOrder);
                items.clear();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("ORDER HAS BEN CREATED");
                alert.show();
                System.out.println(orderID);
            }
        }
    /**
     * ADDING ITEM TO THE 'BASKET'
     * @param event
     */

    @FXML
    void addToBasket(ActionEvent event) {

        try{
            orderType = itemListView.getSelectionModel().getSelectedItem();
            orderQTY = Integer.parseInt(qtyTextField.getText());
            orderID = DeliveryPointService.getID(orderType);
            itemPrice = DeliveryPointService.getPrice(orderType);
                    items.add(new OrderTableView(orderType,orderQTY,orderID,itemPrice));
            System.out.println(orderID);
            totalAmount+=(itemPrice*orderQTY);
            qtyTextField.clear();
            dueTextField.setText(String.valueOf(totalAmount));
        }catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("PICK CLOTH TYPE AND INSERT QUANTITY");
            alert.show();
        }
    }
    public static ObservableList<OrderTableView> getItems(){
       return items;
    }

    /**
     * Methods for Handling change of Scene
     */
    @FXML
    private void handleEditOrder(ActionEvent event) {
        Stage stage = (Stage) btnEdit.getScene().getWindow();
        btnEdit.getScene().getWindow().hide();
        loadScreen(stage, "OrderListView.fxml");
    }
    @FXML
    void handleReturn(ActionEvent event) {
        Stage stage = (Stage) btnreturn.getScene().getWindow();
        loadScreen(stage, "CustomerCreationView.fxml");
    }
}