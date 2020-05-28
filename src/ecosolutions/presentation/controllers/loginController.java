package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    /**
     * TEMPORARY SOLUTION TO LOGIN
     *
     */
    @FXML
    private void handleLogIn(ActionEvent event) throws IOException {
        String employeeID = txtUserName.getText();
        String password = txtPW.getText();

        if(employeeID.startsWith("s")) {
            System.out.println("hej");
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "shopassistantscreen.fxml");
        }
        else if (employeeID.startsWith("m")){
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "managerscreen.fxml");
        }
        else if (employeeID.startsWith("l")){
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "laundryworkerscreen.fxml");
        }
        else if (employeeID.startsWith("d")){
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "driverscreen.fxml");
        } else{
            txtUserName.getStyleClass().add("wrong-credentials");
            txtPW.getStyleClass().add("wrong-credentials");
        }
    }
    /* TODO IMPLEMENT KUBAS CODE
    /*
    @FXML
    private void handleLogIn(ActionEvent event) throws IOException
    {
        String employeeID = txtUserName.getText();
        String password = txtPW.getText();

        //btnLogIn.setOnAction(e->{
           // boolean loggedin = Account.verifyLogin(employeeID, password);
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

        btnLogIn.setOnAction(e->{
            boolean loggedin = Account.verifyLogin(employeeID, password);

            if(loggedin){
                if(Role.equals("Manager")){

                    //LOAD NEW SCREEN FOR MANAGER
                }
                if(Role.equals("ShopAssistant")){

                    //LOAD NEW SCREEN FOR SHOPASSISTANT
                }

                if(employeeID.startsWith("d")) {
                    Stage stage = (Stage) btnLogIn.getScene().getWindow();
                    loadScreen(stage, "driverscreen.fxml");
                }
  /*
                if(Role.equals("Driver")){

                    //LOAD NEW SCREEN FOR DRIVER
                }

                    if(employeeID.startsWith("l")){
                        Stage stage = (Stage) btnLogIn.getScene().getWindow();
                        loadScreen(stage, "cleaningscreen.fxml");
                        //LOAD NEW SCREEN FOR DRIVER
                    }
            }else{
                 txtUserName.getStyleClass().add("wrong-credentials");
                 txtPW.getStyleClass().add("wrong-credentials");
                /*
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("INCORRECT LOGIN, TRY AGAIN");
                alert.setHeaderText("BAD LOGIN");
                alert.show();


            }
    //    });

    }
    */
}