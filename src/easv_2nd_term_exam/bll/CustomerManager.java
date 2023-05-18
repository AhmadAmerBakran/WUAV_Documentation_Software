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

    public Customer findCustomerByEmail(String email) throws SQLException {
        return customerDAO.findCustomerByEmail(email);
    }


    public Customer getCustomer(int id) throws Exception {
        return customerDAO.getCustomer(id);
    }

    public List<Customer> getAllActiveCustomers() throws Exception {
        return customerDAO.getAllActiveCustomers();
    }
    public List<Customer> getDeletedCustomers() throws SQLException
    {
        return customerDAO.getDeletedCustomers();
    }

    public void updateCustomer(Customer customer) throws Exception {
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int id) throws Exception {
        customerDAO.deleteCustomer(id);
    }

    public Customer updateCustomerByEmail(Customer customer) throws SQLException {
        return customerDAO.updateCustomerByEmail(customer);
    }

}
