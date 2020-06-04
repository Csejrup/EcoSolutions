package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RegisterCustomerController extends AbstractController {


    @FXML private JFXButton btnLogOut, btnConfirm;
    @FXML private JFXTextField firstnameTextField, phoneNoTextField, lastnameTextField;



    @FXML
    void handleCreateCustomer(ActionEvent event) {

        /**
         * TODO IMPLEMENT CUSTOMER CREATION CODE
         */

        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "DeliveryPointView.fxml");
    }


    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }


}