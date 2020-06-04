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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LWCheckOrderController extends AbstractController implements Initializable {
    ObservableList list = FXCollections.observableArrayList();
    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    @FXML private JFXTextField orderNoTextField;
    @FXML private JFXButton btnCleaning, btncheckOrder, btnLogOut;
    @FXML private TableView<Order> tableview;
    @FXML private TableColumn<Order, String> launditemCol;
    @FXML private TableColumn<Order, Integer> qtyCol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  loadData();
        initCol();
    }

    private void initCol(){
        launditemCol.setCellValueFactory(new PropertyValueFactory<>("clothtype"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    private void loadData(){

    }

    @FXML
    void loadOrder(ActionEvent event) {
        String order_no = orderNoTextField.getText();
        try {
            tableview.getItems().clear();
            List<Order> itemlist = OrderService.checkorder(Integer.parseInt(order_no));
            itemlist.forEach(System.out::println);
            tableview.getItems().addAll(itemlist);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCleaning(ActionEvent event) {
        var orderservice = new OrderService();
        String order_no = orderNoTextField.getText();
        int no = Integer.parseInt(order_no);
        orderservice.updateOrder(no,"Cleaning");
        Stage stage = (Stage) btnCleaning.getScene().getWindow();
        loadScreen(stage, "LaundryWorkerView.fxml");
    }
    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }
}
