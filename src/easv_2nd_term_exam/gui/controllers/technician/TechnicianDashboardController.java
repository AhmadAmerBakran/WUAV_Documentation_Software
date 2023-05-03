package easv_2nd_term_exam.gui.controllers.technician;

import easv_2nd_term_exam.be.User;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private ImageView drawingView;

    @FXML
    private ComboBox<?> installationTypeBox;
    @FXML
    private Label userLabel;
    @FXML
    Button removeDiagramBtn;

    private User loggedUser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    private void removeDiagram(ActionEvent event) {
        drawingView.setImage(null);
        removeDiagramBtn.setVisible(false);
    }

    public Button getRemoveDiagramBtn() {
        return removeDiagramBtn;
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

    @FXML
    private void createNewReport(ActionEvent event)
    {
        switchTchPane(true, false);

    }

    @FXML
    private void showMyReports(ActionEvent event) {
        switchTchPane(true, false);
    }

}