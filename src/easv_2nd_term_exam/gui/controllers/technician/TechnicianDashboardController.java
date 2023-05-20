package easv_2nd_term_exam.gui.controllers.technician;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.enums.CustomerType;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.util.DialogUtility;
import easv_2nd_term_exam.util.FileUtility;
import easv_2nd_term_exam.util.PdfReportGenerator;
import easv_2nd_term_exam.util.PictureUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TechnicianDashboardController implements Initializable {

    @FXML
    private TextField customerAddressField, customerEmailField, customerNameField,
            techEmailField, techIdField, techNameField, billingAddressField;

    @FXML
    private AnchorPane reportPane;
    @FXML
    private BorderPane technicianInfoPane, customerInfoPane, installationInfoPane, installationPhotoPane;
    @FXML
    private DatePicker datePicker, expireDatePicker;
    @FXML
    private ComboBox < InstallationType > installationTypeBox;
    @FXML
    private ComboBox < CustomerType > customerTypeBox;
    @FXML
    private Label userLabel, diagramPathLabel, uploadedPictureLabel;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TableView < Report > reportTableView;
    @FXML
    private TableColumn < Report, Integer > installationIdColumn;
    @FXML
    private TableColumn < Report, String > installationTypeColumn, customerNameColumn, customerEmailColumn, customerAddressColumn;

    @FXML
    private TableView < Customer > customerTableView;

    @FXML
    private TableColumn < Customer, Integer > customerIdColumnS;

    @FXML
    private TableColumn < Customer, String > customerNameColumnS, customerEmailColumnS,
            customerFirstAddressColumnS, customerSecondAddressColumnS;

    @FXML
    private TableView < DeviceType > deviceTypeTableView;

    @FXML
    private TableColumn < DeviceType, Integer > deviceTypeIdColumn;

    @FXML
    private TableColumn < DeviceType, String > deviceTypeNameColumn;

    @FXML
    private VBox newCustomerVBox;
    @FXML
    private HBox billingAddressHBox;

    private ModelManager modelManager;
    private Report selectedReport;
    private Customer selectedCustomer;
    private DeviceType selectedDeviceType;
    private ObservableList <Device> devices;
    private User loggedUser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        devices = FXCollections.observableArrayList();
        selectedReport = reportTableView.getSelectionModel().getSelectedItem();
        try {
            modelManager = new ModelManager();
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }
        installationTypeBox.getItems().setAll(modelManager.getInstallationTypeModel().getInstallationTypes());
        customerTypeBox.getItems().setAll(CustomerType.values());
        ControllerManager.getInstance().setTechnicianDashboardController(this);
        handleViewSwitch(true, false, false, false, false);
        loggedUser = ControllerManager.getInstance().getLoginViewController().getLoggedUser();
        datePicker.setValue(LocalDate.now());
        expireDatePicker.setValue(datePicker.getValue().plusMonths(48));
        fillTechnicianData();
        customerTypeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == CustomerType.B2B) {
                billingAddressHBox.setVisible(true);
            } else {
                billingAddressHBox.setVisible(false);
                billingAddressField.clear();
            }
        });
        setUpReportTableView();
        setUpCustomerTableView();
        setUpDeviceTypeTableView();
        newCustomerVBox.setVisible(false);

    }

    private void handleViewSwitch(boolean reportOverview, boolean technicianInfoOverview, boolean customerInfoOverview, boolean installationInfoOverview, boolean installationPhotoOverview) {
        reportPane.setVisible(reportOverview);
        technicianInfoPane.setVisible(technicianInfoOverview);
        customerInfoPane.setVisible(customerInfoOverview);
        installationInfoPane.setVisible(installationInfoOverview);
        installationPhotoPane.setVisible(installationPhotoOverview);

    }

    public ObservableList <Device> getDevices() {
        return devices;
    }

    private void fillTechnicianData() {
        userLabel.setText(loggedUser.getName());
        techIdField.setText(String.valueOf(loggedUser.getId()));
        techNameField.setText(loggedUser.getName());
        techEmailField.setText(loggedUser.getEmail());
    }

    private void setUpReportTableView() {
        try {
            reportTableView.getItems().setAll(modelManager.getReportModel().getAllTechnicianReports(loggedUser.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        installationIdColumn.setCellValueFactory(new PropertyValueFactory < Report, Integer > ("installationId"));
        installationTypeColumn.setCellValueFactory(new PropertyValueFactory < Report, String > ("installationType"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory < Report, String > ("customerName"));
        customerEmailColumn.setCellValueFactory(new PropertyValueFactory < Report, String > ("customerEmail"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory < Report, String > ("customerAddress"));
    }

    private void setUpDeviceTypeTableView() {
        deviceTypeTableView.getItems().setAll(modelManager.getDeviceTypeModel().getDeviceTypes());
        deviceTypeIdColumn.setCellValueFactory(new PropertyValueFactory < DeviceType, Integer > ("id"));
        deviceTypeNameColumn.setCellValueFactory(new PropertyValueFactory < DeviceType, String > ("name"));

    }

    private void setUpCustomerTableView() {
        customerTableView.getItems().setAll(modelManager.getCustomerModel().getCustomers());
        customerIdColumnS.setCellValueFactory(new PropertyValueFactory < Customer, Integer > ("id"));
        customerNameColumnS.setCellValueFactory(new PropertyValueFactory < Customer, String > ("name"));
        customerEmailColumnS.setCellValueFactory(new PropertyValueFactory < Customer, String > ("email"));
        customerFirstAddressColumnS.setCellValueFactory(new PropertyValueFactory < Customer, String > ("address"));
        customerSecondAddressColumnS.setCellValueFactory(new PropertyValueFactory < Customer, String > ("billingAddress"));
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        // Get a reference to the current window
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();

        // Hide the current window
        currentStage.hide();

        // Load the login window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/login/LoginView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Show the login window
        Stage loginStage = new Stage();
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }

    @FXML
    private void openDrawingLayout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/technician/DrawingLayout.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Draw Diagram");
        stage.show();
    }



    @FXML
    private void openFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp"));

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFiles != null) {
            for (File selectedFile : selectedFiles) {
                String packagePath = createPackagePath();
                File targetDirectory = new File(packagePath);
                if (!targetDirectory.exists()) {
                    targetDirectory.mkdirs();
                }

                File targetFile = FileUtility.findUniqueOutputFile(packagePath, selectedFile.getName());
                try {
                    FileUtility.copySelectedFile(selectedFile, targetFile);
                    // uploadedPictureLabel.setText(targetFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private String createPackagePath() {
        String projectPath = System.getProperty("user.dir") + File.separator + "src";
        String sanitizedUserName = loggedUser.getName().replace(" ", "_");
        return projectPath + File.separator + "easv_2nd_term_exam" + File.separator + "installation_pictures" + File.separator + sanitizedUserName.toLowerCase() + File.separator;
    }

    public Label getDiagramPathLabel() {
        return diagramPathLabel;
    }

    @FXML
    private void cancel(ActionEvent event) {}
    @FXML
    private void saveReport(ActionEvent event) {
        String customerName = customerNameField.getText();
        String customerEmail = customerEmailField.getText();
        String customerAddress = customerAddressField.getText();
        CustomerType type = customerTypeBox.getValue();
        String customerSecondAddress = billingAddressField.getText();
        Customer customer = null;

        try {
            customer = modelManager.getCustomerModel().findCustomerByEmail(customerEmail);
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }

        if (customer == null) {
            customer = new Customer(customerName, customerAddress, customerEmail, type);
            customer.setBillingAddress(customerSecondAddress);
            try {
                modelManager.getCustomerModel().createCustomer(customer);
            } catch (Exception e) {
                DialogUtility.showExceptionDialog(e);
            }
        } else {
            customer.setName(customerName);
            customer.setAddress(customerAddress);
            customer.setType(type);
            customer.setBillingAddress(customerSecondAddress);
            try {
                modelManager.getCustomerModel().updateCustomerByEmail(customer);
            } catch (Exception e) {
                DialogUtility.showExceptionDialog(e);;
            }
        }

        int customerId = customer.getId();
        int technicianId = Integer.parseInt(techIdField.getText());
        String installationDescription = descriptionArea.getText();
        InstallationType installationType = installationTypeBox.getValue();
        Installation newInstallation = new Installation(customerId, technicianId, installationDescription, installationType.getId());
        newInstallation.setCreatedDate(datePicker.getValue());
        newInstallation.setExpiryDate(expireDatePicker.getValue());

        try {
            modelManager.getInstallationModel().createInstallation(newInstallation);
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }

        int installationId = newInstallation.getId();
        File[] files = new File(createPackagePath()).listFiles();
        List<Picture> pictures = new ArrayList<>();
        if(files != null) {
            for(File file: files) {
                try {
                    Image image = new Image(new FileInputStream(file));
                    byte[] imageData = PictureUtility.imageToByteArray(image);
                    if (imageData != null) {
                        Picture picture = new Picture(installationId, "Uploaded Image", imageData);
                        pictures.add(picture);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            modelManager.getPictureModel().createPictures(pictures);
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }
        deleteFilesFromDirectory(createPackagePath());
        for (Device d: devices) {
            d.setInstallationId(installationId);
        }
        modelManager.getDevicesModel().createDevices(devices);
    }
    @FXML
    private void createNewReport(ActionEvent event) {
        handleViewSwitch(false, true, false, false, false);

    }

    @FXML
    private void showMyReports(ActionEvent event) {
        handleViewSwitch(true, false, false, false, false);
    }

    private void deleteFilesFromDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file: files) {
                    if (!file.isDirectory()) {
                        file.delete();
                    }
                }
            }
        }
    }

    @FXML
    private void removeAllPhotos(ActionEvent event) {
        FileUtility.deleteFilesFromDirectory(createPackagePath());
        diagramPathLabel.setText(null);
        uploadedPictureLabel.setText(null);
    }

    @FXML
    private void downloadReport(ActionEvent event) {
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
    private void selectCustomer(ActionEvent event) {

        selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        newCustomerVBox.setVisible(true);
        clearCustomerFields();
        customerAddressField.setText(selectedCustomer.getAddress());
        customerEmailField.setText(selectedCustomer.getEmail());
        customerNameField.setText(selectedCustomer.getName());
        customerTypeBox.setValue(selectedCustomer.getType());
        billingAddressField.setText(selectedCustomer.getBillingAddress());
    }

    @FXML
    private void addNewCustomer(ActionEvent event) {
        newCustomerVBox.setVisible(true);
        clearCustomerFields();
    }
    private void clearCustomerFields() {
        customerAddressField.clear();
        customerEmailField.clear();
        customerNameField.clear();
        billingAddressField.clear();
        customerTypeBox.setValue(null);

    }

    @FXML
    private void addDeviceToInstallation(ActionEvent event) {
        selectedDeviceType = deviceTypeTableView.getSelectionModel().getSelectedItem();
        openNewWindow("/easv_2nd_term_exam/gui/views/technician/AddDeviceView.fxml", "Add Device To Installation");
        ControllerManager.getInstance().getAddDeviceController().getDeviceTypeIdField().setText(String.valueOf(selectedDeviceType.getId()));
        ControllerManager.getInstance().getAddDeviceController().getDeviceTypeNameField().setText(selectedDeviceType.getName());

    }
    private void openNewWindow(String fxmlPath, String title) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void nextToCustomerInfo(ActionEvent event) {
        handleViewSwitch(false, false, true, false, false);

    }

    @FXML
    private void nextToInstallationInfo(ActionEvent event) {
        handleViewSwitch(false, false, false, true, false);
    }

    @FXML
    private void nextToInstallationPhotos(ActionEvent event) {
        handleViewSwitch(false, false, false, false, true);
    }
}