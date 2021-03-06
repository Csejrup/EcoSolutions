package ecosolutions.presentation.controller.laundryworkerwindow;

import ecosolutions.service.OrderService;
import ecosolutions.alert.AlertCreator;
import ecosolutions.presentation.controller.AbstractController;
import ecosolutions.Domain.Order;

import com.jfoenix.controls.*;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.layout.*;
import java.util.*;
import java.io.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.net.URL;
/**
 * Controller responsible for the view CleaningView.fxml
 * Reponsible for updating an order object through OrderService
 * Class is responsible for handle Laundry worker GUI trough the cleaning process
 */
public class LWCheckOrderController extends AbstractController implements Initializable {

    @FXML private BorderPane borderPane;
    @FXML private StackPane rootPane;
    @FXML private JFXTextField orderNoTextField;
    @FXML private JFXButton btnCleaning, btncheckOrder, btnLogOut;
    @FXML private TableView<Order> tableview;
    @FXML private TableColumn<Order, String> launditemCol;
    @FXML private TableColumn<Order, Integer> qtyCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
    }
    private void initCol(){
        launditemCol.setCellValueFactory(new PropertyValueFactory<>("clothtype"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    @FXML
    void loadOrder(ActionEvent event) {
        try {
            tableview.getItems().clear();
            List<Order> itemlist = OrderService.checkorder(id());
            tableview.getItems().addAll(itemlist);
        } catch (NumberFormatException e) {
            JFXButton button = new JFXButton("Okay");
            AlertCreator.showAlertDialog(rootPane,borderPane, Collections.singletonList(button),"Insert Order Number",null);
        }
    }
    //Button change order status to "Cleaning"
    @FXML
    private void handleCleaning(ActionEvent event) {
        try {
            if (id() != 0) {
                OrderService.updateOrder(id(),"Cleaning");
                Stage stage = (Stage) btnCleaning.getScene().getWindow();
                loadScreen(stage, "LaundryWorkerView.fxml");
            }
        } catch (Exception e) {
            JFXButton button = new JFXButton("Okay");
            AlertCreator.showAlertDialog(rootPane,borderPane, Collections.singletonList(button),"Check Order Number","Insert an Order Number\nAnd select an order from the list");
        }
    }
    //Method print out washable labels with orderID, ClothType and quantity based on data in DB that matches given orderID
    @FXML
    void handlePrint(ActionEvent event) {
        try {
            if (id()!= 0 || tableview.getItems().isEmpty()) {
                List<Integer> itemIDlist = OrderService.getItemIDList(id());
                List<Integer> itemQuantity = OrderService.getItemQuantityByID(itemIDlist,id());
                List<String> itemTypes = OrderService.getItemTypeByID(itemIDlist);
                File file = new File("Label.txt");
                FileWriter fw = new FileWriter("Label.txt");
                for (int i = 0;i<itemIDlist.size();i++) {
                   // File filez = new File(itemTypes.get(i));
                   // FileWriter x = new FileWriter(filez);
                    for(int j =0; j<itemQuantity.get(i);j++){
                        //x.write("ORDER ID: "+id()+"\nCLOTH TYPE: " + itemTypes.get(i)+"\n");
                        fw.write("ORDER ID: "+id()+"\nCLOTH TYPE: " + itemTypes.get(i)+"\n");
                        System.out.println("ORDER ID: " + id() + "\nCLOTH TYPE: " + itemTypes.get(i)+"\nQUANTITY: "+itemQuantity.get(i));
                    }
                }
                fw.close();
                JFXButton button = new JFXButton("Okay");
                AlertCreator.showAlertDialog(rootPane,borderPane, Collections.singletonList(button),"Label Printed","Labels are now printed for Order Number: "+id()+
                        "\n You may now start the Cleaning Process");
            }
        } catch (Exception e) {
            JFXButton button = new JFXButton("Okay");
            AlertCreator.showAlertDialog(rootPane,borderPane, Collections.singletonList(button),"Check Order First, Insert Order Number",null);
        }
    }
    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }
    private int id(){
        String _ORDER_NO = orderNoTextField.getText();
        return Integer.parseInt(_ORDER_NO);
    }
}
