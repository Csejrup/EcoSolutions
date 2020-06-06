package ecosolutions.presentation.controllers.laundryworkerwindow;

import com.jfoenix.controls.*;
import ecosolutions.Domain.OrderService;
import ecosolutions.alert.AlertCreator;
import ecosolutions.presentation.controllers.AbstractController;
import ecosolutions.presentation.models.Order;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.*;

public class LaundryWorkerController extends AbstractController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane root;

    @FXML
    private StackPane rootpane;

    @FXML
    private BorderPane rootborderpane;

    @FXML private JFXButton btnLogOut, btnNewOrder;
    @FXML private TableView<Order> tableview;
    @FXML private TableColumn<Order, String> ord_noCol;
    @FXML private TableColumn<Order, String> c_statCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }
    private void initCol(){
        ord_noCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        c_statCol.setCellValueFactory(new PropertyValueFactory<>("orderstatus"));
    }
    private void loadData(){
        list.clear();
        List<Order> listoforders = OrderService.getLWOrders();
        tableview.getItems().addAll(listoforders);
    }
    @FXML
    private void handleNewOrder(ActionEvent event) {
        Stage stage = (Stage) btnNewOrder.getScene().getWindow();
        loadScreen(stage, "CleaningView.fxml");
    }
    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }
    @FXML
    void handleStatus(ActionEvent event) {
        if(!tableview.getSelectionModel().isEmpty()){
            id("Complete");
            refresh();
        }else{
            JFXButton button = new JFXButton("Okay");
            AlertCreator.showAlertDialog(rootpane,rootborderpane, Arrays.asList(button),"Check Information","Select an Order and then Click Update Status");
        }
    }

    private void id(String status){
        var order = new Order();
        var orderservice = new OrderService();
        order.setOrderID(tableview.getSelectionModel().getSelectedItem().getOrderID());
        order.setOrderstatus(status);

        orderservice.updateOrderr(order);
    }
    private void refresh(){
        tableview.getItems().clear();
        loadData();
    }


}
