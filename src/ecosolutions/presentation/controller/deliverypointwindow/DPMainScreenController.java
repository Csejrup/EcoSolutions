package ecosolutions.presentation.controller.deliverypointwindow;

import com.jfoenix.controls.JFXButton;
import ecosolutions.presentation.controller.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * CLASS FOR CHOOSING WHICH SERVICE DELIVERY POINT WORKER WANTS TO USE.
 * Controller responsible for handling the view deliverypointmain.fxml
 */
public class DPMainScreenController extends AbstractController {

    @FXML private JFXButton btncustomer, btnconfirm, btnverify,btnlogout;

    /**
     * METHOD FOR CHOOSING ORDER CREATION WINDOW.
     *
     */
    @FXML
    void handleConfirmation(ActionEvent event) {
        Stage stage = (Stage) btnconfirm.getScene().getWindow();
        loadScreen(stage, "DeliveryPointView.fxml");
    }
    /**
     * METHOD FOR CHOOSING CUSTOMER CREATION WINDOW.
     *
     */
    @FXML
    void handleCustomer(ActionEvent event) {
        Stage stage = (Stage) btncustomer.getScene().getWindow();
        loadScreen(stage, "CustomerCreationView.fxml");
    }
    /**
     * METHOD FOR LOGGING OUT OF APPLICATION.
     *
     */
    @FXML
    void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnlogout.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }
    /**
     * METHOD FOR CHOOSING ORDER ISSUING WINDOW.
     *
     */
    @FXML
    void handleVerification(ActionEvent event) {
        Stage stage = (Stage) btnverify.getScene().getWindow();
        loadScreen(stage, "verifyorder.fxml");
    }

}
