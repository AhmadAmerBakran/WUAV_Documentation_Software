package easv_2nd_term_exam.gui.controllers.projectManager;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.enums.CustomerType;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import easv_2nd_term_exam.util.DialogUtility;
import easv_2nd_term_exam.util.PictureUtility;
import easv_2nd_term_exam.util.ValidationUtility;
import javafx.animation.FadeTransition;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UpdateReportViewController implements Initializable {

    @FXML
    private TextField customerIdField, customerNameField, customerEmailField, customerAddressField, billingAddressField;

    @FXML
    private HBox billingAddressHBox;

    @FXML
    private TableColumn < Customer, String > customerEmailColumnS;

    @FXML
    private TableColumn < Customer, String > customerFirstAddressColumnS;

    @FXML
    private TableColumn < Customer, Integer > customerIdColumnS;

    @FXML
    private TableColumn < Customer, String > customerNameColumnS;

    @FXML
    private TableColumn < Customer, String > customerSecondAddressColumnS;

    @FXML
    private TableView < Customer > customerTableView;

    @FXML
    private ComboBox < CustomerType > customerTypeBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TableView<Device> installationDevicesTableView;

    @FXML
    private TableColumn<Device, Integer> deviceIdColumn;

    @FXML
    private TableColumn<Device, String> deviceNameColumn, deviceUsernameColumn, devicePasswordColumn;


    @FXML
    private TableColumn < DeviceType, Integer > deviceTypeIdColumn;

    @FXML
    private TableColumn < DeviceType, String > deviceTypeNameColumn;

    @FXML
    private TableView < DeviceType > deviceTypeTableView;

    @FXML
    private ImageView installationPictureView;

    @FXML
    private DatePicker expireDatePicker;

    @FXML
    private BorderPane techPane, customerInfoPane, installationInfoPane, installationPhotoPane;

    @FXML
    private ComboBox < InstallationType > installationTypeBox;

    @FXML
    private VBox newCustomerVBox;

    @FXML
    private Label installationIdLabel;

    @FXML
    private TextField techIdField;

    @FXML
    private TextField techNameField;

    @FXML
    private AnchorPane picturesPane;

    private DeviceType selectedDeviceType;

    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;
    private User loggedUser;
    private Device selectedDevice;
    private ObservableList < Device > devices;
    private List < Image > images = new ArrayList < > ();
    List < Picture > pictures = new ArrayList < > ();
    private int currentIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        installationPictureView.fitWidthProperty().bind(picturesPane.widthProperty());
        installationPictureView.fitHeightProperty().bind(picturesPane.heightProperty().subtract(100));
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
        deviceTypeIdColumn.setCellValueFactory(new PropertyValueFactory < DeviceType, Integer > ("id"));
        deviceTypeNameColumn.setCellValueFactory(new PropertyValueFactory < DeviceType, String > ("name"));

    }

    public void setUpInstallationDeviceTableView() {
        installationDevicesTableView.getItems().setAll(modelManager.getDevicesModel().getDevicesByInstallationId(Integer.parseInt(installationIdLabel.getText())));
        deviceIdColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("id"));
        deviceNameColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
        deviceUsernameColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("username"));
        devicePasswordColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("password"));

    }

    private void handleViewSwitch(boolean technicianInfoOverview, boolean customerInfoOverview, boolean installationInfoOverview, boolean installationPhotoOverview) {
        techPane.setVisible(technicianInfoOverview);
        customerInfoPane.setVisible(customerInfoOverview);
        installationInfoPane.setVisible(installationInfoOverview);
        installationPhotoPane.setVisible(installationPhotoOverview);

    }
    private void setUpCustomerTableView() {
        try {
            customerTableView.getItems().setAll(modelManager.getCustomerModel().getCustomers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        customerIdColumnS.setCellValueFactory(new PropertyValueFactory < Customer, Integer > ("id"));
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
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void addDeviceToInstallation(ActionEvent event) {
        selectedDeviceType = deviceTypeTableView.getSelectionModel().getSelectedItem();

        if (selectedDeviceType == null) {
            DialogUtility.showInformationDialog("Please select a device type before proceeding.");
            return;
        }

        try {
            openNewWindow("/easv_2nd_term_exam/gui/views/projectManager/AddDevicesToInstallationView.fxml", "Add Device To Installation");

            ControllerManager.getInstance().getAddDevicesToInstallationController().getDeviceTypeIdField().setText(String.valueOf(selectedDeviceType.getId()));
            ControllerManager.getInstance().getAddDevicesToInstallationController().getDeviceTypeNameField().setText(selectedDeviceType.getName());

        } catch (Exception ex) {
            DialogUtility.showExceptionDialog(ex);
        }
    }


    public DatePicker getExpireDatePicker() {
        return expireDatePicker;
    }


    @FXML
    private void cancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void nextToCustomerInfo(ActionEvent event) {

        if (!ValidationUtility.isNotEmpty(techIdField)) {
            DialogUtility.showInformationDialog("Technician ID is required.");
            return;
        }

        if (!ValidationUtility.isNotEmpty(techNameField)) {
            DialogUtility.showInformationDialog("Technician Name is required.");
            return;
        }
        handleViewSwitch(false, true, false, false);
    }

    @FXML
    private void nextToInstallationInfo(ActionEvent event) {
        if (!ValidationUtility.isNotEmpty(customerAddressField) &&
                !ValidationUtility.isNotEmpty(customerEmailField) &&
                !ValidationUtility.isNotEmpty(customerNameField) &&
                !ValidationUtility.isNotEmpty(billingAddressField) &&
                !ValidationUtility.isComboBoxNotEmpty(customerTypeBox)) {
            DialogUtility.showInformationDialog("You must select a customer or create a new one before proceeding.");
            return;
        }

        if (!ValidationUtility.isNotEmpty(customerAddressField)) {
            DialogUtility.showInformationDialog("Customer Address is required.");
            return;
        }

        if (!ValidationUtility.isValidDanishAddress(customerAddressField)) {
            DialogUtility.showInformationDialog("Customer Address is not valid.");
            return;
        }

        if (!ValidationUtility.isNotEmpty(customerEmailField)) {
            DialogUtility.showInformationDialog("Customer Email is required.");
            return;
        }

        if (!ValidationUtility.isValidEmail(customerEmailField)) {
            DialogUtility.showInformationDialog("Customer Email is not valid.");
            return;
        }

        if (!ValidationUtility.isNotEmpty(customerNameField)) {
            DialogUtility.showInformationDialog("Customer Name is required.");
            return;
        }

        if (!ValidationUtility.isValidName(customerNameField)) {
            DialogUtility.showInformationDialog("Customer Name is not valid.");
            return;
        }

        if (!ValidationUtility.isComboBoxNotEmpty(customerTypeBox)) {
            DialogUtility.showInformationDialog("Customer Type is required.");
            return;
        }

        if (customerTypeBox.getValue() == CustomerType.B2B) {
            if (!ValidationUtility.isNotEmpty(billingAddressField)) {
                DialogUtility.showInformationDialog("Billing Address is required for B2B customers.");
                return;
            }
        }
        handleViewSwitch(false, false, true, false);
        setUpInstallationDeviceTableView();
    }

    @FXML
    private void nextToInstallationPhotos(ActionEvent event) {
        if (!ValidationUtility.isComboBoxNotEmpty(installationTypeBox)) {
            DialogUtility.showInformationDialog("Installation Type Box is empty. Please select an Installation Type.");
            return;
        }

        if (installationDevicesTableView.getItems().isEmpty()) {
            DialogUtility.showInformationDialog("Devices List is empty. Please add a Device.");
            return;
        }

        if (!ValidationUtility.isTextAreaNotEmpty(descriptionArea)) {
            DialogUtility.showInformationDialog("Description Area is empty. Please fill in the Description.");
            return;
        }

        if (datePicker.getValue() == null) {
            DialogUtility.showInformationDialog("Please select a Creating Date.");
            return;
        }

        if (expireDatePicker.getValue() == null) {
            DialogUtility.showInformationDialog("Expiration Date Picker is empty. Please select an Expiration Date.");
            return;
        }

        handleViewSwitch(false, false, false, true);
        showInstallationImages();
    }

    @FXML
    void submitUpdating(ActionEvent event) {
        try {
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

            modelManager.getReportModel().updateReport(tobeUpdated);

            DialogUtility.showInformationDialog("Update successful.");
            ControllerManager.getInstance().getProjectManagerDashboardController().setUpReportTableView();
            ControllerManager.getInstance().getProjectManagerDashboardController().setUpExpiringReportsTableView();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }
    }

    public TextField getCustomerNameField() {
        return customerNameField;
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

    public TextField getCustomerEmailField() {
        return customerEmailField;
    }

    public ComboBox < CustomerType > getCustomerTypeBox() {
        return customerTypeBox;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public TextArea getDescriptionArea() {
        return descriptionArea;
    }

    public ComboBox < InstallationType > getInstallationTypeBox() {
        return installationTypeBox;
    }

    public TextField getTechNameField() {
        return techNameField;
    }

    public TextField getTechIdField() {
        return techIdField;
    }

    public Label getInstallationIdLabel() {
        return installationIdLabel;
    }
    public ObservableList < Device > getDevices() {
        return devices;
    }

    @FXML
    private void deleteCurrentImage(ActionEvent event) {
        try {
            modelManager.getPictureModel().deletePicture(pictures.get(currentIndex).getId());
            pictures = modelManager.getPictureModel().getPicturesByInstallationId(Integer.parseInt(installationIdLabel.getText()));
            images = pictures.stream().map(picture -> new Image(new ByteArrayInputStream(picture.getImageData()))).collect(Collectors.toList());

            if (pictures.isEmpty()) {
                currentIndex = -1;
            } else if (currentIndex >= pictures.size()) {
                currentIndex--;
            }
            if (currentIndex >= 0) {
                displayImage(currentIndex);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void uploadMoreImages(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp"));

        List < File > selectedFiles = fileChooser.showOpenMultipleDialog(((Node) event.getSource()).getScene().getWindow());
        List < Picture > newPictures = new ArrayList < > ();

        if (selectedFiles != null) {
            for (File selectedFile: selectedFiles) {
                try {
                    Image image = new Image(new FileInputStream(selectedFile));
                    byte[] imageData = PictureUtility.imageToByteArray(image);

                    if (imageData != null) {
                        Picture picture = new Picture(Integer.parseInt(installationIdLabel.getText()), "Uploaded Image", imageData);
                        newPictures.add(picture);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            showInstallationImages();
        }

        try {
            modelManager.getPictureModel().createPictures(newPictures);
            pictures = modelManager.getPictureModel().getPicturesByInstallationId(Integer.parseInt(installationIdLabel.getText()));
            images = pictures.stream().map(picture -> new Image(new ByteArrayInputStream(picture.getImageData()))).collect(Collectors.toList());
            currentIndex = images.size() - 1;
            displayImage(currentIndex);
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }
    }

    private void showInstallationImages() {
        pictures.clear();
        images.clear();
        try {
            pictures = modelManager.getPictureModel().getPicturesByInstallationId(Integer.parseInt(installationIdLabel.getText()));
            for (Picture picture: pictures) {
                Image image = PictureUtility.byteArrayToImage(picture.getImageData());
                images.add(image);
            }
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }
        displayImage(currentIndex);
    }

    @FXML
    private void previousImage() {
        if (currentIndex > 0) {
            currentIndex--;
            animateImageChange();
        }
    }
    @FXML
    private void nextImage() {
        if (currentIndex < images.size() - 1) {
            currentIndex++;
            animateImageChange();
        }
    }
    private void displayImage(int index) {
        installationPictureView.setImage(images.get(index));
    }
    private void animateImageChange() {
        FadeTransition ft = new FadeTransition(Duration.millis(500), installationPictureView);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.setOnFinished(event -> displayImage(currentIndex));
        ft.play();
    }

    @FXML
    private void updateInstallationDevice(ActionEvent event) {
        selectedDevice = installationDevicesTableView.getSelectionModel().getSelectedItem();
        if (selectedDevice == null) {
            DialogUtility.showInformationDialog("Please select a device before proceeding.");
            return;
        }
        try {
            openNewWindow("/easv_2nd_term_exam/gui/views/projectManager/UpdateInstallationDeviceView.fxml", "Update Installation Devices");

            ControllerManager.getInstance().getUpdateInstallationDeviceController().getDeviceIdFieldE().setText(String.valueOf(selectedDevice.getId()));
            ControllerManager.getInstance().getUpdateInstallationDeviceController().getDeviceNameFieldE().setText(selectedDevice.getName());
            ControllerManager.getInstance().getUpdateInstallationDeviceController().getDeviceUsernameFieldE().setText(selectedDevice.getUsername());

        } catch (Exception ex) {
            DialogUtility.showExceptionDialog(ex);
        }
    }


    @FXML
    private void deleteInstallationDevice(ActionEvent event) {
        selectedDevice = installationDevicesTableView.getSelectionModel().getSelectedItem();
        if (selectedDevice == null) {
            DialogUtility.showInformationDialog("Please select a device before proceeding.");
            return;
        }

        if (DialogUtility.showConfirmationDialog("Are you sure you want to delete this device?")) {
            try {
                modelManager.getDevicesModel().deleteDevice(selectedDevice.getId());
                setUpInstallationDeviceTableView();
                DialogUtility.showInformationDialog("Device deleted successfully.");
            } catch (Exception e) {
                DialogUtility.showExceptionDialog(e);
            }
        }
    }



    @FXML
    private void backToInstallationInfo(ActionEvent event) {
        handleViewSwitch(false, false, true, false);
    }

    @FXML
    private void backToCustomerInfo(ActionEvent event) {
        handleViewSwitch(false, true, false, false);
    }

    @FXML
    private void backToTechnicianInfo(ActionEvent event) {
        handleViewSwitch(true, false, false, false);
    }
}