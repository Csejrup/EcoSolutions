package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import ecosolutions.Domain.AccountService;
import ecosolutions.Domain.CustomerService;
import ecosolutions.Domain.DeliveryPointService;
import ecosolutions.Domain.OrderService;

import ecosolutions.persistence.DAO.AccountDao;
import ecosolutions.persistence.DAO.OrderDao;
import ecosolutions.persistence.DB;
import ecosolutions.presentation.models.Account;
import ecosolutions.presentation.models.Customer;
import ecosolutions.presentation.models.Order;
import ecosolutions.presentation.models.OrderTableView;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class DeliveryPointController extends AbstractController implements Initializable {

    //ADD BASIC CLOTH TYPES
    public static ObservableList<OrderTableView> items = FXCollections.observableArrayList();
    ObservableList<String> laundryType = FXCollections.observableArrayList("T-Shirt","Jacket","Carpet","Jeans","Suit","Blinds");
    @FXML
    private JFXButton btnLogOut;
//GLOBAL VARIABLES FOR TABLEVIEW
    public static String orderType;
    public static int orderQTY, orderID;

    @FXML
    private Pane pane1;

    @FXML
    private JFXListView<String> itemListView = new JFXListView<>();


    @FXML private JFXButton btnConfirm, btnRemove, btnReturn, btnEdit, btnAdd;



    @FXML private JFXTextField dueTextField, firstnameTextField, phoneNoTextField, lastnameTextField;
    @FXML private TextField qtyTextField;

    @FXML
    void handleAddItem(ActionEvent event) {

    }

    @FXML
    private void handleEditOrder(ActionEvent event) {
        Stage stage = (Stage) btnEdit.getScene().getWindow();
        loadScreen(stage, "OrderListView.fxml");
    }

    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }

    @FXML
    void handleOrderConfirm(ActionEvent event) throws SQLException {
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
        OrderService.addOrder(newOrder);
        OrderService.addOrderDetails(newOrder);
        items.clear();



        }
        /**
         * FOR PRICE
         * DB.selectSQL("SELECT fldPrice FROM tblItem WHERE fldItemType = 'orderType')
         */

    /**
     * ADDING ITEM TO THE 'BASKET'
     * @param event
     */

    @FXML
    void addToBasket(ActionEvent event) {

        try {
            orderType = itemListView.getSelectionModel().getSelectedItem();
            orderQTY = Integer.parseInt(qtyTextField.getText());
            orderID = DeliveryPointService.getID(orderType);
                    items.add(new OrderTableView(orderType,orderQTY,orderID));
            System.out.println(orderID);
        }
        catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("PICK CLOTH TYPE AND INSERT QUANTITY");
            alert.show();
        }
    }
    public static ObservableList<OrderTableView> getItems(){
       return items;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        itemListView.setItems(DeliveryPointService.getItemTypes());
    }


}