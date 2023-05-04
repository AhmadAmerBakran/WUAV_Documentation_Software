package easv_2nd_term_exam.gui.controllers.projectManager;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.enums.CustomerType;
import easv_2nd_term_exam.enums.InstallationType;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateReportViewController implements Initializable {

    @FXML
    private TextField customerNameField,
            customerAddressField, customerEmailField, customerIdField;

    @FXML
    private ComboBox<CustomerType> customerTypeBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField deviceUsernameField, devicePasswordField, installationIdField;


    @FXML
    private Label diagramPathLabel, uploadedPictureLabel;

    @FXML
    private ImageView drawingView, uploadedImageView;

    @FXML
    private ComboBox<InstallationType> installationTypeBox;


    @FXML
    private TextField techNameField, techIdField;
    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;

    private User loggedUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setUpdateReportViewController(this);
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        loggedUser = ControllerManager.getInstance().getLoginViewController().getLoggedUser();
        customerTypeBox.getItems().setAll(CustomerType.values());
        installationTypeBox.getItems().setAll(InstallationType.values());
    }
    @FXML
    void cancelUpdating(ActionEvent event) {

    }

    private byte[] imageToByteArray(Image image) {
        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
    void openDrawingLayout(ActionEvent event) {

    }

    @FXML
    private void openFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp"));

        // Open the FileChooser dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            String packagePath = createPackagePath();
            File targetDirectory = new File(packagePath);
            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }

            // Check if the file with the same name already exists and avoid overwriting
            File targetFile = findUniqueOutputFile(packagePath, selectedFile.getName());
            copySelectedFile(selectedFile, targetFile);
        }
    }

    private File findUniqueOutputFile(String packagePath, String fileName) {
        int counter = 1;
        String baseFilename = fileName.substring(0, fileName.lastIndexOf("."));
        String extension = fileName.substring(fileName.lastIndexOf("."));
        File outputFile = new File(packagePath + baseFilename + extension);

        while (outputFile.exists()) {
            outputFile = new File(packagePath + baseFilename + "_" + counter + extension);
            counter++;
        }
        return outputFile;
    }

    private void copySelectedFile(File selectedFile, File targetFile) {
        try {
            Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            // Set the absolute path of the targetFile to the label
            uploadedPictureLabel.setText(targetFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createPackagePath() {
        String projectPath = System.getProperty("user.dir") + File.separator + "src";
        String sanitizedUserName = loggedUser.getName().replace(" ", "_");
        return projectPath + File.separator + "easv_2nd_term_exam" + File.separator + "installation_pictures" + File.separator + sanitizedUserName.toLowerCase() + File.separator;
    }

    @FXML
    void removeAllPhotos(ActionEvent event) {

    }

    @FXML
    void submitUpdating(ActionEvent event) {
        String customerName = customerNameField.getText();
        String customerEmail = customerEmailField.getText();
        String customerAddress = customerAddressField.getText();
        CustomerType type = customerTypeBox.getValue();
        Customer newCustomer = new Customer(customerName, customerAddress, customerEmail, type);
        int technicianId = Integer.parseInt(techIdField.getText());
        String deviceUsername = deviceUsernameField.getText();
        String devicePassword = devicePasswordField.getText();
        String installationDescription = descriptionArea.getText();
        InstallationType installationType = installationTypeBox.getValue();
        int customerId = Integer.parseInt(customerIdField.getText());
        int installationId = Integer.parseInt(installationIdField.getText());
       /* Image diagramImage = new Image(diagramPathLabel.getText());
        Image uploadedImage = new Image(uploadedPictureLabel.getText());
        List<Picture> pictures = new ArrayList< >();

        byte[] diagramImageData = imageToByteArray(diagramImage);
        byte[] uploadedImageData = imageToByteArray(uploadedImage);

        if (diagramImageData != null) {
            Picture diagramPicture = new Picture(installationId, "Diagram Image", diagramImageData);
            pictures.add(diagramPicture);
        }

        if (uploadedImageData != null) {
            Picture uploadedPicture = new Picture(installationId, "Uploaded Image", uploadedImageData);
            pictures.add(uploadedPicture);
        }*/

        Report tobeUpdated = new Report();
        tobeUpdated.setCustomerName(customerName);
        tobeUpdated.setCustomerAddress(customerAddress);
        tobeUpdated.setCustomerEmail(customerEmail);
        tobeUpdated.setCustomerType(String.valueOf(type));
        tobeUpdated.setTechnicianId(technicianId);
        tobeUpdated.setInstallationType(String.valueOf(installationType));
        tobeUpdated.setUsername(deviceUsername);
        tobeUpdated.setPassword(devicePassword);
        tobeUpdated.setDescription(installationDescription);
        tobeUpdated.setCustomerId(customerId);
        tobeUpdated.setInstallationId(installationId);
        modelManager.getReportModel().updateReport(tobeUpdated);
        deleteFilesFromDirectory(createPackagePath());



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

    public TextField getInstallationIdField() {
        return installationIdField;
    }

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

    public TextField getDeviceUsernameField() {
        return deviceUsernameField;
    }

    public void setDeviceUsernameField(TextField deviceUsernameField) {
        this.deviceUsernameField = deviceUsernameField;
    }

    public TextField getDevicePasswordField() {
        return devicePasswordField;
    }

    public void setDevicePasswordField(TextField devicePasswordField) {
        this.devicePasswordField = devicePasswordField;
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

    public ImageView getUploadedImageView() {
        return uploadedImageView;
    }

    public void setUploadedImageView(ImageView uploadedImageView) {
        this.uploadedImageView = uploadedImageView;
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

}
