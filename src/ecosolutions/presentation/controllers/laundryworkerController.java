package ecosolutions.presentation.controllers;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;

public class laundryworkerController extends AbstractController{
    @FXML
    private JFXButton btnLogOut;
    @FXML
    private JFXButton btnNewOrder;

    @FXML
    private JFXTextField orderNoTextField;

    @FXML
    private JFXTextField machineNoTextField;

    @FXML
    private JFXButton btnCleaning;

    @FXML
    private JFXTreeTableView<?> tableItem;

    @FXML
    private TreeTableColumn<?, ?> itemCol;

    @FXML
    private TreeTableColumn<?, ?> qtyCol;

    @FXML
    private JFXTreeTableView<?> tableViewOrders;

    @FXML
    private TreeTableColumn<?, ?> ordernoCol;

    @FXML
    private TreeTableColumn<?, ?> machinenoCol;

    @FXML
    private TreeTableColumn<?, ?> statusCol;

    @FXML
    void handleNewOrder(ActionEvent event) {
        Stage stage = (Stage) btnNewOrder.getScene().getWindow();
        loadScreen(stage, "cleaningscreen.fxml");
    }
    @FXML
    void handleCleaning(ActionEvent event) {
        Stage stage = (Stage) btnCleaning.getScene().getWindow();
        loadScreen(stage, "laundryworkerscreen.fxml");
    }
    @FXML
    void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "loginscreen.fxml");
    }
}
