package easv_2nd_term_exam.gui.controllers.projectManager;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.enums.CustomerType;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import easv_2nd_term_exam.util.DialogUtility;
import easv_2nd_term_exam.util.FileUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateReportViewController implements Initializable {

    @FXML
    private TextField customerIdField, customerNameField, customerEmailField, customerAddressField, billingAddressField;

    @FXML
    private HBox billingAddressHBox;

    @FXML
    private TableColumn<Customer, String> customerEmailColumnS;


    @FXML
    private TableColumn<Customer, String> customerFirstAddressColumnS;

    @FXML
    private TableColumn<Customer, Integer> customerIdColumnS;


    @FXML
    private TableColumn<Customer, String> customerNameColumnS;

    @FXML
    private TableColumn<Customer, String> customerSecondAddressColumnS;

    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private ComboBox<CustomerType> customerTypeBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TableColumn<DeviceType, Integer> deviceTypeIdColumn;

    @FXML
    private TableColumn<DeviceType, String> deviceTypeNameColumn;

    @FXML
    private TableView<DeviceType> deviceTypeTableView;

    @FXML
    private Label diagramPathLabel;

    @FXML
    private ImageView drawingView;

    @FXML
    private DatePicker expireDatePicker;

    @FXML
    private BorderPane techPane, customerInfoPane, installationInfoPane, installationPhotoPane;


    @FXML
    private ComboBox<InstallationType> installationTypeBox;

    @FXML
    private VBox newCustomerVBox;

    @FXML
    private Button removeDiagramBtn;

    @FXML
    private Label installationIdLabel;


    @FXML
    private TextField techIdField;

    @FXML
    private TextField techNameField;


    @FXML
    private Label uploadedPictureLabel;

    private DeviceType selectedDeviceType;

    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;
    private User loggedUser;
    private ObservableList<Device> devices;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        devices = FXCollections.observableArrayList();
        ControllerManager.getInstance().setUpdateReportViewController(this);
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        loggedUser = ControllerManager.getInstance().getLoginViewController().getLoggedUser();
        customerTypeBox.getItems().setAll(CustomerType.values());
        installationTypeBox.getItems().setAll(modelManager.getInstallationTypeModel().getInstallationTypes());
        handleViewSwitch(true, false, false, false);
        customerTypeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == CustomerType.B2B) {
                billingAddressHBox.setVisible(true);
            } else {
                billingAddressHBox.setVisible(false);
                billingAddressField.clear();
            }
        });
        setUpDeviceTypeTableView();
        setUpCustomerTableView();
    }
    private void setUpDeviceTypeTableView() {
        deviceTypeTableView.getItems().setAll(modelManager.getDeviceTypeModel().getDeviceTypes());
        deviceTypeIdColumn.setCellValueFactory(new PropertyValueFactory<DeviceType, Integer >("id"));
        deviceTypeNameColumn.setCellValueFactory(new PropertyValueFactory < DeviceType, String > ("name"));

    }

    private void handleViewSwitch(boolean technicianInfoOverview, boolean customerInfoOverview, boolean installationInfoOverview, boolean installationPhotoOverview) {
        techPane.setVisible(technicianInfoOverview);
        customerInfoPane.setVisible(customerInfoOverview);
        installationInfoPane.setVisible(installationInfoOverview);
        installationPhotoPane.setVisible(installationPhotoOverview);

    }
    private void setUpCustomerTableView() {
        customerTableView.getItems().setAll(modelManager.getCustomerModel().getCustomers());
        customerIdColumnS.setCellValueFactory(new PropertyValueFactory <Customer, Integer > ("id"));
        customerNameColumnS.setCellValueFactory(new PropertyValueFactory < Customer, String > ("name"));
        customerEmailColumnS.setCellValueFactory(new PropertyValueFactory < Customer, String > ("email"));
        customerFirstAddressColumnS.setCellValueFactory(new PropertyValueFactory < Customer, String > ("address"));
        customerSecondAddressColumnS.setCellValueFactory(new PropertyValueFactory < Customer, String > ("billingAddress"));
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
    private void addDeviceToInstallation(ActionEvent event) {
        selectedDeviceType = deviceTypeTableView.getSelectionModel().getSelectedItem();
        openNewWindow("/easv_2nd_term_exam/gui/views/projectManager/UpdateDevicesView.fxml", "Add Device To Installation");
        ControllerManager.getInstance().getUpdateDevicesController().getDeviceTypeIdField().setText(String.valueOf(selectedDeviceType.getId()));
        ControllerManager.getInstance().getUpdateDevicesController().getDeviceTypeNameField().setText(selectedDeviceType.getName());}

    public DatePicker getExpireDatePicker() {
        return expireDatePicker;
    }

    @FXML
    void addNewCustomer(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void nextToCustomerInfo(ActionEvent event) {
        handleViewSwitch(false, true, false, false);

    }

    @FXML
    void nextToInstallationInfo(ActionEvent event) {
        handleViewSwitch(false, false, true, false);


    }

    @FXML
    void nextToInstallationPhotos(ActionEvent event) {
        handleViewSwitch(false, false, false, true);

    }

    @FXML
    void openDrawingLayout(ActionEvent event) {

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

    @FXML
    void removeAllPhotos(ActionEvent event) {
        FileUtility.deleteFilesFromDirectory(createPackagePath());
        diagramPathLabel.setText(null);
        uploadedPictureLabel.setText(null);

    }
    private String createPackagePath() {
        String projectPath = System.getProperty("user.dir") + File.separator + "src";
        String sanitizedUserName = loggedUser.getName().replace(" ", "_");
        return projectPath + File.separator + "easv_2nd_term_exam" + File.separator + "installation_pictures" + File.separator + sanitizedUserName.toLowerCase() + File.separator;
    }

    @FXML
    void submitUpdating(ActionEvent event) {

        String customerName = customerNameField.getText();
        String customerEmail = customerEmailField.getText();
        String customerAddress = customerAddressField.getText();
        CustomerType type = customerTypeBox.getValue();
        Customer newCustomer = new Customer(customerName, customerAddress, customerEmail, type);

        int technicianId = Integer.parseInt(techIdField.getText());
        String installationDescription = descriptionArea.getText();
        LocalDate createdDate = datePicker.getValue();
        LocalDate expiryDate = expireDatePicker.getValue();
        InstallationType installationType = installationTypeBox.getValue();
        int customerId = Integer.parseInt(customerIdField.getText());
        int installationId = Integer.parseInt(installationIdLabel.getText());

        Report tobeUpdated = new Report();
        tobeUpdated.setCustomerName(customerName);
        tobeUpdated.setBillingAddress(billingAddressField.getText());
        tobeUpdated.setCustomerAddress(customerAddress);
        tobeUpdated.setCustomerEmail(customerEmail);
        tobeUpdated.setCustomerType(String.valueOf(type));
        tobeUpdated.setTechnicianId(technicianId);
        tobeUpdated.setInstallationId(installationId);
        tobeUpdated.setInstallationTypeId(installationType.getId());

        tobeUpdated.setDescription(installationDescription);
        tobeUpdated.setCustomerId(customerId);
        tobeUpdated.setCreatedDate(createdDate);
        tobeUpdated.setExpiryDate(expiryDate);

        try {
            modelManager.getReportModel().updateReport(tobeUpdated);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Device d: devices) {
            d.setInstallationId(installationId);
        }

        try {
            modelManager.getDevicesModel().createDevices(devices);
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }

    }

    @FXML
    void selectCustomer(ActionEvent event) {

    }

    public TextField getCustomerNameField() {
        return customerNameField;
    }

    public void setCustomerNameField(TextField customerNameField) {
        this.customerNameField = customerNameField;
    }

    public TextField getCustomerAddressField() {
        return customerAddressField;
    }

    public TextField getCustomerIdField() {
        return customerIdField;
    }

    public TextField getBillingAddressField() {
        return billingAddressField;
    }

    public void setBillingAddressField(TextField billingAddressField) {
        this.billingAddressField = billingAddressField;
    }
    /*public TextField getInstallationIdField() {
        return installationIdField;
    }*/

    public void setCustomerAddressField(TextField customerAddressField) {
        this.customerAddressField = customerAddressField;
    }

    public TextField getCustomerEmailField() {
        return customerEmailField;
    }

    public void setCustomerEmailField(TextField customerEmailField) {
        this.customerEmailField = customerEmailField;
    }

    public ComboBox<CustomerType> getCustomerTypeBox() {
        return customerTypeBox;
    }

    public void setCustomerTypeBox(ComboBox<CustomerType> customerTypeBox) {
        this.customerTypeBox = customerTypeBox;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

    public TextArea getDescriptionArea() {
        return descriptionArea;
    }

    public void setDescriptionArea(TextArea descriptionArea) {
        this.descriptionArea = descriptionArea;
    }

    public Label getDiagramPathLabel() {
        return diagramPathLabel;
    }

    public void setDiagramPathLabel(Label diagramPathLabel) {
        this.diagramPathLabel = diagramPathLabel;
    }

    public Label getUploadedPictureLabel() {
        return uploadedPictureLabel;
    }

    public void setUploadedPictureLabel(Label uploadedPictureLabel) {
        this.uploadedPictureLabel = uploadedPictureLabel;
    }

    public ImageView getDrawingView() {
        return drawingView;
    }

    public void setDrawingView(ImageView drawingView) {
        this.drawingView = drawingView;
    }


    public ComboBox<InstallationType> getInstallationTypeBox() {
        return installationTypeBox;
    }

    public void setInstallationTypeBox(ComboBox<InstallationType> installationTypeBox) {
        this.installationTypeBox = installationTypeBox;
    }

    public TextField getTechNameField() {
        return techNameField;
    }

    public void setTechNameField(TextField techNameField) {
        this.techNameField = techNameField;
    }

    public TextField getTechIdField() {
        return techIdField;
    }

    public void setTechIdField(TextField techIdField) {
        this.techIdField = techIdField;
    }

    public Label getInstallationIdLabel() {
        return installationIdLabel;
    }
    public ObservableList <Device> getDevices() {
        return devices;
    }

    public void setInstallationIdLabel(Label installationIdLabel) {
        this.installationIdLabel = installationIdLabel;
    }
}
