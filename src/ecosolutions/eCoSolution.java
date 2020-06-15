package ecosolutions;

import ecosolutions.persistence.DatabaseHandler;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

/**
 * This is a running application
 * designed to simulate a running dry cleaning service
 * where customers create orders and the delivery point handles the order
 * The order goes further into the system, ending in the hands of a driver
 * the driver has the responsibility to delivering the order to central and back to delivery point
 * In the the central the laundry worker have the responsibility to handle a cleaning process
 * and updating the order for when it is ready to be delivered back to customer
 * the manager is able to see an overview of orders and employees.
 */
public class eCoSolution extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("presentation/view/LoginView.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Eco Solutions");
            primaryStage.setResizable(false);
            primaryStage.show();

            new Thread(() -> DatabaseHandler.getInstance()).start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
