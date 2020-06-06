package ecosolutions.CustomerMain.controller.customerC;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import ecosolutions.Domain.CustomerService;
import ecosolutions.presentation.models.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class accountController {

    @FXML
    private JFXTextField fnTextField;

    @FXML
    private JFXTextField lnTextField;

    @FXML
    private JFXTextField phoneTextField;

    @FXML
    private JFXButton btncancel;

    @FXML
    private JFXButton btncreate;

    @FXML
    private JFXPasswordField pwTextField;

    @FXML
    private JFXPasswordField confipwTextField;

    @FXML
    void handleAccount(ActionEvent event) {
        String customerName = fnTextField.getText();
        String customerSurName = lnTextField.getText();
        String customerPhoneNr = phoneTextField.getText();
        String customerPW = pwTextField.getText();
        String customerConfirmPW = confipwTextField.getText();

        if (customerName.isEmpty() || customerSurName.isEmpty() || customerPhoneNr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("INSERT CUSTOMER DETAILS");
            alert.show();
        } else {
            try {
            if(customerPW.equals(customerConfirmPW)){
            CustomerService.addCustomer(new Customer(customerName, customerSurName, customerPhoneNr));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("CUSTOMER CREATED");
            alert.setContentText("CUSTOMER HAS BEEN CREATED");
            alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(" PASSWORDS AREN'T THE SAME");
            }
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("CAN'T CREATE CUSTOMER");
            }
        }
    }
    @FXML
    void handleCancel(ActionEvent event) {

    }

}