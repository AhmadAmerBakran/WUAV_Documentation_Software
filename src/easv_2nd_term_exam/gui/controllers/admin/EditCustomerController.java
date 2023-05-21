package easv_2nd_term_exam.gui.controllers.admin;

import easv_2nd_term_exam.be.Customer;
import easv_2nd_term_exam.enums.CustomerType;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import easv_2nd_term_exam.util.DialogUtility;
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

    }

    @FXML
    void submitEditing(ActionEvent event) {

        String name, email, address, billingAddress = null;
        name = customerNameFieldE.getText();
        email = customerEmailFieldE.getText();
        address = customerAddressFieldE.getText();

        if(customerTypeComboBoxE.getValue() == CustomerType.B2B)
        {
            billingAddress = customerAddressFieldBE.getText();
        }
        Customer customerToBeUpdated = new Customer(name, address, email, customerTypeComboBoxE.getValue());
        customerToBeUpdated.setBillingAddress(billingAddress);

        try {
            modelManager.getCustomerModel().createCustomer(customerToBeUpdated);
            DialogUtility.showInformationDialog("Customer updated successfully.");
            ((Node)event.getSource()).getScene().getWindow().hide();
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }
    }

}

