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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SalesPersonDashboard implements Initializable {

    @FXML
    private TableColumn<Report, String> customerAddressColumn, customerEmailColumn, customerNameColumn, installationTypeColumn;


    @FXML
    private TableColumn<Report, Integer> installationIdColumn, technicianIdColumn, customerIdColumn;


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

    public void setUpReportTableView() {
        try {
            reportTableView.getItems().setAll(modelManager.getReportModel().getAllReports());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setCellValueFactories(installationIdColumn, technicianIdColumn, installationTypeColumn, customerNameColumn, customerEmailColumn, customerAddressColumn, customerIdColumn);
        setTableColumnsPrefWidth(reportTableView, installationIdColumn, technicianIdColumn, installationTypeColumn, customerNameColumn, customerEmailColumn, customerAddressColumn, customerIdColumn);
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
