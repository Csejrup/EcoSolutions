package ecosolutions.presentation.controllers;

import com.jfoenix.controls.*;

import ecosolutions.Domain.AccountService;
import ecosolutions.presentation.models.Account;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.input.*;


import static javax.swing.JOptionPane.showMessageDialog;

public class LoginController extends AbstractController{

    @FXML private JFXTextField txtUserName;
    @FXML private JFXPasswordField txtPW;
    @FXML private JFXButton btnLogIn;
    public static int accountID = Account.getStaticAccountID();

    //Action for btnLogIn
    @FXML
    private void handleLogIn(ActionEvent event) {
        String employeeID = txtUserName.getText();
        String password = txtPW.getText();
        // method in the comment works
        if(employeeID.startsWith("M")) {
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "ManagerView.fxml");
            verifyLogIn("ManagerView.fxml");
        }
        else if (employeeID.startsWith("S")){
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "deliverypointmain.fxml");
            //verifyLogIn("DeliveryPointView.fxml");
        }
        else if (employeeID.startsWith("L")){
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "LaundryWorkerView.fxml");
         // verifyLogIn("LaundryWorkerView.fxml");
        }
        else if (employeeID.startsWith("D")){
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "DriverView.fxml");
            //verifyLogIn("DriverView.fxml");
        } else{
            txtUserName.getStyleClass().add("wrong-credentials");
            txtPW.getStyleClass().add("wrong-credentials");
            txtUserName.setText("");
            txtPW.setText("");
        }
    }
    private void verifyLogIn(String scene) {
        String username = txtUserName.getText();
        String password = txtPW.getText();
        AccountService accountService = new AccountService();
        var account = accountService.getAccount(username);
        String pass = account.get().getPw();
        btnLogIn.setOnKeyPressed(event ->{
           if(event.getCode() == KeyCode.ENTER){
               if (account.isEmpty() || !pass.equals(password)) {
                   showMessageDialog(null, "Wrong username or password. Try again!");
               } else {
                   Stage stage = (Stage) btnLogIn.getScene().getWindow();
                   loadScreen(stage, scene);
               }
           }
       });
    }
}






