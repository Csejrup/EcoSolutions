package ecosolutions.presentation.controllers;

import com.jfoenix.controls.*;

import ecosolutions.Domain.AccountService;
import javafx.event.*;
import javafx.fxml.*;
import javafx.stage.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class LoginController extends AbstractController{

    @FXML private JFXTextField txtUserName;
    @FXML private JFXPasswordField txtPW;
    @FXML private JFXButton btnLogIn;

    @FXML
    private void handleLogIn(ActionEvent event) {
        String employeeID = txtUserName.getText();
        String password = txtPW.getText();

        // method in the comment works
        if(employeeID.startsWith("M")) {
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "ManagerView.fxml");
            //verifyLogIn("ManagerView.fxml");
        }
        else if (employeeID.startsWith("S")){
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "CustomerCreationView.fxml");
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
        }
    }
    private void verifyLogIn(String scene) {
        String username = txtUserName.getText();
        String password = txtPW.getText();
        AccountService accountService = new AccountService();
        var account = accountService.getAccount(username);
        String pass = account.get().getPw();
        if (account.isEmpty() || !pass.equals(password)) {
            showMessageDialog(null, "Wrong username or password. Try again!");
        } else {
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, scene);
        }
    }
}






