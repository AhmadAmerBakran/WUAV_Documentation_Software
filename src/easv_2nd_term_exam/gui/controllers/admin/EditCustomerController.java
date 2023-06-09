package easv_2nd_term_exam.gui.controllers.admin;

import easv_2nd_term_exam.be.Customer;
import easv_2nd_term_exam.enums.CustomerType;
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
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {

    @FXML
    private VBox billingAddressVBoxE;

    @FXML
    private TextField customerAddressFieldBE, customerAddressFieldE, customerEmailFieldE, customerNameFieldE;


    @FXML
    private ComboBox<CustomerType> customerTypeComboBoxE;

    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;
    private Customer selectedCustomer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        ControllerManager.getInstance().setEditCustomerController(this);
        customerTypeComboBoxE.getItems().setAll(CustomerType.values());
        customerTypeComboBoxE.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == CustomerType.B2B) {
                billingAddressVBoxE.setVisible(true);
            } else {
                billingAddressVBoxE.setVisible(false);
            }
        });

    }

    public void fillTextFieldsWithCustomer(Customer customer) {
        selectedCustomer = customer;
        customerNameFieldE.setText(customer.getName());
        customerEmailFieldE.setText(customer.getEmail());
        customerAddressFieldE.setText(customer.getAddress());
        customerTypeComboBoxE.setValue(customer.getType());
        customerAddressFieldBE.setText(customer.getBillingAddress());
    }

    @FXML
    void cancelEditing(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void submitEditing(ActionEvent event) {
        if(!ValidationUtility.isNotEmpty(customerNameFieldE) ||
                !ValidationUtility.isValidName(customerNameFieldE)) {
            DialogUtility.showInformationDialog("Invalid or empty Name.");
            return;
        }

        if(!ValidationUtility.isNotEmpty(customerEmailFieldE) ||
                !ValidationUtility.isValidEmail(customerEmailFieldE)) {
            DialogUtility.showInformationDialog("Invalid or empty Email.");
            return;
        }

        if(!ValidationUtility.isNotEmpty(customerAddressFieldE) ||
                !ValidationUtility.isValidDanishAddress(customerAddressFieldE)) {
            DialogUtility.showInformationDialog("Invalid or empty Address.");
            return;
        }

        if(!ValidationUtility.isComboBoxNotEmpty(customerTypeComboBoxE)) {
            DialogUtility.showInformationDialog("Customer type is not selected.");
            return;
        }

        String name = customerNameFieldE.getText();
        String email = customerEmailFieldE.getText();
        String address = customerAddressFieldE.getText();
        String billingAddress = null;
        CustomerType type = customerTypeComboBoxE.getValue();

        if(type == CustomerType.B2B) {
            if(!ValidationUtility.isNotEmpty(customerAddressFieldBE)) {
                DialogUtility.showInformationDialog("Billing Address is required for B2B customers.");
                return;
            }
            billingAddress = customerAddressFieldBE.getText();
        }

        selectedCustomer.setName(name);
        selectedCustomer.setAddress(address);
        selectedCustomer.setEmail(email);
        selectedCustomer.setType(type);
        selectedCustomer.setBillingAddress(billingAddress);

        try {
            modelManager.getCustomerModel().updateCustomer(selectedCustomer);
            DialogUtility.showInformationDialog("Customer updated successfully.");
            ControllerManager.getInstance().getAdminDashboardController().setUpCustomerTableView();
            ((Node)event.getSource()).getScene().getWindow().hide();
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }
    }




}

