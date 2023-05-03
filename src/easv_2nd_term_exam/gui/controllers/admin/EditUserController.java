package easv_2nd_term_exam.gui.controllers.admin;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.enums.UserRole;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.AdminModel;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import easv_2nd_term_exam.util.DialogUtil;
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
        String name = userFullnameFieldE.getText();
        String email = userEmailFieldE.getText();
        String username = uUsernameFieldE.getText();
        String password = userPasswordFieldE.getText();
        UserRole role = userTypeComboBox.getValue();

        User newUser = null;
        switch (role) {
            case ADMIN:
                newUser = new Admin(name, email, username, password);
                newUser.setRole(UserRole.ADMIN);
                newUser.setId(controller.getSelectedUser().getId());
                break;
            case TECHNICIAN:
                newUser = new Technician(name, email, username, password);
                newUser.setRole(UserRole.TECHNICIAN);
                newUser.setId(controller.getSelectedUser().getId());
                break;
            case PROJECT_MANAGER:
                newUser = new ProjectManager(name, email, username, password);
                newUser.setRole(UserRole.PROJECT_MANAGER);
                newUser.setId(controller.getSelectedUser().getId());
                break;
            case SALES_PERSON:
                newUser = new SalesPerson(name, email, username, password);
                newUser.setRole(UserRole.SALES_PERSON);
                newUser.setId(controller.getSelectedUser().getId());
                break;
        }

        // Add the new user to the model
        if (newUser != null) {
            modelManager.getAdminModel().updateUser(newUser);
            DialogUtil.showInformationDialog("User Updated successfully!");
            ControllerManager.getInstance().getAdminDashboardController().showAllUsers(event);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            DialogUtil.showExceptionDialog(new IllegalArgumentException("Something went wrong"));
        }

    }


}
