package ecosolutions.presentation.controllers.managerwindow;

import ecosolutions.Domain.OrderService;
import ecosolutions.presentation.models.Order;
import java.net.URL;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import java.util.*;


public class OrderFullListController implements Initializable {
    ObservableList<Order> list = FXCollections.observableArrayList();

    @FXML private TableView<Order> tableView;
    @FXML private TableColumn<Order, String> ordernoCol;
    @FXML private TableColumn<Order, String> statusCol;
    @FXML private TableColumn<Order, String> qtyCol;
    @FXML private TableColumn<Order, String> customerCol;
    @FXML private TableColumn<Order, String> deliverypointCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }
    private void initCol(){
        ordernoCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("orderstatus"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        deliverypointCol.setCellValueFactory(new PropertyValueFactory<>("deliverypointname"));
    }
    private void loadData(){
        list.clear();
        tableView.getItems().addAll(OrderService.getOrders());
    }

}
