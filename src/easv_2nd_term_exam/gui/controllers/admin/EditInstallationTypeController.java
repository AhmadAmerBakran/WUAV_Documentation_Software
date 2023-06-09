package easv_2nd_term_exam.gui.controllers.admin;

import easv_2nd_term_exam.be.InstallationType;
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

public class EditInstallationTypeController implements Initializable {

    @FXML
    private TextField installationTypeFieldE;

    ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;

    private InstallationType selectedInstallationType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        ControllerManager.getInstance().setEditInstallationTypeController(this);
    }

    @FXML
    private void cancelEditing(ActionEvent event) {
        closeStage(event);
    }

    @FXML
    private void submitEditing(ActionEvent event) {
        String installationTypeName = installationTypeFieldE.getText();
        if (!ValidationUtility.isNotEmpty(installationTypeFieldE) || !ValidationUtility.isValidName(installationTypeFieldE)) {
            DialogUtility.showInformationDialog("Invalid Installation Type name. Please enter a valid name.");
            return;
        }
        if (selectedInstallationType != null) {
            selectedInstallationType.setName(installationTypeName);
            try {
                modelManager.getInstallationTypeModel().updateInstallationType(selectedInstallationType);
                DialogUtility.showInformationDialog("Installation Type updated successfully.");
                ControllerManager.getInstance().getAdminDashboardController().setUpInstallationTypeTableView();
                closeStage(event);
            } catch (Exception e) {
                DialogUtility.showExceptionDialog(new RuntimeException("An unexpected error occurred.", e));
            }
        }
    }



    public void fillTextFieldWithInstallationTypeData(InstallationType installationType) {
        selectedInstallationType = installationType;
        installationTypeFieldE.setText(installationType.getName());
    }
    private void closeStage(ActionEvent event) {
        Stage stage = (Stage) installationTypeFieldE.getScene().getWindow();
        stage.close();
    }
}
