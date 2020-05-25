package ecosolutions.presentation.controllers;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class driverController extends AbstractController{
    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXCheckBox checkBoxUp;

    @FXML
    private JFXCheckBox checkBoxDeliv;

    @FXML
    private JFXButton driverLogOut;

    @FXML
    private JFXTreeTableView<?> orderTblView;

    @FXML
    private TreeTableColumn<?, ?> ordernoCol;

    @FXML
    private TreeTableColumn<?, ?> statusCol;

    @FXML
    private TreeTableColumn<?, ?> locationCol;

    @FXML
    void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "loginscreen.fxml");
    }

}
