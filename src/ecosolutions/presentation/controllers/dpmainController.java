package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class dpmainController extends AbstractController {

    @FXML private JFXButton btncustomer, btnconfirm, btnverify,btnlogout;


    @FXML
    void handleConfirmation(ActionEvent event) {
        Stage stage = (Stage) btnconfirm.getScene().getWindow();
        loadScreen(stage, "DeliveryPointView.fxml");
    }

    @FXML
    void handleCustomer(ActionEvent event) {
        Stage stage = (Stage) btncustomer.getScene().getWindow();
        loadScreen(stage, "CustomerCreationView.fxml");
    }

    @FXML
    void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnlogout.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }

    @FXML
    void handleVerification(ActionEvent event) {
        Stage stage = (Stage) btncustomer.getScene().getWindow();
        loadScreen(stage, "verifyorder.fxml");
    }

}
