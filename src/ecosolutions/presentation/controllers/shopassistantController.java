package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class shopassistantController {

    @FXML
    private AnchorPane rootpane;

    @FXML
    private Pane pane1;

    @FXML
    private JFXListView<?> itemListView;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private JFXTextField dueTextField;

    @FXML
    private JFXTextField nameTextfield;

    @FXML
    private JFXTextField phoneNoTextField;

    @FXML
    private TextField qtyTextField;

    @FXML
    void handleLogOut(ActionEvent event) {

    }

    @FXML
    void handleOrderConfirm(ActionEvent event) {

    }

}