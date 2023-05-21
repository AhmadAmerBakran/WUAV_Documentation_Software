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

public class UpdateInstallationDeviceController implements Initializable {

    @FXML
    private TextField devicePasswordFieldE, deviceUsernameFieldE, deviceIdFieldE, deviceNameFieldE;
    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setUpdateInstallationDeviceController(this);
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
    }

    public void cancelEditing(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void submitEditing(ActionEvent event) {
        try {
            int id = Integer.parseInt(deviceIdFieldE.getText());
            String name = deviceNameFieldE.getText();
            String username = deviceUsernameFieldE.getText();
            String password = devicePasswordFieldE.getText();
            Device updatedDevice = new Device();
            updatedDevice.setId(id);
            updatedDevice.setName(name);
            updatedDevice.setUsername(username);
            updatedDevice.setPassword(password);

            modelManager.getDevicesModel().updateDevice(updatedDevice);

            ControllerManager.getInstance().getUpdateReportViewController().setUpInstallationDeviceTableView();
            ControllerManager.getInstance().getProjectManagerDashboardController().setUpReportTableView();

            DialogUtility.showInformationDialog("Device update successful.");
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }
    }



    public TextField getDeviceUsernameFieldE() {
        return deviceUsernameFieldE;
    }

    public TextField getDeviceIdFieldE() {
        return deviceIdFieldE;
    }

    public TextField getDeviceNameFieldE() {
        return deviceNameFieldE;
    }
}
