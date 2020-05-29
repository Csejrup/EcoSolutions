package ecosolutions.presentation.controllers.ManagerWindow;

import com.jfoenix.controls.*;
import ecosolutions.presentation.controllers.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class ManagerController extends AbstractController implements Initializable {

    @FXML private Text txtorderno, txtorderstat, txtempname, txtrole, txtstatus;

    @FXML private JFXButton btnrefresh, btnworkforce, btnorders, btnstatistics, btnsettings;

    @FXML private JFXTextField employID, orderID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // initCol();
    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void loadOrderList(ActionEvent event) {
        Stage stage = (Stage) btnorders.getScene().getWindow();
        loadpopup("FullOrderListView.fxml");
    }

    @FXML
    void loadSettings(ActionEvent event) {
        Stage stage = (Stage) btnworkforce.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }

    @FXML
    void loadStatisticsList(ActionEvent event) {
    }

    @FXML
    void loadWorkForceList(ActionEvent event) {
        Stage stage = (Stage) btnworkforce.getScene().getWindow();
        loadpopup("EmployeesView.fxml");
    }

}
