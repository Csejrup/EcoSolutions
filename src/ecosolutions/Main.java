package ecosolutions;


import ecosolutions.persistence.DatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {

            Parent root = FXMLLoader.load(getClass().getResource("presentation/views/LoginView.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Eco Solutions");
            primaryStage.setResizable(false);
            primaryStage.show();

            new Thread(() -> {
                DatabaseHandler.getInstance();
            }).start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
