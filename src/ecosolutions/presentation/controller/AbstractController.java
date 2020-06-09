package ecosolutions.presentation.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This Class Represents an Abstract Controller
 * For all Controller Classes used in this Project
 *
 */
public abstract class AbstractController {
    /**
     * Method loads a new screen and assigns it to the current Stage
     * @param primaryStage
     * @param layoutfile
     */
    protected void loadScreen(Stage primaryStage, String layoutfile) {
        try {
            System.out.println(layoutfile);
            Parent root = FXMLLoader.load(getClass().getResource("/ecosolutions/presentation/view/" + layoutfile));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Eco Solutions");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methods loads a new fxml file and creates a new stage (pop up window)
     * @param layoutfile
     */
    protected void loadpopup(String layoutfile) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader((getClass().getResource("/ecosolutions/presentation/view/" + layoutfile)));
            Parent root2 = fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Pop Up");
            stage.setScene(new Scene(root2));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methods loads a new stage with Stage and Stackpane as load in,
     * @param secondaryStage
     * @param layout
     */
    protected void loadnewstage(Stage secondaryStage, StackPane layout) {
        try {
            Parent content = layout;
            secondaryStage.setTitle("Statistic");
            secondaryStage.setScene(new Scene(content,500,500));
            secondaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
