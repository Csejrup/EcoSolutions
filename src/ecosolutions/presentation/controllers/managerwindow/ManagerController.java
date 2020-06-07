package ecosolutions.presentation.controllers.managerwindow;

import ecosolutions.Domain.EmployeeService;
import ecosolutions.Domain.OrderService;
import ecosolutions.alert.AlertCreator;
import ecosolutions.presentation.controllers.AbstractController;
import com.jfoenix.controls.*;
import ecosolutions.presentation.models.Employee;
import ecosolutions.presentation.models.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.net.URL;

/**
 *
 */
public class ManagerController extends AbstractController implements Initializable {

    @FXML private StackPane rootPane;
    @FXML private BorderPane rootborderPane;
    @FXML private JFXButton btnrefresh, btnworkforce, btnorders, btnstatistics, btnsettings;
    @FXML private StackPane ordercontainer, employcontainer;
    @FXML private Tab overviewtab, laundryotab;

    @FXML private TableView<Order> tableviewm;
    @FXML private TableColumn<Order, String> orderCol;
    @FXML private TableColumn<Order, String> statusCol;
    @FXML private TableColumn<Order, String> active_byCol;

    private PieChart orderchart, employchart;
    ObservableList list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      initGraphs();
     // initCol();
     // loadData();
    }

    private void initCol(){
        orderCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("orderstatus"));
        active_byCol.setCellValueFactory(new PropertyValueFactory<>("employeeid"));
    }
    private void initGraphs(){
        orderchart = new PieChart(OrderService.getGraph1());
        ordercontainer.getChildren().add(orderchart);
        employchart = new PieChart(OrderService.getGraph2());
        employcontainer.getChildren().add(employchart);
    }
    private void loadData(){
        List<Order> listoforders = OrderService.getOrders();
        list.clear();
        tableviewm.getItems().addAll(listoforders);
    }
    @FXML
    void handleRefresh(ActionEvent event) {
        tableviewm.getItems().clear();
        //loadData();
    }


    @FXML
    void loadOrderList(ActionEvent event) {
        loadpopup("FullOrderListView.fxml");
    }

    @FXML
    void loadSettings(ActionEvent event) {
        Stage stage = (Stage) btnsettings.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }
    @FXML
    void loadStatisticsList(ActionEvent event) {
        JFXButton button = new JFXButton("Okay");
        AlertCreator.showAlertDialog(rootPane,rootborderPane, Arrays.asList(button),"Test",null);
    }
    @FXML
    void loadWorkForceList(ActionEvent event) {
        loadpopup("EmployeesView.fxml");
    }
}
