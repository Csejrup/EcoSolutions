package ecosolutions.presentation.controller.deliverypointwindow;
import com.jfoenix.controls.*;
import ecosolutions.service.CustomerService;
import ecosolutions.service.DeliveryPointService;
import ecosolutions.service.LaundryItemService;
import ecosolutions.service.OrderService;
import ecosolutions.presentation.controller.AbstractController;
import ecosolutions.presentation.controller.LoginController;
import ecosolutions.Domain.Order;
import ecosolutions.application.OrderTableView;

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

            int accountID = LoginController.accountID;
            int deliveryPointID = DeliveryPointService.getDpID(accountID);
            String date = sdp.format(now);
                if (items.size() == 0||customerID == 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("ORDER IS EMPTY, PICK CLOTH TYPE AND QUANTITY.");
                    alert.show();
                }else if (CustomerService.isExist(customerID)) {
                    Order newOrder = new Order(customerID, orderStatusID, date, deliveryPointID, items, price);
                    OrderService.addOrder(newOrder);
                    OrderService.addOrderDetails(newOrder);
                    items.clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("ORDER HAS BEN CREATED");
                    alert.show();
                    int orderID = OrderService.getLastOrderID();
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
            if(orderType!=null&&orderQTY!=0){
            items.add(new OrderTableView(orderType,orderQTY,orderID,itemPrice));

            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("PICK CLOTHTYPE AND QUANTITY");
                alert.show();
            }
            System.out.println(orderID);
            totalAmount+=(itemPrice*orderQTY);
            qtyTextField.clear();
            dueTextField.setText(String.valueOf(totalAmount));
        }catch(NumberFormatException e){
           // e.printStackTrace();
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