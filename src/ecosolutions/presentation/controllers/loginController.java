package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController extends AbstractController{

    @FXML
    private AnchorPane rootpane;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPW;

    @FXML
    private JFXButton btnLogIn;

    @FXML
    void handleLogIn(ActionEvent event) throws IOException
    {
        //(txtPW.getText().equals("1234") && txtUserName.getText().equals("johnwick")
        if(txtPW.getText().equals(""))
        {
            Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            loadScreen(mainWindow, "loginscreen.fxml");
        }
        else
        {
          //  info1.setText("Password is incorrect. Please Try Again");
        }
    }
}