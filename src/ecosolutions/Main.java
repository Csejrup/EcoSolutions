package ecosolutions;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            URL url = new File("src\\ecosolutions\\View\\fxml\\loginsceen.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            primaryStage.setScene(new Scene(root));
            root.getStylesheets().add("style.css");
            primaryStage.setTitle("EcoSolution");
            primaryStage.setResizable(false);
         //   primaryStage.setScene(new Scene(root, 300, 275));
            primaryStage.show();

        }catch(Exception e){
            e.printStackTrace();

        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
