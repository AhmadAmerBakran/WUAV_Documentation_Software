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

public class AddCustomerController implements Initializable {

    @FXML
    private VBox billingAddressVBox;

    @FXML
    private TextField customerNameField, customerEmailField, customerAddressField;

    @FXML
    private TextField customerAddressFieldB;

    @FXML
    private ComboBox<CustomerType> customerTypeComboBox;


    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();
        ControllerManager.getInstance().setAddCustomerController(this);
        customerTypeComboBox.getItems().setAll(CustomerType.values());
        customerTypeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == CustomerType.B2B) {
                billingAddressVBox.setVisible(true);
            } else {
                billingAddressVBox.setVisible(false);
            }
        });
    }


    @FXML
    private void cancelAdding(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }


    @FXML
    private void submitAdding(ActionEvent event) {
        String name, email, address, billingAddress = null;
        name = customerNameField.getText();
        email = customerEmailField.getText();
        address = customerAddressField.getText();

        if(customerTypeComboBox.getValue() == CustomerType.B2B)
        {
            billingAddress = customerAddressFieldB.getText();
        }
        Customer customerToBeCreated = new Customer(name, address, email, customerTypeComboBox.getValue());
        customerToBeCreated.setBillingAddress(billingAddress);

        try {
            modelManager.getCustomerModel().createCustomer(customerToBeCreated);
            DialogUtility.showInformationDialog("Customer created successfully.");
            ((Node)event.getSource()).getScene().getWindow().hide();
        } catch (Exception e) {
            DialogUtility.showExceptionDialog(e);
        }
    }



}