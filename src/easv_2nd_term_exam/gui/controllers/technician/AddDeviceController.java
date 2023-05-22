package easv_2nd_term_exam.gui.controllers.technician;

import easv_2nd_term_exam.be.Device;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.util.DialogUtility;
import easv_2nd_term_exam.util.ValidationUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddDeviceController implements Initializable {

    @FXML
    private TextField devicePasswordField, deviceTypeIdField, deviceTypeNameField, deviceUsernameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setAddDeviceController(this);

    }
    @FXML
    private void cancelAdding(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void submitAdding(ActionEvent event) {
        String deviceTypeName = deviceTypeNameField.getText();
        String deviceTypeIdStr = deviceTypeIdField.getText();

        if (!ValidationUtility.isNotEmpty(deviceTypeNameField) || deviceTypeIdStr.isEmpty()) {
            DialogUtility.showInformationDialog("Device Type Name is empty. Please fill in this field.");
            return;
        }

        int deviceTypeId;
        try {
            deviceTypeId = Integer.parseInt(deviceTypeIdStr);
        } catch (NumberFormatException e) {
            DialogUtility.showInformationDialog("Invalid device type ID. Please enter a valid number.");
            return;
        }

        String username = deviceUsernameField.getText();
        String password = devicePasswordField.getText();

        Device newDevice = new Device(deviceTypeName, deviceTypeId, username, password);
        ControllerManager.getInstance().getTechnicianDashboardController().getDevices().add(newDevice);
        DialogUtility.showInformationDialog("Device added successfully.");

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    public TextField getDeviceTypeIdField() {
        return deviceTypeIdField;
    }

    public TextField getDeviceTypeNameField() {
        return deviceTypeNameField;
    }
}
