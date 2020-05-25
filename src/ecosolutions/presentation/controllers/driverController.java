package ecosolutions.presentation.controllers;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class driverController {

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

    }

}
