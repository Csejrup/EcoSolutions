package ecosolutions.presentation.controllers.managerwindow;

import com.jfoenix.controls.*;
import ecosolutions.Domain.EmployeeService;
import ecosolutions.persistence.DatabaseHandler;
import ecosolutions.presentation.controllers.AbstractController;
import ecosolutions.presentation.models.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import ecosolutions.presentation.models.Status;


/**
 *
 */
public class ManagerController extends AbstractController implements Initializable {

    @FXML private Text txtorderno, txtorderstat, txtempname, txtrole, txtstatus;
    @FXML private JFXButton btnrefresh, btnworkforce, btnorders, btnstatistics, btnsettings;
    @FXML private JFXTextField employIDInput, orderIDInput;
    @FXML private StackPane ordercontainer, employcontainer;
    @FXML private Tab overviewtab, laundryotab;

    private PieChart orderchart, employchart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      initGraphs();
    }

    @FXML
    private void loadEmployeeInfo(){
        clearemployCache();
        disablenableGraph(false);

    }

    @FXML
    private void loadOrderInfo(){
        clearOrderCache();
    }

    void clearOrderCache(){
        txtorderno.setText("");
        txtorderstat.setText("");
    }
    void clearemployCache(){
        txtempname.setText("");
        txtrole.setText("");
        txtstatus.setText("");
    }
    private void initGraphs(){
        orderchart = new PieChart(DatabaseHandler.getOrderGraphStatistics());
        ordercontainer.getChildren().add(orderchart);

        employchart = new PieChart(DatabaseHandler.getEmployeeStatistics());
        employcontainer.getChildren().add(employchart);
    }
    private void refreshGraphs(){
        orderchart = new PieChart(DatabaseHandler.getOrderGraphStatistics());
        employchart = new PieChart(DatabaseHandler.getEmployeeStatistics());
    }
    private void disablenableGraph(Boolean status){
        if(status){
            orderchart.setOpacity(1);
            employchart.setOpacity(1);
        }else{
            orderchart.setOpacity(0);
            employchart.setOpacity(0);
        }
    }
    private boolean validateInput(){
       orderIDInput.fireEvent(new ActionEvent());
       employIDInput.fireEvent(new ActionEvent());

        return orderIDInput.getText().isEmpty() || employIDInput.getText().isEmpty()
                || txtorderno.getText().isEmpty() || txtempname.getText().isEmpty()
                || txtorderno.getText().equals(Status.ORDER_DOES_NOT_EXIST) || txtempname.getText().equals(Status.EMPLOYEE_DOES_NOT_EXIST);
    }
    @FXML
    void handleRefresh(ActionEvent event) {

    }
    @FXML
    void loadOrderList(ActionEvent event) {
        loadpopup("FullOrderListView.fxml");
    }
    @FXML
    void loadSettings(ActionEvent event) {
        Stage stage = (Stage) btnworkforce.getScene().getWindow();
        loadScreen(stage, "LoginView.fxml");
    }
    @FXML
    void loadStatisticsList(ActionEvent event) {

    }
    @FXML
    void loadWorkForceList(ActionEvent event) {
        loadpopup("EmployeesView.fxml");
    }
}
