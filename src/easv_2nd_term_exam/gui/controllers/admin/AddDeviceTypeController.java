package easv_2nd_term_exam.gui.controllers.admin;

import easv_2nd_term_exam.be.DeviceType;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import easv_2nd_term_exam.util.DialogUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddDeviceTypeController implements Initializable {

    @FXML
    private TextField deviceNameField;

    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        ControllerManager.getInstance().setAddDeviceTypeController(this);
    }

    @FXML
    private void cancelAdding(ActionEvent event) {
        closeStage(event);

    }

    @FXML
    private void submitAdding(ActionEvent event) {

        String deviceTypeName = deviceNameField.getText();
        DeviceType deviceType = new DeviceType();
        deviceType.setName(deviceTypeName);
        try {
            modelManager.getDeviceTypeModel().createDeviceType(deviceType);
            DialogUtility.showInformationDialog("Device Type added successfully.");
            closeStage(event);
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(new RuntimeException("An unexpected error occurred.", e));
        }
    }

    private void closeStage(ActionEvent event) {
        Stage stage = (Stage) deviceNameField.getScene().getWindow();
        stage.close();
    }
}
