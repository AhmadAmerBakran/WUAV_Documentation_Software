package easv_2nd_term_exam.gui.models;

import easv_2nd_term_exam.be.Customer;
import easv_2nd_term_exam.bll.CustomerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CustomerModel {

    private CustomerManager customerManager;
    private ObservableList<Customer> customers;

    public CustomerModel() {
        customerManager = new CustomerManager();
        customers = FXCollections.observableArrayList();
    }

    public void loadCustomers() throws Exception {
        List<Customer> customerList = customerManager.getAllCustomers();
        customers.setAll(customerList);
    }

    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    public Customer createCustomer(Customer customer) throws Exception {
        Customer createdCustomer = customerManager.createCustomer(customer);
        customers.add(createdCustomer);
        return createdCustomer;
    }

    public void updateCustomer(Customer customer) throws Exception {
        customerManager.updateCustomer(customer);
        int index = customers.indexOf(customer);
        customers.set(index, customer);
    }

    public void deleteCustomer(int id) throws Exception {
        customerManager.deleteCustomer(id);
        customers.removeIf(c -> c.getId() == id);
    }
}
