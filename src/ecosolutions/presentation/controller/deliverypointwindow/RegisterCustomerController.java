package ecosolutions.presentation.controller.deliverypointwindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ecosolutions.service.CustomerService;
import ecosolutions.presentation.controller.AbstractController;
import ecosolutions.Domain.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class RegisterCustomerController extends AbstractController {


    @FXML private JFXButton btnLogOut, btnreturn;
    @FXML private JFXTextField firstnameTextField, phoneNoTextField, lastnameTextField;


    /**
     * METHOOD FOR CREATION CUSTOMER.
     * @param event
     */
    @FXML
    void handleCreateCustomer(ActionEvent event) {
        String customerName = firstnameTextField.getText();
        String customerSurName = lastnameTextField.getText();
        String customerPhoneNr = phoneNoTextField.getText();

        if(customerName.isEmpty()||customerSurName.isEmpty()||customerPhoneNr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("INSERT CUSTOMER DETAILS");
            alert.show();
        }else{
            CustomerService.addCustomer(new Customer(customerName,customerSurName,customerPhoneNr));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("CUSTOMER CREATED");
            alert.setContentText("CUSTOMER HAS BEEN CREATED");
            alert.show();

        }

    }

    /**
     * METHOD FOR LOGGING OUT.
     * @param event
     */
    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }

    /**
     * METHOD FOR RETURNING TO MAIN WINDOW.
     * @param event
     */
    @FXML
    private void handleReturn(ActionEvent event){
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "deliverypointmain.fxml");
    }
    

}