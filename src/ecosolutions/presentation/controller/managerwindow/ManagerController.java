package ecosolutions.presentation.controller.managerwindow;

import ecosolutions.service.OrderService;
import ecosolutions.presentation.controller.AbstractController;
import ecosolutions.Domain.Order;
import com.jfoenix.controls.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import java.util.*;

import javafx.stage.Stage;
import java.net.URL;
/**
 * Controller responsible for handling ManagerView.fxml
 * Class functions as a menu for statistics and overviews for orders and employees
 *
 */
public class ManagerController extends AbstractController implements Initializable {

    @FXML private JFXButton btnrefresh, btnstatistics, btnsettings;
    @FXML private StackPane ordercontainer, employcontainer;
    @FXML private TableView<Order> tableviewm;
    @FXML private TableColumn<Order, String> orderCol;
    @FXML private TableColumn<Order, String> statusCol;
    @FXML private TableColumn<Order, String> active_byCol;

    private BarChart orderbarchart;
    //Barchart
    CategoryAxis xAxis = new CategoryAxis();
    //List for Tableview
    private final ObservableList list = FXCollections.observableArrayList();
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
        //Chart declarations
        PieChart orderchart = new PieChart(OrderService.getGraph1());
        ordercontainer.getChildren().add(orderchart);
        PieChart employchart = new PieChart(OrderService.getGraph2());
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

    /**
     * Method is Not Complete. However the Method could be a future implementation
     * For when the Cleaning Service Business grows bigger and needs additional
     * Statistics
     */
    @FXML
    void loadStatisticsList() {
        //New Objects
        StackPane layout = new StackPane();
        JFXButton btnreturn = new JFXButton("Return");
        JFXTextField txtfield = new JFXTextField("This scene is empty on purpose, please click return");
        //Placing the Button and Adding Style
        btnreturn.setTranslateX(0);
        btnreturn.setTranslateY(230);
        layout.getStylesheets().add("resources/StyleSheets/color-style.css");
        btnreturn.getStyleClass().add("button");
        //
        //ADDS The Stage to loadnewstage in AbstractController
        //orderbarchart
        layout.getChildren().addAll(btnreturn,txtfield);
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
