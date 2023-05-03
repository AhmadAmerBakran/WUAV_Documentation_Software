package easv_2nd_term_exam.gui.controllers.technician;

import easv_2nd_term_exam.be.Customer;
import easv_2nd_term_exam.be.Installation;
import easv_2nd_term_exam.be.Picture;
import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.enums.CustomerType;
import easv_2nd_term_exam.enums.InstallationType;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TechnicianDashboardController implements Initializable {

    @FXML
    private TextField customerAddressField, customerEmailField, customerNameField, devicePasswordField, deviceUsernameField, techEmailField, techIdField, techNameField;

    @FXML
    private TabPane technicianTabPane;
    @FXML
    private Pane technicianPane;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<InstallationType> installationTypeBox;
    @FXML
    private ComboBox<CustomerType> customerTypeBox;
    @FXML
    private Label userLabel, diagramPathLabel, uploadedPictureLabel;
    @FXML
    private TextArea descriptionArea;

    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;


    private User loggedUser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        installationTypeBox.getItems().setAll(InstallationType.values());
        customerTypeBox.getItems().setAll(CustomerType.values());
        ControllerManager.getInstance().setTechnicianDashboardController(this);
        switchTchPane(false, true);
        loggedUser = ControllerManager.getInstance().getLoginViewController().getLoggedUser();
        datePicker.setValue(LocalDate.now());
        fillTechnicianData();

    }

    private void switchTchPane(boolean dashboard, boolean startBoard)
    {
        technicianPane.setVisible(startBoard);
        technicianTabPane.setVisible(dashboard);

    }

    private void fillTechnicianData()
    {
        userLabel.setText(loggedUser.getName());
        techIdField.setText(String.valueOf(loggedUser.getId()));
        techNameField.setText(loggedUser.getName());
        techEmailField.setText(loggedUser.getEmail());
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

    private String createPackagePath() {
        String projectPath = System.getProperty("user.dir") + File.separator + "src";
        String sanitizedUserName = loggedUser.getName().replace(" ", "_");
        return projectPath + File.separator + "easv_2nd_term_exam" + File.separator + "installation_pictures" + File.separator + sanitizedUserName.toLowerCase() + File.separator;
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




    public Label getDiagramPathLabel() {
        return diagramPathLabel;
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void saveReport(ActionEvent event) {
        String customerName = customerNameField.getText();
        String customerEmail = customerEmailField.getText();
        String customerAddress = customerAddressField.getText();
        CustomerType type = customerTypeBox.getValue();
        Customer newCustomer = new Customer(customerName, customerAddress, customerEmail, type);
        try {
            modelManager.getCustomerModel().createCustomer(newCustomer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int customerId = newCustomer.getId();
        int technicianId = Integer.parseInt(techIdField.getText());
        String deviceUsername = deviceUsernameField.getText();
        String devicePassword = devicePasswordField.getText();
        String installationDescription = descriptionArea.getText();
        InstallationType installationType = installationTypeBox.getValue();
        Installation newInstallation = new Installation(customerId, technicianId, deviceUsername, devicePassword, installationDescription, installationType);
        try {
            modelManager.getInstallationModel().createInstallation(newInstallation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int installationId = newInstallation.getId();
        Image diagramImage = new Image(diagramPathLabel.getText());
        Image uploadedImage = new Image(uploadedPictureLabel.getText());
        List<Picture> pictures = new ArrayList<>();

        byte[] diagramImageData = imageToByteArray(diagramImage);
        byte[] uploadedImageData = imageToByteArray(uploadedImage);

        if (diagramImageData != null) {
            Picture diagramPicture = new Picture(installationId, "Diagram Image", diagramImageData);
            pictures.add(diagramPicture);
        }

        if (uploadedImageData != null) {
            Picture uploadedPicture = new Picture(installationId, "Uploaded Image", uploadedImageData);
            pictures.add(uploadedPicture);
        }
        try {
            modelManager.getPictureModel().createPictures(pictures);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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


    @FXML
    private void createNewReport(ActionEvent event)
    {
        switchTchPane(true, false);

    }

    @FXML
    private void showMyReports(ActionEvent event) {
        switchTchPane(false, true);
    }
}