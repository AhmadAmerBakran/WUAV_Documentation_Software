package easv_2nd_term_exam.gui.controllers.salesPerson;

import easv_2nd_term_exam.be.Report;
import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import easv_2nd_term_exam.util.DialogUtility;
import easv_2nd_term_exam.util.PdfReportGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
        ControllerManager.getInstance().setSalesPersonDashboardController(this);
        loggedUser = ControllerManager.getInstance().getLoginViewController().getLoggedUser();
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        setUpReportTableView();
        userLabel.setText(loggedUser.getName());

    }

    private void setUpReportTableView()
    {
        try {
            reportTableView.getItems().setAll(modelManager.getReportModel().getAllReports());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        installationIdColumn.setCellValueFactory(new PropertyValueFactory<Report, Integer>("installationId"));
        technicianIdColumn.setCellValueFactory(new PropertyValueFactory<Report, Integer>("technicianId"));
        installationTypeColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("installationType"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("customerName"));
        customerEmailColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("customerEmail"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("customerAddress"));
    }
    @FXML
    void downloadReport(ActionEvent event) {
        Report selectedReport = reportTableView.getSelectionModel().getSelectedItem();
        if (selectedReport != null) {
            Node source = (Node) event.getSource();
            Stage primaryStage = (Stage) source.getScene().getWindow();

            PdfReportGenerator.generatePdfReport(selectedReport, primaryStage);
        } else {
            DialogUtility.showInformationDialog("Please select a report to download.");
        }
    }

    @FXML
    void handleLogout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/login/LoginView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.hide();

    }

    @FXML
    void showAllReports(ActionEvent event) {
        setUpReportTableView();

    }


}
