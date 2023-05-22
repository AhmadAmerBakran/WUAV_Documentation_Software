package easv_2nd_term_exam.gui.controllers.admin;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.enums.UserRole;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import easv_2nd_term_exam.util.DialogUtility;
import easv_2nd_term_exam.util.ValidationUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUserController implements Initializable {

    @FXML
    private TextField uUsernameFieldE, userEmailFieldE, userFullnameFieldE, userPasswordFieldE;

    @FXML
    private ComboBox<UserRole> userTypeComboBox;

    private AdminDashboardController controller;
    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setEditUserController(this);
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        controller = ControllerManager.getInstance().getAdminDashboardController();
        userTypeComboBox.getItems().setAll(UserRole.values());
        userTypeComboBox.setValue(controller.getSelectedUser().getRole());
        uUsernameFieldE.setText(controller.getSelectedUser().getUsername());
        userEmailFieldE.setText(controller.getSelectedUser().getEmail());
        userFullnameFieldE.setText(controller.getSelectedUser().getName());
    }
    @FXML
    void cancelEditing(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void submitEditing(ActionEvent event)  {
        if (!ValidationUtility.isNotEmpty(userFullnameFieldE)) {
            DialogUtility.showInformationDialog("Please enter a name.");
            return;
        }
        if (!ValidationUtility.isNotEmpty(userEmailFieldE)) {
            DialogUtility.showInformationDialog("Please enter an email.");
            return;
        }
        if (!ValidationUtility.isValidEmail(userEmailFieldE)) {
            DialogUtility.showInformationDialog("Please enter a valid email.");
            return;
        }
        if (!ValidationUtility.isNotEmpty(uUsernameFieldE)) {
            DialogUtility.showInformationDialog("Please enter a username.");
            return;
        }
        if (!ValidationUtility.isNotEmpty(userPasswordFieldE)) {
            DialogUtility.showInformationDialog("Please enter a password.");
            return;
        }
        if (!ValidationUtility.isComboBoxNotEmpty(userTypeComboBox)) {
            DialogUtility.showInformationDialog("Please select a user role.");
            return;
        }

        String name = userFullnameFieldE.getText();
        String email = userEmailFieldE.getText();
        String username = uUsernameFieldE.getText();
        String password = userPasswordFieldE.getText();
        UserRole role = userTypeComboBox.getValue();

        User updatedUser = null;
        switch (role) {
            case ADMIN:
                updatedUser = new Admin(name, email, username, password);
                updatedUser.setRole(UserRole.ADMIN);
                updatedUser.setId(controller.getSelectedUser().getId());
                break;
            case TECHNICIAN:
                updatedUser = new Technician(name, email, username, password);
                updatedUser.setRole(UserRole.TECHNICIAN);
                updatedUser.setId(controller.getSelectedUser().getId());
                break;
            case PROJECT_MANAGER:
                updatedUser = new ProjectManager(name, email, username, password);
                updatedUser.setRole(UserRole.PROJECT_MANAGER);
                updatedUser.setId(controller.getSelectedUser().getId());
                break;
            case SALES_PERSON:
                updatedUser = new SalesPerson(name, email, username, password);
                updatedUser.setRole(UserRole.SALES_PERSON);
                updatedUser.setId(controller.getSelectedUser().getId());
                break;
        }

        // Add the new user to the model
        if (updatedUser != null) {
            modelManager.getAdminModel().updateUser(updatedUser);
            DialogUtility.showInformationDialog("User Updated successfully!");
            ControllerManager.getInstance().getAdminDashboardController().showAllUsers(event);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            DialogUtility.showExceptionDialog(new IllegalArgumentException("Something went wrong"));
        }

    }



}
