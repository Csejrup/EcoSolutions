package ecosolutions.presentation.controller;

import com.jfoenix.controls.*;
import ecosolutions.alert.AlertCreator;
import ecosolutions.service.AccountService;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import javax.swing.*;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Controller responsible for handling the view LoginView.fxml
 * Responsible for getting account objects in the database
 * and compare them with inserted input data from end-user
 * Primarily responsible for switching between stages.
 */
public class LoginController extends AbstractController implements Initializable {
    @FXML private StackPane stackroot;
    @FXML private BorderPane bpaneroot;
    @FXML private JFXTextField txtUserName;
    @FXML private JFXPasswordField txtPW;
    @FXML private JFXButton btnLogIn;

    public static int accountID = 0;

    //Action for btnLogIn
    @FXML
    private void handleLogIn(ActionEvent event) {
        String employeeID = txtUserName.getText();
        if (employeeID.startsWith("M")) {
             Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "ManagerView.fxml");
           verifyLogIn("ManagerView.fxml");
        } else if (employeeID.startsWith("S")) {
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "deliverypointmain.fxml");
           verifyLogIn("deliverypointmain.fxml");
        } else if (employeeID.startsWith("L")) {
              Stage stage = (Stage) btnLogIn.getScene().getWindow();
              loadScreen(stage, "LaundryWorkerView.fxml");
           verifyLogIn("LaundryWorkerView.fxml");
        } else if (employeeID.startsWith("D")) {
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "DriverView.fxml");
            verifyLogIn("DriverView.fxml");
        }else{
            txtUserName.getStyleClass().add("wrong-credentials");
            txtPW.getStyleClass().add("wrong-credentials");
            txtUserName.setText("");
            txtPW.setText("");
        }
    }
    //Method takes username and password inputted by user and check if it matches data in DB
    private void verifyLogIn(String scene) {
        try {
            String username = txtUserName.getText();
            String password = txtPW.getText();
            var account = AccountService.getAccount(username);
            String pass = account.get().getPw();
            accountID = account.get().getAccount_id();
            System.out.println(accountID);
            if (account.isEmpty() || !pass.equals(password)) {
               // showMessageDialog(null, "Wrong username or password. Try again!");
            } else {
                Stage stage = (Stage) btnLogIn.getScene().getWindow();
                loadScreen(stage, scene);
            }
        } catch (Exception e) {
            txtUserName.getStyleClass().add("wrong-credentials");
            txtPW.getStyleClass().add("wrong-credentials");
            txtUserName.setText("");
            txtPW.setText("");
            //showMessageDialog(null, "Wrong username or password. Try again!");
        }
    }
    /**
     * The initalize method is purely used for showing a dialogbox
     * with crucial information for the tester.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXButton button = new JFXButton("Okay");
        AlertCreator.showAlertDialog(stackroot,bpaneroot, Collections.singletonList(button),"Log In Information For Testing","Hello, Tester please " +
                "insert one of the following options " +
                "into the username textfield" +
                "and press the login button, no password needed:" +
                "\n'S' for DeliveryPoint\n'D' for Driver\n'L' for Laundry Worker\n'M' for Manager");
    }
}






