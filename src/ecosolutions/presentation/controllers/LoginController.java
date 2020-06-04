package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import ecosolutions.Domain.AccountService;
import ecosolutions.Domain.EmployeeService;
import ecosolutions.Domain.OrderService;
import ecosolutions.persistence.DAO.AccountDao;
import ecosolutions.persistence.DAO.Dao;
import ecosolutions.persistence.DAO.EmployeeDao;
import ecosolutions.persistence.DAO.OrderDao;
import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.presentation.models.Account;
import ecosolutions.presentation.models.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javax.swing.JOptionPane.showMessageDialog;

public class LoginController extends AbstractController{

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

        // method in the comment works
        if(employeeID.startsWith("M")) {
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "ManagerView.fxml");
            //verifyLogIn("ManagerView.fxml");

        }
        else if (employeeID.startsWith("S")){
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "DeliveryPointView.fxml");
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

    private void verifyLogIn(String scene) throws IOException {
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






