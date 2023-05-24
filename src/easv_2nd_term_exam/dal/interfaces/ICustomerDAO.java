package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    Customer createCustomer(Customer customer) throws SQLException;
    Customer getCustomer(int id) throws SQLException;
    List<Customer> getAllActiveCustomers() throws SQLException;
    List<Customer> getDeletedCustomers() throws SQLException;
    void updateCustomer(Customer customer) throws SQLException;
    void deleteCustomer(int id)  throws SQLException;
    Customer findCustomerByEmail(String email) throws SQLException;
    Customer updateCustomerByEmail(Customer customer) throws SQLException;
    public void restoreCustomer(int id) throws SQLException;
}
