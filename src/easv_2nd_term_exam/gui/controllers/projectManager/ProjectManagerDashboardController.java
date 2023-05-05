package easv_2nd_term_exam.gui.controllers.projectManager;

import easv_2nd_term_exam.be.Report;
import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.enums.CustomerType;
import easv_2nd_term_exam.enums.InstallationType;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import easv_2nd_term_exam.util.AppUtility;
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
import java.util.ResourceBundle;

public class ProjectManagerDashboardController implements Initializable {


    @FXML
    private TableColumn<Report, String> customerAddressColumn, customerEmailColumn, customerNameColumn, installationTypeColumn;


    @FXML
    private TableColumn<Report, Integer> installationIdColumn, technicianIdColumn, customerIdColumn;


    @FXML
    private TableView<Report> reportTableView;

    @FXML
    private AnchorPane projectManagerPane;


    @FXML
    private Label userLabel;

    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;
    private User loggedUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setProjectManagerDashboardController(this);
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
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<Report, Integer>("customerId"));
    }
    /*@FXML
    void downloadReport(ActionEvent event) {
        Report selectedReport = reportTableView.getSelectionModel().getSelectedItem();
        if (selectedReport != null) {
            AppUtility.generatePdfReport(selectedReport);
        } else {
            AppUtility.showInformationDialog("Please select a report to download.");
        }
    }*/

    @FXML
    void downloadReport(ActionEvent event) {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/report/ReportHolder.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root, 895, 895);
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();*/

        Report selectedReport = reportTableView.getSelectionModel().getSelectedItem();
        if (selectedReport != null) {
            Node source = (Node) event.getSource();
            Stage primaryStage = (Stage) source.getScene().getWindow();

            AppUtility.generatePdfReport(selectedReport, primaryStage);
        } else {
            AppUtility.showInformationDialog("Please select a report to download.");
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

    @FXML
    void updateReport(ActionEvent event) {
        Report selectedReport = reportTableView.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/projectManager/UpdateReportView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UpdateReportViewController controller = loader.getController();
        controller.getTechIdField().setText(String.valueOf(selectedReport.getTechnicianId()));
        controller.getTechNameField().setText(selectedReport.getTechnicianName());
        controller.getCustomerNameField().setText(selectedReport.getCustomerName());
        controller.getCustomerAddressField().setText(selectedReport.getCustomerAddress());
        controller.getCustomerEmailField().setText(selectedReport.getCustomerEmail());
        controller.getCustomerTypeBox().setValue(CustomerType.valueOf(selectedReport.getCustomerType()));
        controller.getInstallationTypeBox().setValue(InstallationType.valueOf(selectedReport.getInstallationType()));
        controller.getDeviceUsernameField().setText(selectedReport.getUsername());
        controller.getDevicePasswordField().setText(selectedReport.getPassword());
        controller.getDatePicker().setValue(selectedReport.getCreatedDate());
        controller.getExpireDatePicker().setValue(selectedReport.getExpiryDate());
        controller.getDescriptionArea().setText(selectedReport.getDescription());
        controller.getCustomerIdField().setText(String.valueOf(selectedReport.getCustomerId()));
        controller.getInstallationIdField().setText(String.valueOf(selectedReport.getInstallationId()));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Update Installation/Report");
        stage.setScene(scene);
        stage.show();


    }



}
