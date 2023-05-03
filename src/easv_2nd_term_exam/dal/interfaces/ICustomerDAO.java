package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.Customer;
import java.util.List;

public interface ICustomerDAO {
    Customer createCustomer(Customer customer) throws Exception;
    Customer getCustomer(int id) throws Exception;
    List<Customer> getAllCustomers() throws Exception;
    void updateCustomer(Customer customer) throws Exception;
    void deleteCustomer(int id) throws Exception;
}
