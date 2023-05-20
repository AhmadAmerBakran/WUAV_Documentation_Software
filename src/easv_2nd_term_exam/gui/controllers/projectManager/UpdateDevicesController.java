package easv_2nd_term_exam.gui.controllers.projectManager;

import easv_2nd_term_exam.be.Device;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateDevicesController implements Initializable {

    @FXML
    private TextField devicePasswordField, deviceTypeIdField, deviceTypeNameField, deviceUsernameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setUpdateDevicesController(this);

    }
    @FXML
    private void cancelAdding(ActionEvent event) {

    }

    @FXML
    private void submitAdding(ActionEvent event) {
        String deviceTypeName = deviceTypeNameField.getText();
        int deviceTypeId = Integer.parseInt(deviceTypeIdField.getText());
        String username = deviceUsernameField.getText();
        String password = devicePasswordField.getText();
        Device newDevice = new Device(deviceTypeName, deviceTypeId, username, password);
        ControllerManager.getInstance().getUpdateReportViewController().getDevices().add(newDevice);

    }

    public TextField getDeviceTypeIdField() {
        return deviceTypeIdField;
    }

    public TextField getDeviceTypeNameField() {
        return deviceTypeNameField;
    }
}