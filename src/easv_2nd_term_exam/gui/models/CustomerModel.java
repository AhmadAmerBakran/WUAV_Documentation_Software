package easv_2nd_term_exam.gui.models;

import easv_2nd_term_exam.be.Customer;
import easv_2nd_term_exam.bll.CustomerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class CustomerModel {

    private CustomerManager customerManager;
    private ObservableList<Customer> customers;
    private ObservableList<Customer> deletedCustomers;

    public CustomerModel() throws Exception {
        customerManager = new CustomerManager();
        customers = FXCollections.observableArrayList();
        customers.setAll(customerManager.getAllActiveCustomers());
        deletedCustomers = FXCollections.observableArrayList();
        deletedCustomers.setAll(customerManager.getDeletedCustomers());
    }

    public Customer findCustomerByEmail(String email) throws SQLException {
        return customerManager.findCustomerByEmail(email);
    }

    public void loadCustomers() throws Exception {
        List<Customer> customerList = customerManager.getAllActiveCustomers();
        customers.setAll(customerList);
    }

    public ObservableList<Customer> getCustomers() throws Exception {
        return FXCollections.observableArrayList(customerManager.getAllActiveCustomers());
    }
    public ObservableList<Customer> getDeletedCustomers() throws SQLException
    {
        return FXCollections.observableArrayList(customerManager.getDeletedCustomers());
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

    public void restoreCustomer(int id) throws Exception {
        customerManager.restoreCustomer(id);
    }

    public Customer updateCustomerByEmail(Customer customer) throws Exception {
        return customerManager.updateCustomerByEmail(customer);
    }


}