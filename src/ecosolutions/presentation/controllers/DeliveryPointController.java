package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DeliveryPointController extends AbstractController{


    @FXML private JFXButton btnLogOut, btnConfirm, btnRemove, btnReturn, btnEdit, btnAdd;
    @FXML private JFXListView<?> itemListView;
    @FXML private JFXListView<?> orderListView;
    @FXML private JFXTextField dueTextField, firstnameTextField, phoneNoTextField, lastnameTextField;
    @FXML private TextField qtyTextField;




    @FXML
    void handleAddItem(ActionEvent event) {

    }
    @FXML
    void handleRemove(ActionEvent event) {

    }
    @FXML
    void handleOrderConfirm(ActionEvent event) {

    }




    @FXML
    private void handleEditOrder(ActionEvent event) {
        Stage stage = (Stage) btnEdit.getScene().getWindow();
        loadScreen(stage, "orderlistscreen.fxml");
    }

    @FXML
    private void handleReturn(ActionEvent event) {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        loadScreen(stage, "deliverypointscreen.fxml");
    }
    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "loginscreen.fxml");
    }


}