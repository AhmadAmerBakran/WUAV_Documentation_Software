package easv_2nd_term_exam.gui.controllers.admin;

import easv_2nd_term_exam.be.DeviceType;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import easv_2nd_term_exam.util.DialogUtility;
import easv_2nd_term_exam.util.ValidationUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDeviceTypeController implements Initializable {

    @FXML
    private TextField deviceTypeFieldE;

    ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;

    private DeviceType selectedDeviceType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        ControllerManager.getInstance().setEditDeviceTypeController(this);
    }

    @FXML
    private void cancelEditing(ActionEvent event) {
        closeStage(event);
    }

    @FXML
    private void submitEditing(ActionEvent event) {
        String deviceTypeName = deviceTypeFieldE.getText();

        if (!ValidationUtility.isNotEmpty(deviceTypeFieldE) || !ValidationUtility.isValidName(deviceTypeFieldE)) {
            DialogUtility.showInformationDialog("Invalid Device Type name. Please enter a valid name.");
            return;
        }

        if (selectedDeviceType != null) {
            selectedDeviceType.setName(deviceTypeName);
            try {
                modelManager.getDeviceTypeModel().updateDeviceType(selectedDeviceType);
                DialogUtility.showInformationDialog("Device Type successfully updated.");
                ControllerManager.getInstance().getAdminDashboardController().setUpDeviceTypeTableView();
                closeStage(event);
            } catch (Exception e) {
                DialogUtility.showExceptionDialog(new RuntimeException("An unexpected error occurred.", e));
            }
        }
    }


    public void fillTextFieldWithDeviceTypeData(DeviceType deviceType) {
        selectedDeviceType = deviceType;
        deviceTypeFieldE.setText(deviceType.getName());
    }
    private void closeStage(ActionEvent event) {
        Stage stage = (Stage) deviceTypeFieldE.getScene().getWindow();
        stage.close();
    }
}
