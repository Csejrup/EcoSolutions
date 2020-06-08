package ecosolutions.presentation.controllers.laundryworkerwindow;
import ecosolutions.Domain.OrderService;
import ecosolutions.alert.AlertCreator;
import ecosolutions.presentation.controllers.AbstractController;
import ecosolutions.presentation.models.Order;

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
 *
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

    ObservableList list = FXCollections.observableArrayList();
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
    private void id(String status){
        var order = new Order();
        var orderservice = new OrderService();
        order.setOrderID(tableview.getSelectionModel().getSelectedItem().getOrderID());
        System.out.println(order.getOrderID());
        order.setOrderstatus(status);
        orderservice.updateOrderr(order);
    }
    private void refresh(){
        tableview.getItems().clear();
        loadData();
    }
    @FXML
    void handleStatus(ActionEvent event) {
        String stat;
        try {
            stat = tableview.getSelectionModel().getSelectedItem().getOrderstatus();
            if(!stat.isEmpty() && !stat.equals("In_Transit")){
                id("Complete");
                System.out.println(stat);
                refresh();
            }else{
                JFXButton button = new JFXButton("Okay");

                AlertCreator.showAlertDialog(rootpane,rootborderpane, Arrays.asList(button),"Check Information","Cannot Select a 'In_Transit Order'"+
                        "\n And please select an 'Cleaning' Order");
            }
        } catch (Exception e) {
            JFXButton button = new JFXButton("Okay");
            AlertCreator.showAlertDialog(rootpane,rootborderpane, Arrays.asList(button),"Check Information","Please Select an Order from the list");
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
