package ecosolutions.presentation.controllers;

import com.jfoenix.controls.*;
import ecosolutions.Domain.OrderService;
import ecosolutions.persistence.DAO.*;
import ecosolutions.presentation.models.Order;
import ecosolutions.presentation.models.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.net.http.WebSocket;
import java.sql.SQLOutput;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static javax.swing.JOptionPane.showMessageDialog;

public class DriverController extends AbstractController implements Initializable {

    @FXML private JFXButton btnLogOut;
    @FXML private JFXCheckBox checkBoxUp, checkBoxDeliv, checkboxTransit;
    @FXML private TableView<Order> tableView;
    @FXML private TableColumn<Order, String> ordernoCol;
    @FXML private TableColumn<Order, String> ordstatCol;
    @FXML private TableColumn<Order, String> locaCol;

    ObservableList list = FXCollections.observableArrayList();

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
    public void statusUnderWay(ActionEvent event){
        checkBoxDeliv.setSelected(false);
        checkboxTransit.setSelected(false);
        change("Under_Way");
    }
    public void statusDelivered(ActionEvent event) {
        checkBoxUp.setSelected(false);
        checkboxTransit.setSelected(false);
        change("Delivered");
    }
    @FXML
    void statusTransit(ActionEvent event) {
        checkBoxDeliv.setSelected(false);
        checkBoxUp.setSelected(false);
        change("In_Transit");
    }

    private void change(String status){
         Order order = new Order();
         OrderService orderservice = new OrderService();

         if(tableView.getSelectionModel().isEmpty()){
             showMessageDialog(null,"Select order");
             checkBoxDeliv.setSelected(false);
             checkBoxUp.setSelected(false);
             checkboxTransit.setSelected(false);
         }else{
             order.setOrderID(tableView.getSelectionModel().getSelectedItem().getOrderID());
             order.setOrderstatus(status);
             orderservice.updateOrderr(order);
             System.out.println(status);
             refresh();
         }
    }
    private void refresh(){
        tableView.getItems().clear();
        loadData();
    }
    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }

}
