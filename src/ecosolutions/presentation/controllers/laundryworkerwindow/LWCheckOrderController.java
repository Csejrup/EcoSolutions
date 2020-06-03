package ecosolutions.presentation.controllers.laundryworkerwindow;

import ecosolutions.Domain.OrderService;
import ecosolutions.presentation.controllers.AbstractController;
import ecosolutions.presentation.models.Order;
import com.jfoenix.controls.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LWCheckOrderController extends AbstractController implements Initializable {
    ObservableList list = FXCollections.observableArrayList();

    @FXML private JFXTextField orderNoTextField;
    @FXML private JFXButton btnCleaning, btncheckOrder, btnLogOut;
    @FXML private TableView<Order> tableview;
    @FXML private TableColumn<Order, String> launditemCol;
    @FXML private TableColumn<Order, Integer> qtyCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //loadData();
        initCol();
    }

    private void initCol(){
        launditemCol.setCellValueFactory(new PropertyValueFactory<>("laundryitems"));
       qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }
    private void loadData(){
        list.clear();
        List<Order> itemlist = OrderService.getOrders();
        tableview.getItems().addAll(itemlist);
    }

    @FXML
    void loadOrder(ActionEvent event) {

    }
    @FXML
    private void handleCleaning(ActionEvent event) {
        Stage stage = (Stage) btnCleaning.getScene().getWindow();
        loadScreen(stage, "LaundryWorkerView.fxml");
    }
    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }
}
