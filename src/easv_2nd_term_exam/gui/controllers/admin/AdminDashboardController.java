package easv_2nd_term_exam.gui.controllers.admin;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.enums.UserRole;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.AdminModel;
import easv_2nd_term_exam.util.DialogUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Supplier;

public class AdminDashboardController implements Initializable {

    @FXML
    private TabPane adminTabPane;
    @FXML
    private TableView<User> userTableView, techTableView, managerTableView, salesPersonTableView;
    @FXML
    private TableColumn<User, Integer> userIdColumn, techIdColumn, manIdColumn, salesPersonsIdColumn;
    @FXML
    private TableColumn<User, String> uUsernameColumn, userEmailColumn, userNameColumn,
            techNameColumn, techEmailColumn, techUsernameColumn,
            manNameColumn, manEmailColumn, manUsernameColumn,
            salesPersonsNameColumn, salesPersonsEmailColumn, salesPersonsUsernameColumn;
    @FXML
    private TableColumn<User, UserRole> userRoleColumn, techRoleColumn, manRoleColumn, salesPersonsRoleColumn;
    @FXML
    private Label userLabel;
    @FXML
    private Tab allUsersTab, techTab, managerTab, salesPersonsTab;

    private AdminModel adminModel;
    private User selectedUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setAdminDashboardController(this);
        adminModel = new AdminModel();
        adminTabPane.getSelectionModel().select(allUsersTab);
        userLabel.setText(ControllerManager.getInstance().getLoginViewController().getLoggedUser().getName());
        setupTableViews();
        setupTableViewListeners();
    }

    private void setupTableViewListeners() {
        setupTableViewListener(userTableView);
        setupTableViewListener(techTableView);
        setupTableViewListener(managerTableView);
        setupTableViewListener(salesPersonTableView);
    }

    private void setupTableViewListener(TableView<User> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedUser = newSelection;
            }
        });
    }

    private void setupTableViews() {
        setupTableView(userTableView, adminModel::getUsers, userIdColumn, userNameColumn, uUsernameColumn, userEmailColumn, userRoleColumn);
        setupTableView(techTableView, adminModel::getTechnicians, techIdColumn, techNameColumn, techUsernameColumn, techEmailColumn, techRoleColumn);
        setupTableView(managerTableView, adminModel::getProjectManagers, manIdColumn, manNameColumn, manUsernameColumn, manEmailColumn, manRoleColumn);
        setupTableView(salesPersonTableView, adminModel::getSalesPersons, salesPersonsIdColumn, salesPersonsNameColumn, salesPersonsUsernameColumn, salesPersonsEmailColumn, salesPersonsRoleColumn);
    }

    private void setupTableView(TableView<User> tableView, Supplier<?> dataSupplier, TableColumn<User, Integer> idColumn,
                                TableColumn<User, String> nameColumn, TableColumn<User, String> usernameColumn,
                                TableColumn<User, String> emailColumn, TableColumn<User, UserRole> roleColumn) {
        tableView.getItems().setAll((List<User>) dataSupplier.get());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
    }


    @FXML
    private void addNewUser(ActionEvent event) {
        openNewWindow("/easv_2nd_term_exam/gui/views/admin/AddUserView.fxml", "Add New User");
    }

    @FXML
    public void showAllUsers(ActionEvent event) {
        adminTabPane.getSelectionModel().select(allUsersTab);
        setupTableViews();
    }

    @FXML
    private void showTechnicians(ActionEvent event) {
        adminTabPane.getSelectionModel().select(techTab);
        setupTableViews();
    }

    @FXML
    private void showProjectManagers(ActionEvent event) {
        adminTabPane.getSelectionModel().select(managerTab);
        setupTableViews();
    }

    @FXML
    private void showSalesPersons(ActionEvent event) {
        adminTabPane.getSelectionModel().select(salesPersonsTab);
        setupTableViews();
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        closeCurrentWindow(event);
        openNewWindow("/easv_2nd_term_exam/gui/views/login/LoginView.fxml", "Login");
    }

    private void openNewWindow(String fxmlPath, String title) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private void closeCurrentWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.hide();
    }

    private void deleteUserWithConfirmation() {
        if (selectedUser != null) {
            boolean confirmed = DialogUtil.showConfirmationDialog("Are you sure you want to delete " + selectedUser.getName() + " ?");
            if (confirmed) {
                adminModel.deleteUser(selectedUser.getId());
                setupTableViews(); // Refresh the table view after deletion
            }
        } else {
            DialogUtil.showInformationDialog("Please select a user to delete.");
        }
    }

    @FXML
    void deleteUser(ActionEvent event) {
        deleteUserWithConfirmation();
    }

    @FXML
    void updateUser(ActionEvent event) {
    }

    @FXML
    private void deleteTechnician(ActionEvent event) {
        deleteUserWithConfirmation();
    }

    @FXML
    private void updateTechnician(ActionEvent event) {
    }

    @FXML
    private void deleteManager(ActionEvent event) {
        deleteUserWithConfirmation();
    }

    @FXML
    private void updateManager(ActionEvent event) {
    }

    @FXML
    private void deleteSalesPersons(ActionEvent event) {
        deleteUserWithConfirmation();
    }

    @FXML
    private void updateSalesPerson(ActionEvent event) {
    }
}
