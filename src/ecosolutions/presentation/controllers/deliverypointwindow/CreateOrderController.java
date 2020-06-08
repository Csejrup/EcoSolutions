package ecosolutions.presentation.controllers.deliverypointwindow;
import com.jfoenix.controls.*;
import ecosolutions.Domain.CustomerService;
import ecosolutions.Domain.LaundryItemService;
import ecosolutions.Domain.OrderService;
import ecosolutions.presentation.controllers.AbstractController;
import ecosolutions.presentation.controllers.LoginController;
import ecosolutions.presentation.models.Order;
import ecosolutions.presentation.models.OrderTableView;

import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.text.SimpleDateFormat;

/**
 *
 */
public class CreateOrderController extends AbstractController implements Initializable {
    @FXML private JFXListView<String> itemListView = new JFXListView<>();
    @FXML private JFXButton  btnreturn, btnEdit;
    @FXML private JFXTextField dueTextField;
    @FXML private TextField qtyTextField, cusidTextField;
    //ADD BASIC CLOTH TYPES
    public static ObservableList<OrderTableView> items = FXCollections.observableArrayList();
    //GLOBAL VARIABLES FOR TABLEVIEW
    public static String orderType;
    public static int orderQTY, orderID;
    public static float itemPrice;
    public static float totalAmount = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemListView.setItems(LaundryItemService.getItemTypes());
        dueTextField.setText(String.valueOf(totalAmount));
    }
    @FXML
    void handleOrderConfirm(ActionEvent event) {
        java.util.Date now = new Date();
        SimpleDateFormat sdp = new SimpleDateFormat("yyyy/MM/dd");
        int orderStatusID = 1;
        float price = 12.3F;
        try {
            int customerID = Integer.parseInt(cusidTextField.getText());
            int orderID = OrderService.getLastOrderID();
            int accountID = LoginController.accountID;
            String date = sdp.format(now);
                if (items.size() == 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("ORDER IS EMPTY, PICK CLOTH TYPE AND QUANTITY.");
                    alert.show();
                }else if (CustomerService.isExist(customerID)) {
                    Order newOrder = new Order(customerID, orderStatusID, date, accountID, items, price);
                    OrderService.addOrder(newOrder);
                    OrderService.addOrderDetails(newOrder);
                    items.clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("ORDER HAS BEN CREATED");
                    alert.show();
                    System.out.println(orderID);
                }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("INSERT ORDER DETAILS");
            alert.setContentText("ORDER CAN NOT BE CREATED, INSERT ORDER DETAILS");
            alert.show();
        }
    }
    /**
     * ADDING ITEM TO THE 'BASKET'
     * @param event
     */
    @FXML
    void addToBasket(ActionEvent event) {
        try{
            orderType = itemListView.getSelectionModel().getSelectedItem();
            orderQTY = Integer.parseInt(qtyTextField.getText());
            orderID = LaundryItemService.getID(orderType);
            itemPrice = LaundryItemService.getPrice(orderType);
            items.add(new OrderTableView(orderType,orderQTY,orderID,itemPrice));
            System.out.println(orderID);
            totalAmount+=(itemPrice*orderQTY);
            qtyTextField.clear();
            dueTextField.setText(String.valueOf(totalAmount));
        }catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("PICK CLOTH TYPE AND INSERT QUANTITY");
            alert.show();
        }
    }
    public static ObservableList<OrderTableView> getItems(){
       return items;
    }
    /**
     * Methods for Handling change of Scene
     */
    @FXML
    private void handleEditOrder(ActionEvent event) {
        Stage stage = (Stage) btnEdit.getScene().getWindow();
        btnEdit.getScene().getWindow().hide();
        loadScreen(stage, "OrderListView.fxml");
    }
    @FXML
    void handleReturn(ActionEvent event) {
        Stage stage = (Stage) btnreturn.getScene().getWindow();
        loadScreen(stage, "deliverypointmain.fxml");
    }
}