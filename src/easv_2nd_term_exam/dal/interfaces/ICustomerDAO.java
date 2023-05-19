package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    Customer createCustomer(Customer customer) throws Exception;
    Customer getCustomer(int id) throws Exception;
    List<Customer> getAllActiveCustomers() throws Exception;
    List<Customer> getDeletedCustomers() throws SQLException;
    void updateCustomer(Customer customer) throws Exception;
    void deleteCustomer(int id) throws Exception;
    Customer findCustomerByEmail(String email) throws SQLException;
    Customer updateCustomerByEmail(Customer customer) throws SQLException;
    public void restoreCustomer(int id) throws SQLException;
}
