package ecosolutions.presentation.controllers.deliverypointwindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ecosolutions.Domain.CustomerService;
import ecosolutions.Domain.OrderService;
import ecosolutions.presentation.controllers.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class verifyController extends AbstractController {

    @FXML
    private JFXButton btnverify;

    @FXML
    private JFXTextField phoneTextField;

    @FXML
    private JFXButton btnreturn;

    @FXML
    private JFXTextField nameTextField;

    @FXML
    private JFXTextField ordnoTextField;

    @FXML
    void handleReturn(ActionEvent event) {
        Stage stage = (Stage) btnreturn.getScene().getWindow();
        loadScreen(stage, "DeliveryPointView.fxml");
    }

    @FXML
    void handleVerify(ActionEvent event) {
        int orderID = Integer.parseInt(ordnoTextField.getText());
        String phoneNO = phoneTextField.getText();
        String name = nameTextField.getText();
        try {
            int customerID = OrderService.getCustomerIDbyOrderID(orderID);
            String custName = CustomerService.getCustomerName(customerID);
            String custPhone = CustomerService.getCustomerPhone(customerID);
            if(custName.equals(name)&&custPhone.equals(phoneNO)){
                OrderService.issueStatus(orderID);
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
