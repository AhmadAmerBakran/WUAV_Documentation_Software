package easv_2nd_term_exam.gui.controllers.projectManager;

import easv_2nd_term_exam.be.InstallationType;
import easv_2nd_term_exam.be.Report;
import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.enums.CustomerType;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProjectManagerDashboardController implements Initializable {

    @FXML
    private TableColumn<Report, String> customerAddressColumn, customerEmailColumn, customerNameColumn, installationTypeColumn;
    @FXML
    private TableColumn<Report, String> customerAddressColumnE, customerEmailColumnE, customerNameColumnE, installationTypeColumnE;

    @FXML
    private TableColumn<Report, Integer> installationIdColumn, technicianIdColumn, customerIdColumn;
    @FXML
    private TableColumn<Report, Integer> installationIdColumnE, technicianIdColumnE, customerIdColumnE;
    @FXML
    private TableColumn<Report, LocalDate> expiryDateColumn;

    @FXML
    private TableView<Report> reportTableView;
    @FXML
    private TableView<Report> expiredReportTable;

    @FXML
    private AnchorPane expiredProjectPane;
    @FXML
    private AnchorPane allProjectsPane;

    @FXML
    private Label userLabel;

    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;
    private User loggedUser;
    private Report selectedReport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setProjectManagerDashboardController(this);
        loggedUser = ControllerManager.getInstance().getLoginViewController().getLoggedUser();
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        showReportsPane(true, false);
        try {
            setUpReportTableView();
            setUpExpiringReportsTableView();
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }

        userLabel.setText(loggedUser.getName());
    }

    public void setUpReportTableView() {
        try {
            reportTableView.getItems().setAll(modelManager.getReportModel().getAllReports());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setCellValueFactories(installationIdColumn, technicianIdColumn, installationTypeColumn, customerNameColumn, customerEmailColumn, customerAddressColumn, customerIdColumn);
        setTableColumnsPrefWidth(reportTableView, installationIdColumn, technicianIdColumn, installationTypeColumn, customerNameColumn, customerEmailColumn, customerAddressColumn, customerIdColumn);
    }

    public void setUpExpiringReportsTableView() {
        try {
            expiredReportTable.getItems().setAll(modelManager.getReportModel().getExpiringReports(30));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setCellValueFactories(installationIdColumnE, technicianIdColumnE, installationTypeColumnE, customerNameColumnE, customerEmailColumnE, customerAddressColumnE, customerIdColumnE);
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
                setTableColumnsPrefWidth(expiredReportTable, installationIdColumnE, technicianIdColumnE, installationTypeColumnE, customerNameColumnE, customerEmailColumnE, customerAddressColumnE, customerIdColumnE, expiryDateColumn);
    }

    private void setCellValueFactories(TableColumn<Report, Integer> installationIdColumn, TableColumn<Report, Integer> technicianIdColumn, TableColumn<Report, String> installationTypeColumn, TableColumn<Report, String> customerNameColumn, TableColumn<Report, String> customerEmailColumn, TableColumn<Report, String> customerAddressColumn, TableColumn<Report, Integer> customerIdColumn) {
        installationIdColumn.setCellValueFactory(new PropertyValueFactory<>("installationId"));
        technicianIdColumn.setCellValueFactory(new PropertyValueFactory<>("technicianId"));
        installationTypeColumn.setCellValueFactory(new PropertyValueFactory<>("installationType"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerEmailColumn.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }

    private void setTableColumnsPrefWidth(TableView<Report> tableView, TableColumn<Report, Integer> installationIdColumn, TableColumn<Report, Integer> technicianIdColumn, TableColumn<Report, String> installationTypeColumn, TableColumn<Report, String> customerNameColumn, TableColumn<Report, String> customerEmailColumn, TableColumn<Report, String> customerAddressColumn, TableColumn<Report, Integer> customerIdColumn, TableColumn... extraColumns) {
        double[] columnRatios = {0.09, 0.09, 0.14, 0.14, 0.14, 0.21, 0.19};
        double[] columnRatiosWithExpiryDate = {0.08, 0.08, 0.12, 0.12, 0.12, 0.17, 0.13, 0.18};
        TableColumn[] columns = {installationIdColumn, technicianIdColumn, installationTypeColumn, customerNameColumn, customerEmailColumn, customerAddressColumn, customerIdColumn};

        if (extraColumns.length > 0) {
            for (int i = 0; i < columns.length; i++) {
                columns[i].prefWidthProperty().bind(tableView.widthProperty().multiply(columnRatiosWithExpiryDate[i]));
            }

            TableColumn<Report, LocalDate> expiryDateColumn = extraColumns[0];
            expiryDateColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(columnRatiosWithExpiryDate[7]));
        } else {
            for (int i = 0; i < columns.length; i++) {
                columns[i].prefWidthProperty().bind(tableView.widthProperty().multiply(columnRatios[i]));
            }
        }
    }

    private void showReportsPane(boolean allReports, boolean expiredReports) {
        allProjectsPane.setVisible(allReports);
        expiredProjectPane.setVisible(expiredReports);
    }



    @FXML
    private void handleLogout(ActionEvent event) {
        if (DialogUtility.showConfirmationDialog("Are you sure you want to logout?")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/login/LoginView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                DialogUtility.showExceptionDialog(e);
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
    }


    @FXML
    void showAllReports(ActionEvent event) {
        showReportsPane(true, false);
        try {
            setUpReportTableView();
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }
    }
    @FXML
    private void showExpiringReport(ActionEvent event) {
        showReportsPane(false, true);
        setUpExpiringReportsTableView();
    }


    private void handleDownloadReport(Report report, ActionEvent event)
    {
        if (report != null) {
            Node source = (Node) event.getSource();
            Stage primaryStage = (Stage) source.getScene().getWindow();

            try {
                PdfReportGenerator.generatePdfReport(report, primaryStage);
            } catch (Exception e) {
                DialogUtility.showExceptionDialog(e);
            }

        } else {
            DialogUtility.showInformationDialog("Please select a report to download.");
        }
    }

    @FXML
    void downloadReport(ActionEvent event) {
        selectedReport = reportTableView.getSelectionModel().getSelectedItem();
        handleDownloadReport(selectedReport, event);
    }


    @FXML
    private void downloadExpiredReport(ActionEvent event) {
        selectedReport = expiredReportTable.getSelectionModel().getSelectedItem();
        handleDownloadReport(selectedReport, event);
    }


    private void handleUpdateReport(Report report)
    {
        if (report == null) {
            DialogUtility.showInformationDialog("Please select a report to update.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/projectManager/UpdateReportView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UpdateReportViewController controller = loader.getController();
        controller.getTechIdField().setText(String.valueOf(report.getTechnicianId()));
        controller.getTechNameField().setText(report.getTechnicianName());
        controller.getCustomerNameField().setText(report.getCustomerName());
        controller.getCustomerAddressField().setText(report.getCustomerAddress());
        controller.getBillingAddressField().setText(report.getBillingAddress());
        controller.getCustomerEmailField().setText(report.getCustomerEmail());
        controller.getInstallationIdLabel().setText(String.valueOf(report.getInstallationId()));
        controller.getCustomerTypeBox().setValue(CustomerType.valueOf(report.getCustomerType()));

        controller.getDatePicker().setValue(report.getCreatedDate());
        controller.getExpireDatePicker().setValue(report.getExpiryDate());
        controller.getDescriptionArea().setText(report.getDescription());
        controller.getCustomerIdField().setText(String.valueOf(report.getCustomerId()));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Update Installation/Report");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void updateReport(ActionEvent event) {
        selectedReport = reportTableView.getSelectionModel().getSelectedItem();
        handleUpdateReport(selectedReport);
    }
    @FXML
    private void updateExpiredReport(ActionEvent event) {
        selectedReport = expiredReportTable.getSelectionModel().getSelectedItem();
        handleUpdateReport(selectedReport);
    }


    private void handleDeleteReport(Report report)
    {
        if (report != null) {
            if (DialogUtility.showConfirmationDialog("Are you sure you want to delete this report?")) {
                try {
                    modelManager.getReportModel().deleteReport(report.getInstallationId());
                    DialogUtility.showInformationDialog("Report deleted successfully.");
                    setUpExpiringReportsTableView();
                } catch (Exception e) {
                    DialogUtility.showExceptionDialog(e);
                }
            }
        } else {
            DialogUtility.showInformationDialog("Please select a report to delete.");
        }

    }

    @FXML
    private void deleteReport(ActionEvent event) {
        selectedReport = reportTableView.getSelectionModel().getSelectedItem();
        handleDeleteReport(selectedReport);
    }

    @FXML
    private void deleteExpiredReport(ActionEvent event) {
        selectedReport = expiredReportTable.getSelectionModel().getSelectedItem();
        handleDeleteReport(selectedReport);
    }
}
