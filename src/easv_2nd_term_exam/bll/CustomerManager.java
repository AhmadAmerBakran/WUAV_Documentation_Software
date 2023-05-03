package easv_2nd_term_exam.bll;

import easv_2nd_term_exam.be.Customer;
import easv_2nd_term_exam.dal.CustomerDAO;
import easv_2nd_term_exam.dal.interfaces.ICustomerDAO;

import java.sql.SQLException;
import java.util.List;

public class CustomerManager {

    private ICustomerDAO customerDAO;

    public CustomerManager() {
        customerDAO = new CustomerDAO();
    }

    public Customer createCustomer(Customer customer) throws Exception {
        return customerDAO.createCustomer(customer);
    }

    public Customer getCustomer(int id) throws Exception {
        return customerDAO.getCustomer(id);
    }

    public List<Customer> getAllCustomers() throws Exception {
        return customerDAO.getAllCustomers();
    }

    public void updateCustomer(Customer customer) throws Exception {
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int id) throws Exception {
        customerDAO.deleteCustomer(id);
    }
}
