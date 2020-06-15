package ecosolutions.presentation.controller.laundryworkerwindow;
import ecosolutions.service.OrderService;
import ecosolutions.alert.AlertCreator;
import ecosolutions.presentation.controller.AbstractController;
import ecosolutions.Domain.Order;

import com.jfoenix.controls.*;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.collections.*;
import javafx.scene.layout.*;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;

/**
 * Controller responsible for handling the view LaundryWorkerView.fxml
 * Responsible for updating an order object in the database through orderService
 * Class is responsible for handle Laundry worker GUI after the cleaning process is finish
 */
public class LaundryWorkerController extends AbstractController implements Initializable {
    //---------------------FXML-------------------------//
    @FXML private StackPane rootpane;
    @FXML private BorderPane rootborderpane;
    @FXML private JFXButton btnLogOut, btnNewOrder;
    @FXML private TableView<Order> tableview;
    @FXML private TableColumn<Order, String> ord_noCol;
    @FXML private TableColumn<Order, String> c_statCol;
    //---------------------FXML-------------------------//
    private final ObservableList list = FXCollections.observableArrayList();
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
    //Update order status based on selected orderID in tableview
    private void id(){
        var order = new Order();
        order.setOrderID(tableview.getSelectionModel().getSelectedItem().getOrderID());
        System.out.println(order.getOrderID());
        order.setOrderstatus("Complete");
        OrderService.updateOrderr(order);
    }
    private void refresh(){
        tableview.getItems().clear();
        loadData();
    }
    //Button changes status of selected order to "Complete"
    @FXML
    void handleStatus(ActionEvent event) {
        String stat;
        try {
            stat = tableview.getSelectionModel().getSelectedItem().getOrderstatus();
            if(!stat.isEmpty() && !stat.equals("In_Transit")){
                id();
                System.out.println(stat);
                refresh();
            }else{
                JFXButton button = new JFXButton("Okay");

                AlertCreator.showAlertDialog(rootpane,rootborderpane, Collections.singletonList(button),"Check Information","Cannot Select a 'In_Transit Order'"+
                        "\n And please select an 'Cleaning' Order");
            }
        } catch (Exception e) {
            JFXButton button = new JFXButton("Okay");
            AlertCreator.showAlertDialog(rootpane,rootborderpane, Collections.singletonList(button),"Check Information","Please Select an Order from the list");
        }
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
}
