package ecosolutions.presentation.controller.managerwindow;

import ecosolutions.service.EmployeeService;
import ecosolutions.Domain.Employee;
import java.net.URL;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import java.util.*;

/**
 *
 */
public class EmployeesListController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();

    @FXML private TableView<Employee> tableView;
    @FXML private TableColumn<Employee, String> idCol;
    @FXML private TableColumn<Employee, String> fnCol;
    @FXML private TableColumn<Employee, String> lnCol;
    @FXML private TableColumn<Employee, String> roleCol;
    @FXML private TableColumn<Employee, String> statCol;
    @FXML private TableColumn<Employee, String> phone_noCol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }
    private void initCol(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("employeeid"));
        fnCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lnCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        statCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        phone_noCol.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
    }
    private void loadData(){
        List<Employee> listofemployees = EmployeeService.getEmployees();
        list.clear();
        tableView.getItems().addAll(listofemployees);
    }
}
