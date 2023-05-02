package easv_2nd_term_exam.gui.controllers.technician;

import easv_2nd_term_exam.gui.controllers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TechnicianDashboardController implements Initializable {

    @FXML
    private TextField customerAddressField, customerEmailField, customerNameField, devicePasswordField, deviceUsernameField, techEmailField, techIdField, techNameField;


    @FXML
    private DatePicker datePicker;

    @FXML
    private ImageView drawingView;

    @FXML
    private ComboBox<?> installationTypeBox;
    @FXML
    private Label userLabel;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setTechnicianDashboardController(this);
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

    public TextField getTechEmailField() {
        return techEmailField;
    }

    public TextField getTechIdField() {
        return techIdField;
    }

    public TextField getTechNameField() {
        return techNameField;
    }

    public Label getUserLabel() {
        return userLabel;
    }

    public ImageView getDrawingView() {
        return drawingView;
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void saveReport(ActionEvent event) {
    }
}