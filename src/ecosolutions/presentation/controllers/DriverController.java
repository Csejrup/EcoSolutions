package ecosolutions.presentation.controllers;

import com.jfoenix.controls.*;
import ecosolutions.Domain.OrderService;
import ecosolutions.persistence.DAO.OrderDao;
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
import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class DriverController extends AbstractController implements Initializable {
    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXCheckBox checkBoxUp;

    @FXML
    private JFXCheckBox checkBoxDeliv;

    @FXML
    private JFXButton driverLogOut;

    @FXML
    private TableView<Order> tableView;

    @FXML
    private TableColumn<Order, String> ordernoCol;

    @FXML
    private TableColumn<Order, String> ordstatCol;

    @FXML
    private TableColumn<Order, String> locaCol;


    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
        tableView.getSelectionModel().getSelectedItem();
    }
    private void initCol(){
        ordernoCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        ordstatCol.setCellValueFactory(new PropertyValueFactory<>("orderstatus"));
        locaCol.setCellValueFactory(new PropertyValueFactory<>("deliverypointname"));
    }
    private void loadData(){
        list.clear();
        tableView.getItems().addAll(OrderService.getOrders());
    }

    public void changeStatus(ActionEvent event){
        change("Under_Way");
    }

    public void statusDelivered(ActionEvent event) { change("Delivered"); }

    private void change(String status){
         Order order = new Order();
         OrderDao dao = new OrderDao();

         order.setOrderID(tableView.getSelectionModel().getSelectedItem().getOrderID());
         order.setOrderstatus(status);
         System.out.println(order);
         dao.update(order);
         refresh();
    }
    private void refresh(){
        tableView.getItems().clear();
        loadData();

    }
}
