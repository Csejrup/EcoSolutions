package ecosolutions.presentation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import ecosolutions.persistence.DAO.Dao;
import ecosolutions.persistence.DAO.OrderDao;
import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.presentation.models.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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


        Dao dao = new OrderDao();

        var orders = dao.getAll();
        orders.forEach(System.out::println);



       // var loadedorder = dao.get(4);
       // System.out.println(loadedorder);

        if(employeeID.startsWith("s")) {
            System.out.println("hej");
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "DeliveryPointView.fxml");
        }
        else if (employeeID.startsWith("m")){
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "ManagerView.fxml");
        }
        else if (employeeID.startsWith("l")){
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "LaundryWorkerView.fxml");
        }
        else if (employeeID.startsWith("d")){
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            loadScreen(stage, "DriverView.fxml");
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
                    loadScreen(stage, "ManagerView.fxml");
                    //LOAD NEW SCREEN FOR MANAGER
                }
                if(employeeID.startsWith("s")) {
                    System.out.println("hej");
                    Stage stage = (Stage) btnLogIn.getScene().getWindow();
                    loadScreen(stage, "DeliveryPointView.fxml");
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
                    loadScreen(stage, "DriverView.fxml");
                }
  /*
                if(Role.equals("Driver")){

                    //LOAD NEW SCREEN FOR DRIVER
                }

                    if(employeeID.startsWith("l")){
                        Stage stage = (Stage) btnLogIn.getScene().getWindow();
                        loadScreen(stage, "CleaningView.fxml");
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