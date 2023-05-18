package easv_2nd_term_exam.gui.controllers.admin;

import easv_2nd_term_exam.be.Customer;
import easv_2nd_term_exam.be.DeviceType;
import easv_2nd_term_exam.be.InstallationType;
import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.enums.UserRole;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import easv_2nd_term_exam.util.DialogUtility;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Supplier;

public class AdminDashboardController implements Initializable {

    @FXML
    private TabPane usersTabPane, customersPane;

    @FXML
    private AnchorPane installationTypesPane, deviceTypesPane, reportPane;

    @FXML
    private TableView<InstallationType> installationTypeTableView;

    @FXML
    private TableColumn<InstallationType, Integer> installationTypeIdColumn;

    @FXML
    private TableColumn<InstallationType, String> installationTypeNameColumn;


    @FXML
    private TableView<DeviceType> deviceTypeTableView;

    @FXML
    private TableColumn<DeviceType, Integer> deviceTypeIdColumn;

    @FXML
    private TableColumn<DeviceType, String> deviceTypeNameColumn;



    @FXML
    private TableView<Customer> customerTableView, customerTableView1;

    @FXML
    private TableColumn<Customer, Integer> customerIdColumn, customerIdColumn1;

    @FXML
    private TableColumn<Customer, String> customerNameColumn, customerEmailColumn,
            customerFirstAddressColumn, customerSecondAddressColumn, customerNameColumn1, customerEmailColumn1,
            customerFirstAddressColumn1, customerSecondAddressColumn1;

    @FXML
    private TableView<User> userTableView, techTableView, managerTableView, salesPersonTableView, userTableView1;
    @FXML
    private TableColumn<User, Integer> userIdColumn, techIdColumn, manIdColumn, salesPersonsIdColumn, userIdColumn1;
    @FXML
    private TableColumn<User, String> uUsernameColumn, userEmailColumn, userNameColumn,
            uUsernameColumn1, userEmailColumn1, userNameColumn1,
            techNameColumn, techEmailColumn, techUsernameColumn,
            manNameColumn, manEmailColumn, manUsernameColumn,
            salesPersonsNameColumn, salesPersonsEmailColumn, salesPersonsUsernameColumn;
    @FXML
    private TableColumn<User, UserRole> userRoleColumn, techRoleColumn, manRoleColumn, salesPersonsRoleColumn, userRoleColumn1;
    @FXML
    private Label userLabel;
    @FXML
    private Tab allUsersTab, techTab, managerTab, salesPersonsTab, deletedUsersTab;

    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;
    private User selectedUser;
    private Customer selectedCustomer;

    private InstallationType selectedInstallationType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        ControllerManager.getInstance().setAdminDashboardController(this);
        getPane(true, false, false, false, false);
        usersTabPane.getSelectionModel().select(allUsersTab);
        userLabel.setText(ControllerManager.getInstance().getLoginViewController().getLoggedUser().getName());
        setupTableViews();
        setupTableViewListeners();
        setUpCustomerTableView();
        setUpInstallationTypeTableView();
        setUpDeviceTypeTableView();
        setUpDeletedUsersTableView();
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
        setupTableView(userTableView, modelManager.getAdminModel()::getUsers, userIdColumn, userNameColumn, uUsernameColumn, userEmailColumn, userRoleColumn);
        setupTableView(techTableView, modelManager.getAdminModel()::getTechnicians, techIdColumn, techNameColumn, techUsernameColumn, techEmailColumn, techRoleColumn);
        setupTableView(managerTableView, modelManager.getAdminModel()::getProjectManagers, manIdColumn, manNameColumn, manUsernameColumn, manEmailColumn, manRoleColumn);
        setupTableView(salesPersonTableView, modelManager.getAdminModel()::getSalesPersons, salesPersonsIdColumn, salesPersonsNameColumn, salesPersonsUsernameColumn, salesPersonsEmailColumn, salesPersonsRoleColumn);
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

