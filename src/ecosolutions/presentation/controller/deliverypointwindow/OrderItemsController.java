package ecosolutions.presentation.controller.deliverypointwindow;

import com.jfoenix.controls.JFXButton;
import ecosolutions.presentation.controller.AbstractController;
import ecosolutions.application.OrderTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * CLASS IS REPRESENTATION OF 'BUSKET'.
 * Controller responsible for handling the view OrderListView.fxml
 */
public class OrderItemsController extends AbstractController implements Initializable {

    //TABLE VIEW DETAILS
    @FXML private TableView<OrderTableView> tv;
    @FXML private TableColumn<OrderTableView,String> tcClothType;
    @FXML private TableColumn<OrderTableView, Integer> tcClothQTY;
    @FXML private JFXButton btnReturn, btnRemove;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tcClothQTY.setCellValueFactory(new PropertyValueFactory<>("clothQty"));
        tcClothType.setCellValueFactory(new PropertyValueFactory<>("clothType"));
        tv.setItems(CreateOrderController.getItems());

    }
    /**
     * METHOD FOR REMOVING ITEM FROM BASKET.
     */
    @FXML
    void handleRemove(ActionEvent event) {
        OrderTableView item = tv.getSelectionModel().getSelectedItem();
        tv.getItems().remove(item);
        CreateOrderController.totalAmount -= (item.getItemPrice()* item.getClothQty());
    }

    /**
     * METHOD FOR RETURNING TO MAIN WINDOW.
     */
    @FXML
    private void handleReturn(ActionEvent event) {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        loadScreen(stage, "DeliveryPointView.fxml");
    }

}
