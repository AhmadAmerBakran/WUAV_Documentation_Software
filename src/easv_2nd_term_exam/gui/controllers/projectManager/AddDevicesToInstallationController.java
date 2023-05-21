package easv_2nd_term_exam.gui.controllers.projectManager;

import easv_2nd_term_exam.be.Device;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import easv_2nd_term_exam.util.DialogUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddDevicesToInstallationController implements Initializable {

    @FXML
    private TextField devicePasswordField, deviceTypeIdField, deviceTypeNameField, deviceUsernameField;

    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setAddDevicesToInstallationController(this);
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();

    }
    @FXML
    private void cancelAdding(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void submitAdding(ActionEvent event) {
        try {
            String deviceTypeName = deviceTypeNameField.getText();
            int deviceTypeId = Integer.parseInt(deviceTypeIdField.getText());
            String username = deviceUsernameField.getText();
            String password = devicePasswordField.getText();
            int installationId = Integer.parseInt(ControllerManager.getInstance().getUpdateReportViewController().getInstallationIdLabel().getText());
            Device newDevice = new Device(deviceTypeName, deviceTypeId, username, password);
            newDevice.setInstallationId(installationId);
            modelManager.getDevicesModel().createDevice(newDevice);
            ControllerManager.getInstance().getUpdateReportViewController().setUpInstallationDeviceTableView();
            ControllerManager.getInstance().getProjectManagerDashboardController().setUpReportTableView();
            DialogUtility.showInformationDialog("Device successfully added.");
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }
    }

    public TextField getDeviceTypeIdField() {
        return deviceTypeIdField;
    }

    public TextField getDeviceTypeNameField() {
        return deviceTypeNameField;
    }
}