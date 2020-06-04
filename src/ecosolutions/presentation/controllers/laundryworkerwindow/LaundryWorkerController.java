package ecosolutions.presentation.controllers.laundryworkerwindow;

import com.jfoenix.controls.*;
import ecosolutions.Domain.OrderService;
import ecosolutions.presentation.controllers.AbstractController;
import ecosolutions.presentation.models.Order;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.util.*;

public class LaundryWorkerController extends AbstractController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();

    @FXML private JFXButton btnLogOut, btncheckOrder, btnNewOrder, btnCleaning, btnPrint;
    @FXML private JFXTextField orderNoTextField;
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
    public void handlePrintLabel(ActionEvent event) {
        Order order =  tableview.getSelectionModel().getSelectedItem();
        int orderID = order.getOrderID();
        List<Integer> itemIDlist = OrderService.getItemIDList(orderID);
        List<Integer> itemQuantity = OrderService.getItemQuantityByID(itemIDlist,orderID);
        List<String> itemTypes = OrderService.getItemTypeByID(itemIDlist);
        for (int i = 0;i<itemIDlist.size();i++) {
            System.out.println("ORDER ID: " + orderID + "\nCLOTH TYPE: " + itemTypes.get(i)+"\nQUANTITY: "+itemQuantity.get(i));
        }
    }
}
