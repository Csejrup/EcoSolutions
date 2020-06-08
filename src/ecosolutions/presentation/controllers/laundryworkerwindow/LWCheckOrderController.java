package ecosolutions.presentation.controllers.laundryworkerwindow;

import ecosolutions.Domain.OrderService;
import ecosolutions.alert.AlertCreator;
import ecosolutions.presentation.controllers.AbstractController;
import ecosolutions.presentation.models.Order;

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
 *
 */
public class LWCheckOrderController extends AbstractController implements Initializable {

    @FXML private BorderPane borderPane;
    @FXML private StackPane rootPane;
    @FXML private JFXTextField orderNoTextField;
    @FXML private JFXButton btnCleaning, btncheckOrder, btnLogOut;
    @FXML private TableView<Order> tableview;
    @FXML private TableColumn<Order, String> launditemCol;
    @FXML private TableColumn<Order, Integer> qtyCol;

    private String _ORDER_NO;
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
            AlertCreator.showAlertDialog(rootPane,borderPane, Arrays.asList(button),"Insert Order Number",null);
        }
    }
    @FXML
    private void handleCleaning(ActionEvent event) {
        var orderservice = new OrderService();
        try {
            if (id() != 0) {
                orderservice.updateOrder(id(),"Cleaning");
                Stage stage = (Stage) btnCleaning.getScene().getWindow();
                loadScreen(stage, "LaundryWorkerView.fxml");
            }else{
                JFXButton button = new JFXButton("Okay");
                AlertCreator.showAlertDialog(rootPane,borderPane, Arrays.asList(button),"Check Order First, Insert Order Number",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
            }
        } catch (Exception e) {
            JFXButton button = new JFXButton("Okay");
            AlertCreator.showAlertDialog(rootPane,borderPane, Arrays.asList(button),"Check Order First, Insert Order Number",null);
        }
    }
    @FXML
    private void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }
    private int id(){
        _ORDER_NO = orderNoTextField.getText();
        return Integer.parseInt(_ORDER_NO);
    }
}
