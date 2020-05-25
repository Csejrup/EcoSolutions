package ecosolutions;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {

            Parent root = FXMLLoader.load(getClass().getResource("presentation/views/driverscreen.fxml"));
            primaryStage.setScene(new Scene(root));
            root.getStylesheets().add("style.css");
            primaryStage.setTitle("Eco Solutions");
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