        // Set column widths to be proportional to the TableView width
        idColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.075));
        nameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        usernameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        emailColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        roleColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.18));
    }

    private void setUpDeletedUsersTableView()
    {
        userTableView1.getItems().setAll(modelManager.getAdminModel().getDeletedUsers());
        userIdColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
        userNameColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        uUsernameColumn1.setCellValueFactory(new PropertyValueFactory<>("username"));
        userEmailColumn1.setCellValueFactory(new PropertyValueFactory<>("email"));
        userRoleColumn1.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    private void setUpCustomerTableView()
    {
        customerTableView.getItems().setAll(modelManager.getCustomerModel().getCustomers());
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        customerEmailColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        customerFirstAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        customerSecondAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("billingAddress"));
    }

    private void setUpDeletedCustomersTableView()
    {
        try {
            customerTableView1.getItems().setAll(modelManager.getCustomerModel().getDeletedCustomers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        customerIdColumn1.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        customerNameColumn1.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        customerEmailColumn1.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        customerFirstAddressColumn1.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        customerSecondAddressColumn1.setCellValueFactory(new PropertyValueFactory<Customer, String>("billingAddress"));
    }

    private void setUpInstallationTypeTableView()
    {
        installationTypeTableView.getItems().setAll(modelManager.getInstallationTypeModel().getInstallationTypes());
        installationTypeIdColumn.setCellValueFactory(new PropertyValueFactory<InstallationType, Integer>("id"));
        installationTypeNameColumn.setCellValueFactory(new PropertyValueFactory<InstallationType, String>("name"));
    }
    private void setUpDeviceTypeTableView()
    {
        deviceTypeTableView.getItems().setAll(modelManager.getDeviceTypeModel().getDeviceTypes());
        deviceTypeIdColumn.setCellValueFactory(new PropertyValueFactory<DeviceType, Integer>("id"));
        deviceTypeNameColumn.setCellValueFactory(new PropertyValueFactory<DeviceType, String>("name"));
    }

    private void getPane(boolean usersOverview, boolean customerOverview, boolean installationOverview, boolean deviceOverview, boolean reportOverview)
    {
        usersTabPane.setVisible(usersOverview);
        customersPane.setVisible(customerOverview);
        installationTypesPane.setVisible(installationOverview);
        deviceTypesPane.setVisible(deviceOverview);
        reportPane.setVisible(reportOverview);

    }



    @FXML
    private void addNewUser(ActionEvent event) {
        openNewWindow("/easv_2nd_term_exam/gui/views/admin/AddUserView.fxml", "Add New User");
    }

    @FXML
    public void showAllUsers(ActionEvent event) {
        getPane(true, false, false, false, false);
        usersTabPane.getSelectionModel().select(allUsersTab);
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

    @FXML
    private void showDeletedUsers()
    {
        setUpDeletedUsersTableView();

    }

    @FXML
    private void showAllUsersTab()
    {
        if(modelManager != null){
        setupTableViews();}

    }

    private void closeCurrentWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.hide();
    }

    private void deleteUserWithConfirmation() {
        if (selectedUser != null) {
            boolean confirmed = DialogUtility.showConfirmationDialog("Are you sure you want to delete " + selectedUser.getName() + " ?");
            if (confirmed) {
                modelManager.getAdminModel().deleteUser(selectedUser.getId());
                setupTableViews(); // Refresh the table view after deletion
            }
        } else {
            DialogUtility.showInformationDialog("Please select a user to delete.");
        }
    }

    private void updateUserWithException()
    {
        if(selectedUser != null){
            openNewWindow("/easv_2nd_term_exam/gui/views/admin/EditUserView.fxml", "Update " + selectedUser.getRole().toString().toLowerCase());
        }else {
            DialogUtility.showInformationDialog("Please select a user to update.");
        }

    }


    @FXML
    void deleteUser(ActionEvent event) {
        deleteUserWithConfirmation();
    }

    @FXML
    void updateUser(ActionEvent event) {
        updateUserWithException();
    }

    @FXML
    private void deleteTechnician(ActionEvent event) {
        deleteUserWithConfirmation();
    }

    @FXML
    private void updateTechnician(ActionEvent event) {
        updateUserWithException();
    }

    @FXML
    private void deleteManager(ActionEvent event) {
        deleteUserWithConfirmation();
    }

    @FXML
    private void updateManager(ActionEvent event) {
        updateUserWithException();
    }

    @FXML
    private void deleteSalesPersons(ActionEvent event) {
        deleteUserWithConfirmation();
    }

    @FXML
    private void updateSalesPerson(ActionEvent event) {
        updateUserWithException();
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    @FXML
    private void showCustomers(ActionEvent event) {
        getPane(false, true, false, false, false);
    }

    @FXML
    private void showInstallationTypes(ActionEvent event) {
        getPane(false, false, true, false, false);

    }

    @FXML
    private void showDeviceTypes(ActionEvent event) {
        getPane(false, false, false, true, false);
    }

    @FXML
    private void addNewCustomer(ActionEvent event) {
        openNewWindow("/easv_2nd_term_exam/gui/views/admin/AddCustomerView.fxml", "Add New Customer");
    }

    @FXML
    private void updateCustomer(ActionEvent event) {
    }

    @FXML
    private void deleteCustomer(ActionEvent event) {
        selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        boolean confirmed = DialogUtility.showConfirmationDialog("Are you sure you want to delete customer " + selectedCustomer.getName() + " ?");
        if(confirmed) {
            try {
                modelManager.getCustomerModel().deleteCustomer(selectedCustomer.getId());
                DialogUtility.showInformationDialog("Customer deleted successfully.");
                setUpDeletedCustomersTableView();
                setUpCustomerTableView();
            } catch (Exception e) {
                DialogUtility.showExceptionDialog(e);
            }
        }
    }


    @FXML
    private void addNewInstallationType(ActionEvent event) {
        openNewWindow("/easv_2nd_term_exam/gui/views/admin/AddInstallationTypeView.fxml", "Add New Installation Type");
    }

    @FXML
    private void updateInstallationType(ActionEvent event) {
        selectedInstallationType = installationTypeTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/admin/EditInstallationTypeView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EditInstallationTypeController controller = loader.getController();
        controller.fillTextFieldWithInstallationTypeData(selectedInstallationType);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Edit Installation Type");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteInstallationType(ActionEvent event) {
    }

    @FXML
    private void addNewDeviceType(ActionEvent event) {
        openNewWindow("/easv_2nd_term_exam/gui/views/admin/AddDeviceTypeView.fxml", "Add New Device Type");

    }

    @FXML
    private void updateDeviceType(ActionEvent event) {
    }

    @FXML
    private void deleteDeviceType(ActionEvent event) {
    }

    @FXML
    private void showReports(ActionEvent event) {
        getPane(false, false, false, false, true);
    }

    @FXML
    private void downloadReport(ActionEvent event) {
    }

    @FXML
    private void restoreUser(ActionEvent event) {
        selectedUser = userTableView1.getSelectionModel().getSelectedItem();
        modelManager.getAdminModel().restoreUser(selectedUser.getId());
        setUpDeletedUsersTableView();

    }

    @FXML
    private void restoreCustomer(ActionEvent event) {
    }

    @FXML
    private void showActiveCustomers(Event event) {
        if(modelManager != null){
            setUpCustomerTableView();}
    }

    @FXML
    private void showDeletedCustomers(Event event) {
        if(modelManager != null){
            setUpDeletedCustomersTableView();}
    }
}
