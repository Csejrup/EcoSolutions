package ecosolutions.presentation.controllers;

import com.jfoenix.controls.*;
import ecosolutions.Domain.OrderService;
import ecosolutions.presentation.models.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
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

}
