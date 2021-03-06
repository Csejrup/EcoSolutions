package ecosolutions.presentation.controller.deliverypointwindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ecosolutions.service.CustomerService;
import ecosolutions.service.OrderService;
import ecosolutions.presentation.controller.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Controller class for handling verifyorder.fxml
 */
public class verifyController extends AbstractController {

    @FXML private JFXButton btnverify;
    @FXML private JFXTextField phoneTextField, nameTextField, ordnoTextField;
    @FXML private JFXButton btnreturn;
    /**
     * METHOD FOR RETURNING INTO DELIVERY POINT WINDOW.
     */
    @FXML
    void handleReturn(ActionEvent event) {
        Stage stage = (Stage) btnreturn.getScene().getWindow();
        loadScreen(stage, "deliverypointmain.fxml");
    }
    /**
     * METHOD FOR ISSUING THE ORDER.
     */
    @FXML
    void handleVerify(ActionEvent event) {
        try {
            int orderID = Integer.parseInt(ordnoTextField.getText());
            String phoneNO = phoneTextField.getText();
            String name = nameTextField.getText();
            int customerID = OrderService.getCustomerIDbyOrderID(orderID);
            String custName = CustomerService.getCustomerName(customerID);
            String custPhone = CustomerService.getCustomerPhone(customerID);
            if(custName.equals(name)&&custPhone.equals(phoneNO)){
                OrderService.issueStatus(orderID);
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("ORDER HAS BEEN VERIFIED");
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("WRONG ORDER DETAILS, TRY AGAIN");
                alert.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
