package ecosolutions.CustomerMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage SecondaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("presentation/views/LoginView.fxml"));
            SecondaryStage.setScene(new Scene(root));
            SecondaryStage.setTitle("Customer Screen");
            SecondaryStage.setResizable(false);
            SecondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
