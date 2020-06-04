package ecosolutions.presentation.controllers.laundryworkerwindow;

import ecosolutions.Domain.OrderService;
import ecosolutions.alert.AlertCreator;
import ecosolutions.presentation.controllers.AbstractController;
import ecosolutions.presentation.models.Order;

import com.jfoenix.controls.*;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class LWCheckOrderController extends AbstractController implements Initializable {

    @FXML private AnchorPane rootAnchorPane;
    @FXML private BorderPane borderPane;
    @FXML private StackPane rootPane;
    @FXML private JFXTextField orderNoTextField;
    @FXML private JFXButton btnCleaning, btncheckOrder, btnLogOut, btnprintlabel;
    @FXML private TableView<Order> tableview;
    @FXML private TableColumn<Order, String> launditemCol;
    @FXML private TableColumn<Order, Integer> qtyCol;

    private String _ORDER_NO;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
    }
    private void initCol(){
        launditemCol.setCellValueFactory(new PropertyValueFactory<>("clothtype"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    @FXML
    void loadOrder(ActionEvent event) {
        try {
            tableview.getItems().clear();
            List<Order> itemlist = OrderService.checkorder(id());
            tableview.getItems().addAll(itemlist);
        } catch (NumberFormatException e) {
            JFXButton button = new JFXButton("Okay");
            AlertCreator.showAlertDialog(rootPane,borderPane, Arrays.asList(button),"Insert Order Number",null);
        }
    }
    @FXML
    private void handleCleaning(ActionEvent event) {
        var orderservice = new OrderService();
        try {
            if (id() != 0) {
                orderservice.updateOrder(id(),"Cleaning");
                Stage stage = (Stage) btnCleaning.getScene().getWindow();
                loadScreen(stage, "LaundryWorkerView.fxml");
            }else{
                JFXButton button = new JFXButton("Okay");
                AlertCreator.showAlertDialog(rootPane,borderPane, Arrays.asList(button),"Check Order First, Insert Order Number",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handlePrint(ActionEvent event) {
        try {
            if (id()!= 0 || tableview.getItems().isEmpty()) {
                List<Integer> itemIDlist = OrderService.getItemIDList(id());
                List<Integer> itemQuantity = OrderService.getItemQuantityByID(itemIDlist,id());
                List<String> itemTypes = OrderService.getItemTypeByID(itemIDlist);
                for (int i = 0;i<itemIDlist.size();i++) {
                    System.out.println("ORDER ID: " + id() + "\nCLOTH TYPE: " + itemTypes.get(i)+"\nQUANTITY: "+itemQuantity.get(i));
                }
            }
        } catch (Exception e) {
            JFXButton button = new JFXButton("Okay");
            AlertCreator.showAlertDialog(rootPane,borderPane, Arrays.asList(button),"Check Order First, Insert Order Number",null);
        }
    }
    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }

    private int id(){
        _ORDER_NO = orderNoTextField.getText();
        return Integer.parseInt(_ORDER_NO);
    }
}
