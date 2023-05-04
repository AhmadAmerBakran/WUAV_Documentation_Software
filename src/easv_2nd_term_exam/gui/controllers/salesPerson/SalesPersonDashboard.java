package easv_2nd_term_exam.gui.controllers.salesPerson;

import easv_2nd_term_exam.be.Report;
import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SalesPersonDashboard implements Initializable {

    @FXML
    private TableColumn<Report, String> customerAddressColumn, customerEmailColumn, customerNameColumn, installationTypeColumn;


    @FXML
    private TableColumn<Report, Integer> installationIdColumn, technicianIdColumn;


    @FXML
    private TableView<Report> reportTableView;

    @FXML
    private AnchorPane salesPersonPane;

    @FXML
    private Label userLabel;

    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;

    private User loggedUser;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedUser = ControllerManager.getInstance().getLoginViewController().getLoggedUser();
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        setUpReportTableView();
        userLabel.setText(loggedUser.getName());

    }

    private void setUpReportTableView()
    {
        reportTableView.getItems().setAll(modelManager.getReportModel().getAllReports());
        installationIdColumn.setCellValueFactory(new PropertyValueFactory<Report, Integer>("installationId"));
        technicianIdColumn.setCellValueFactory(new PropertyValueFactory<Report, Integer>("technicianId"));
        installationTypeColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("installationType"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("customerName"));
        customerEmailColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("customerEmail"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("customerAddress"));
    }
    @FXML
    void downloadReport(ActionEvent event) {

    }

    @FXML
    void handleLogout(ActionEvent event) {

    }

    @FXML
    void showAllReports(ActionEvent event) {
        setUpReportTableView();

    }


}
