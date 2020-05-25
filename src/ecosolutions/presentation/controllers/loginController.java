package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import ecosolutions.Domain.Account.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController extends AbstractController{

    @FXML private AnchorPane rootpaneloginscreen;


    @FXML private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPW;

    @FXML
    private JFXButton btnLogIn;



    @FXML
    void handleLogIn(ActionEvent event) throws IOException
    {
        String userName = txtUserName.getText();
        String password = txtPW.getText();
       // btnLogIn.setOnAction(e->{
            //boolean loggedin = Account.verifyLogin(userName, password);
           // if(loggedin){
                if(true){
                if(userName.startsWith("m")){
                    Stage stage = (Stage) btnLogIn.getScene().getWindow();
                    loadScreen(stage, "managerscreen.fxml");
                    //LOAD NEW SCREEN FOR MANAGER
                }
                if(userName.startsWith("s")){
                    System.out.println("hej");
                    Stage stage = (Stage) btnLogIn.getScene().getWindow();
                    loadScreen(stage, "shopassistantscreen.fxml");

                    //LOAD NEW SCREEN FOR SHOPASSISTANT

                }
                if(userName.startsWith("d")){
                    Stage stage = (Stage) btnLogIn.getScene().getWindow();
                    loadScreen(stage, "driverscreen.fxml");
                    //LOAD NEW SCREEN FOR DRIVER
                }
                    if(userName.startsWith("l")){
                        Stage stage = (Stage) btnLogIn.getScene().getWindow();
                        loadScreen(stage, "cleaningscreen.fxml");
                        //LOAD NEW SCREEN FOR DRIVER
                    }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("INCORRECT LOGIN, TRY AGAIN");
                alert.setHeaderText("BAD LOGIN");
                alert.show();
            }
    //    });

    }
}