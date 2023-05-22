package easv_2nd_term_exam.gui.controllers.admin;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.enums.UserRole;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.AdminModel;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.util.DialogUtility;
import easv_2nd_term_exam.util.PdfReportGenerator;
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

public class AddUserController implements Initializable {

    @FXML
    private TextField uUsernameField;

    @FXML
    private TextField userEmailField;

    @FXML
    private TextField userFullnameField;

    @FXML
    private TextField userPasswordField;

    @FXML
    private ComboBox<UserRole> userTypeComboBox;

    private AdminModel adminModel;
    private ModelManager modelManager;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setAddUserController(this);
        adminModel = new AdminModel();
        userTypeComboBox.getItems().setAll(UserRole.values());

    }
    @FXML
    void cancelAdding(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    void submitAdding(ActionEvent event) {
        String name = userFullnameField.getText();
        String email = userEmailField.getText();
        String username = uUsernameField.getText();
        String password = userPasswordField.getText();
        UserRole role = userTypeComboBox.getValue();

        if (!ValidationUtility.isNotEmpty(userFullnameField)) {
            DialogUtility.showInformationDialog("Full name field cannot be empty!");
            return;
        }
        if (!ValidationUtility.isValidName(userFullnameField)) {
            DialogUtility.showInformationDialog("Please enter a valid name.");
            return;
        }
        if (!ValidationUtility.isNotEmpty(userEmailField) || !ValidationUtility.isValidEmail(userEmailField)) {
            DialogUtility.showInformationDialog("Please enter a valid email address.");
            return;
        }
        if (!ValidationUtility.isNotEmpty(uUsernameField)) {
            DialogUtility.showInformationDialog("Username field cannot be empty!");
            return;
        }
        if (!ValidationUtility.isNotEmpty(userPasswordField)) {
            DialogUtility.showInformationDialog("Password field cannot be empty!");
            return;
        }
        if (!ValidationUtility.isComboBoxNotEmpty(userTypeComboBox)) {
            DialogUtility.showInformationDialog("User role must be selected!");
            return;
        }

        User newUser = null;
        switch (role) {
            case ADMIN:
                newUser = new Admin(name, email, username, password);
                newUser.setRole(UserRole.ADMIN);
                break;
            case TECHNICIAN:
                newUser = new Technician(name, email, username, password);
                newUser.setRole(UserRole.TECHNICIAN);
                break;
            case PROJECT_MANAGER:
                newUser = new ProjectManager(name, email, username, password);
                newUser.setRole(UserRole.PROJECT_MANAGER);
                break;
            case SALES_PERSON:
                newUser = new SalesPerson(name, email, username, password);
                newUser.setRole(UserRole.SALES_PERSON);
                break;
        }

        if (newUser != null) {
            adminModel.addUser(newUser);
            DialogUtility.showInformationDialog("User added successfully!");
            ControllerManager.getInstance().getAdminDashboardController().showAllUsers(event);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            DialogUtility.showExceptionDialog(new IllegalArgumentException("Invalid user role selected."));
        }
    }



}
