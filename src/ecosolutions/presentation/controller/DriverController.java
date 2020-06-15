package ecosolutions.presentation.controller;

import com.jfoenix.controls.*;
import ecosolutions.service.OrderService;
import ecosolutions.persistence.DAO.*;
import ecosolutions.Domain.Customer;
import ecosolutions.Domain.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Controller responsible for handling the view DriverView.fxml
 * Responsible for updating an order through OrderService
 * Responsible for getting customer objects to send out an notification
 */
public class DriverController extends AbstractController implements Initializable {

    @FXML private JFXButton btnLogOut;
    @FXML private JFXCheckBox checkBoxUp, checkBoxDeliv, checkboxTransit;
    @FXML private TableView<Order> tableView;
    @FXML private TableColumn<Order, String> ordernoCol;
    @FXML private TableColumn<Order, String> ordstatCol;
    @FXML private TableColumn<Order, String> locaCol;

    final ObservableList list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }

    private void initCol(){
        ordernoCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        ordstatCol.setCellValueFactory(new PropertyValueFactory<>("orderstatus"));
        locaCol.setCellValueFactory(new PropertyValueFactory<>("deliverypointname"));
    }
    private void loadData(){
        list.clear();
        List<Order> listoforders = OrderService.getDriverOrders();
        tableView.getItems().addAll(listoforders);
    }
    public void statusUnderWay(){
        checkBoxDeliv.setSelected(false);
        checkboxTransit.setSelected(false);
        change("Under_Way");
    }
    public void statusDelivered() {
        checkBoxUp.setSelected(false);
        checkboxTransit.setSelected(false);
        change("Delivered");
        sendMessage("Delievered");
    }
    @FXML
    void statusTransit(ActionEvent event) {
        checkBoxDeliv.setSelected(false);
        checkBoxUp.setSelected(false);
        change("In_Transit");
    }
    //Change status of order in DB and in talbeview
    private void change(String status){
         Order order = new Order();
         if(tableView.getSelectionModel().isEmpty()){
             showMessageDialog(null,"Select order");
             checkBoxDeliv.setSelected(false);
             checkBoxUp.setSelected(false);
             checkboxTransit.setSelected(false);
         }else{
             order.setOrderID(tableView.getSelectionModel().getSelectedItem().getOrderID());
             order.setOrderstatus(status);

             sendMassage(status);
             orderservice.updateOrderr(order);


             System.out.println(status);
             refresh();
         }
    }
    //Clear the table view and gets data from DB again
    private void refresh(){
        tableView.getItems().clear();
        loadData();
    }
    //Send massage to customer when his order status is equal to "Delivered"
    private void sendMessage(String ready) {
        List<Customer> customers = new ArrayList<>();

        CustomerDao customerDao = new CustomerDao();

        if (ready.equals("Delivered")) {
            try {

                customers=customerDao.getCustomerFromOrder(tableView.getSelectionModel().getSelectedItem().getOrderID());


            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Calling customer ="+ customers+" Order is ready");

        }
    }

    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }

}
