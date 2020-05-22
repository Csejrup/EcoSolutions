package ecosolutions.presentation.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    protected void loadScreen(Stage primaryStage, String layoutfile){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("ecosolutions/presentation/views/" + layoutfile));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Eco Solutions");
            primaryStage.setScene(scene);
            primaryStage.show();


        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
