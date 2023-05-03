package easv_2nd_term_exam.gui.controllers.technician;

import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.enums.InstallationType;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
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
    private Label userLabel, diagramPathLabel, uploadedPictureLabel;

    private User loggedUser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        installationTypeBox.getItems().setAll(InstallationType.values());
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
            uploadedPictureLabel.setText("/src/easv_2nd_term_exam/installation_pictures/" + loggedUser.getName().replace(" ", "_").toLowerCase() + "/" + targetFile.getName());
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