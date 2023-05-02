package easv_2nd_term_exam.gui.controllers.admin;

import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.enums.UserRole;
import easv_2nd_term_exam.gui.models.AdminModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private TabPane technicianTabPane;

    @FXML
    private TableColumn<User, String> uUsernameColumn;

    @FXML
    private TextField uUsernameField;

    @FXML
    private TableColumn<User, String> userEmailColumn;

    @FXML
    private TextField userEmailField;

    @FXML
    private TextField userFullnameField;

    @FXML
    private TableColumn<User, Integer> userIdColumn;
    @FXML
    private TableView<User> userTableView;

    @FXML
    private Label userLabel;

    @FXML
    private TableColumn<User, String> userNameColumn;

    @FXML
    private TextField userPasswordField;

    @FXML
    private TableColumn<User, UserRole> userRoleColumn;

    @FXML
    private ComboBox<UserRole> userTypeBox;

    @FXML
    private Tab allUsersPane;

    @FXML
    private Tab historyPane;

    @FXML
    private Tab newUserPane;

    private AdminModel adminModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userTypeBox.getItems().setAll(UserRole.values());
        newUserPane.setDisable(true);
        historyPane.setDisable(true);
        newUserPane.setDisable(false);
        adminModel = new AdminModel();
    }
    @FXML
    void addNewUser(ActionEvent event) {

    }

    @FXML
    void cancelAdding(ActionEvent event) {

    }

    @FXML
    void deleteUser(ActionEvent event) {

    }

    @FXML
    void handleLogout(ActionEvent event) {

    }

    @FXML
    void showAllUsers(ActionEvent event) {


    }

    @FXML
    void showHistory(ActionEvent event) {

    }

    @FXML
    void submitAdding(ActionEvent event) {

    }

    @FXML
    void updateUser(ActionEvent event) {

    }


}
