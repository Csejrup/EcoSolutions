package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import ecosolutions.presentation.models.OrderTableView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderItemsController extends AbstractController implements Initializable {

    //TABLE VIEW DETAILS
    @FXML private TableView<OrderTableView> tv;
    @FXML private TableColumn<OrderTableView,String> tcClothType;
    @FXML private TableColumn<OrderTableView, Integer> tcClothQTY;
    @FXML private JFXButton btnReturn, btnRemove;
    OrderTableView item;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tcClothQTY.setCellValueFactory(new PropertyValueFactory<OrderTableView,Integer>("clothQty"));
        tcClothType.setCellValueFactory(new PropertyValueFactory<OrderTableView,String>("clothType"));
        tv.setItems(DeliveryPointController.getItems());

    }
    @FXML
    void handleRemove(ActionEvent event) {
        item = tv.getSelectionModel().getSelectedItem();
        tv.getItems().remove(item);
        DeliveryPointController.totalAmount -= (item.getItemPrice()*item.getClothQty());
    }

    @FXML
    private void handleReturn(ActionEvent event) {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        loadScreen(stage, "DeliveryPointView.fxml");
    }

}
