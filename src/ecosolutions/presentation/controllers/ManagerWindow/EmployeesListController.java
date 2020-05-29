package ecosolutions.presentation.controllers.ManagerWindow;

import ecosolutions.Domain.EmployeeService;
import ecosolutions.presentation.models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesListController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, String> idCol;

    @FXML
    private TableColumn<Employee, String> fnCol;

    @FXML
    private TableColumn<Employee, String> lnCol;

    @FXML
    private TableColumn<Employee, String> roleCol;

    @FXML
    private TableColumn<Employee, String> statCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }
    private Stage getStage(){
        return (Stage) tableView.getScene().getWindow();
    }
    private void initCol(){
        idCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeid"));
        fnCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstname"));
        lnCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastname"));
        roleCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("role"));
        statCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("status"));
    }

    private void loadData(){
        list.clear();
        tableView.getItems().addAll(EmployeeService.getEmployees());
    }

}
