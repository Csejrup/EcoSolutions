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
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Parent;
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

    //Chart declarations
    private PieChart orderchart, employchart;
    private BarChart orderbarchart;
    //Barchart
    CategoryAxis xAxis = new CategoryAxis();
    //List for Tableview
    ObservableList list = FXCollections.observableArrayList();
    XYChart.Series<String, Order> series = new XYChart.Series<>();
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
      //  orderbarchart = new BarChart(OrderService.getGraph3());

    }
    private void loadData(){
        List<Order> listoforders = OrderService.getOrders();
        list.clear();
        tableviewm.getItems().addAll(listoforders);
    }
    @FXML
    void handleRefresh() {
        tableviewm.getItems().clear();
        //loadData();
    }


    @FXML
    void loadOrderList() {
        loadpopup("FullOrderListView.fxml");
    }

    @FXML
    void loadSettings() {
        Stage stage = (Stage) btnsettings.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }
    @FXML
    void loadStatisticsList() {
        //New Objects
        StackPane layout = new StackPane();
        JFXButton btnreturn = new JFXButton("Return");

        //Placing the Button and Adding Style
        btnreturn.setTranslateX(0);
        btnreturn.setTranslateY(230);
        layout.getStylesheets().add("resources/StyleSheets/color-style.css");
        btnreturn.getStyleClass().add("button");

        //


        //ADDS The Stage to loadnewstage in AbstractController

        //orderbarchart
        layout.getChildren().addAll(btnreturn);


        Stage secondaryStage = (Stage) btnstatistics.getScene().getWindow();
        loadnewstage(secondaryStage,layout);

        //btnreturn Action to return the main managerview (ManagerView)
        btnreturn.setOnAction(event -> {
            Stage stage = (Stage) btnreturn.getScene().getWindow();
            loadScreen(stage, "ManagerView.fxml");
        });
    }
    @FXML
    void loadWorkForceList() {
        loadpopup("EmployeesView.fxml");
    }
}
