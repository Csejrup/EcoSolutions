package ecosolutions.presentation.controllers;

import com.jfoenix.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;
import ecosolutions.Domain.Order.OrderList;
/**
 *
 */
public class managerController extends  AbstractController implements Initializable {

    @FXML private Text txtorderno, txtorderstat, txtempname, txtrole, txtstatus;

    @FXML private JFXButton btnrefresh, btnworkforce, btnorders, btnstatistics, btnsettings;

    @FXML private JFXTextField employID, orderID;

    @FXML private TableView<orderList> orderoverviewtable;
    @FXML private TableColumn<orderList, String> ordernoCol;
    @FXML private TableColumn<orderList, String> statusCol;
    @FXML private TableColumn<orderList, String> qtyCol;
    @FXML private TableColumn<orderList, String> customerCol;
    @FXML private TableColumn<orderList, String> deliverypointCol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // initCol();
    }

    private void initCol() {
        ordernoCol.setCellValueFactory(new PropertyValueFactory<>("order_no"));
        ordernoCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        ordernoCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        ordernoCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        ordernoCol.setCellValueFactory(new PropertyValueFactory<>("deliverypoint"));
    }

    class orderList{
        private SimpleStringProperty order_no;
        private SimpleStringProperty qty;
        private SimpleStringProperty status;
        private SimpleStringProperty customer;
        private SimpleStringProperty deliverypoint;

        orderList(String order_no, String qty, String status, String customer, String deliverypoint){
            this.order_no = new SimpleStringProperty(order_no);
            this.qty = new SimpleStringProperty(qty);
            this.status = new SimpleStringProperty(status);
            this.customer = new SimpleStringProperty(customer);
            this.deliverypoint = new SimpleStringProperty(deliverypoint);
        }
        public String getOrder_no() { return order_no.get(); }
        public String getQty() { return qty.get(); }
        public String getStatus() { return status.get(); }
        public String getCustomer() { return customer.get(); }
        public String getDeliverypoint() { return deliverypoint.get(); }
    }
    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void loadOrderList(ActionEvent event) {

    }

    @FXML
    void loadSettings(ActionEvent event) {

    }

    @FXML
    void loadStatisticsList(ActionEvent event) {

    }

    @FXML
    void loadWorkForceList(ActionEvent event) {

    }





}
