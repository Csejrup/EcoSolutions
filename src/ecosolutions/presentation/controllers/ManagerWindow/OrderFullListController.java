package ecosolutions.presentation.controllers.ManagerWindow;

import ecosolutions.Domain.OrderService;
import ecosolutions.presentation.models.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderFullListController implements Initializable {
    ObservableList<Order> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Order> tableView;

    @FXML
    private TableColumn<Order, String> ordernoCol;

    @FXML
    private TableColumn<Order, String> statusCol;

    @FXML
    private TableColumn<Order, String> qtyCol;

    @FXML
    private TableColumn<Order, String> customerCol;

    @FXML
    private TableColumn<Order, String> deliverypointCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }
    private Stage getStage(){
        return (Stage) tableView.getScene().getWindow();
    }
    private void initCol(){
        ordernoCol.setCellValueFactory(new PropertyValueFactory<Order, String>("orderID"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Order, String>("orderstatus"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<Order, String>("qty"));
        customerCol.setCellValueFactory(new PropertyValueFactory<Order, String>("customerID"));
        deliverypointCol.setCellValueFactory(new PropertyValueFactory<Order, String>("deliverypointname"));
    }
    private void loadData(){
        list.clear();
        tableView.getItems().addAll(OrderService.getOrders());
    }

}
