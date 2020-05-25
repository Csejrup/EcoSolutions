package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import ecosolutions.Domain.Account.Account;
import ecosolutions.persistence.DB;
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
        String employeeID = txtUserName.getText();
        String password = txtPW.getText();

       // btnLogIn.setOnAction(e->{
            //boolean loggedin = Account.verifyLogin(userName, password);
           // if(loggedin){
                if(true){
                if(employeeID.startsWith("m")){
                    Stage stage = (Stage) btnLogIn.getScene().getWindow();
                    loadScreen(stage, "managerscreen.fxml");
                    //LOAD NEW SCREEN FOR MANAGER
                }
                if(employeeID.startsWith("s")) {
                    System.out.println("hej");
                    Stage stage = (Stage) btnLogIn.getScene().getWindow();
                    loadScreen(stage, "shopassistantscreen.fxml");
                }
  /*
  //JBDC CODE NOT IN CONTROLLER!!!!!!!
        btnLogIn.setOnAction(e->{
            boolean loggedin = Account.verifyLogin(employeeID, password);
            DB.selectSQL("SELECT fldRole from tblRole where fldEmployeeID = '"+employeeID+"';");
            String Role = DB.getQueryData();
            if(loggedin){
                if(Role.equals("Manager")){

                    //LOAD NEW SCREEN FOR MANAGER
                }
                if(Role.equals("ShopAssistant")){

                    //LOAD NEW SCREEN FOR SHOPASSISTANT
                }
                */              
                if(employeeID.startsWith("d")) {
                    Stage stage = (Stage) btnLogIn.getScene().getWindow();
                    loadScreen(stage, "driverscreen.fxml");
                }
  /*
                if(Role.equals("Driver")){

                    //LOAD NEW SCREEN FOR DRIVER
                }
                */
                    if(employeeID.startsWith("l")){
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