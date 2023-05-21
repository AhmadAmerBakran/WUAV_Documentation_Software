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
        try {
            if (selectedDeviceType != null) {
                selectedDeviceType.setName(deviceTypeFieldE.getText());
                modelManager.getDeviceTypeModel().updateDeviceType(selectedDeviceType);
                ControllerManager.getInstance().getAdminDashboardController().setUpDeviceTypeTableView();
                DialogUtility.showInformationDialog("Device Type successfully updated.");
                closeStage(event);
            }
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
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
