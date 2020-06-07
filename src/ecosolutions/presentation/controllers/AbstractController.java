package ecosolutions.presentation.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
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
     *
     * @param primaryStage
     * @param layoutfile
     */
    protected void loadScreen(Stage primaryStage, String layoutfile) {
        try {
            System.out.println(layoutfile);
            Parent root = FXMLLoader.load(getClass().getResource("/ecosolutions/presentation/views/" + layoutfile));
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
            FXMLLoader fxmlloader = new FXMLLoader((getClass().getResource("/ecosolutions/presentation/views/" + layoutfile)));
            Parent root2 = fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Pop Up");
            stage.setScene(new Scene(root2));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
