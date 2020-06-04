package ecosolutions.alert;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import java.util.*;

/**
 * This Class is for making global accessible Alerts throughout the code.
 */
public class AlertCreator {




    public static void showAlertDialog(StackPane root, Node nodeblurred, List<JFXButton> dialogcontrols, String header, String body) {
        BoxBlur blur = new BoxBlur(2, 2, 2);
        if (dialogcontrols.isEmpty()) {
            dialogcontrols.add(new JFXButton("Okay"));
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);
        dialogcontrols.forEach(controlButton -> {
            controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
        });
        dialogLayout.setHeading(new Label(header));
        dialogLayout.setBody(new Label(body));
        dialogLayout.setActions(dialogcontrols);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
            nodeblurred.setEffect(null);
        });
        nodeblurred.setEffect(blur);
    }
}
