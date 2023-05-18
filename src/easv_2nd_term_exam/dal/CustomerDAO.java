package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.Customer;
import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.dal.connector.DBConnector;
import easv_2nd_term_exam.dal.interfaces.ICustomerDAO;
import easv_2nd_term_exam.enums.CustomerType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {

    private DBConnector dbConnector;

    public CustomerDAO() {
        dbConnector = new DBConnector();
    }

    @Override
    public Customer createCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (Name, Address, Email, Type, BillingAddress, IsDeleted) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getAddress());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getType().toString());
            pstmt.setString(5, customer.getBillingAddress());
            pstmt.setBoolean(6, customer.isDeleted());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
            }
        }
        return customer;
    }

    @Override
    public Customer findCustomerByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE Email = ? AND IsDeleted = 0";
        Customer customer = null;

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, email);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    customer = constructCustomer(resultSet);
                }
            }
        }

        return customer;
    }
    @Override
    public Customer getCustomer(int id) throws SQLException {
        Customer customer = null;
        String sql = "SELECT * FROM Customer WHERE ID = ? AND IsDeleted = 0";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    customer = constructCustomer(rs);
                }
            }
        }
        return customer;
    }

    @Override
    public List<Customer> getDeletedCustomers() throws SQLException {
        List<Customer> deletedCustomers = new ArrayList<>();
        String sql = "SELECT * FROM Customer WHERE IsDeleted = 1";
        try (Connection connection = dbConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = constructCustomer(rs);
                deletedCustomers.add(customer);
            }
        }
        return deletedCustomers;
    }


    @Override
    public List<Customer> getAllActiveCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer WHERE IsDeleted = 0";
        try (Connection connection = dbConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = constructCustomer(rs);
                customers.add(customer);
            }
        }
        return customers;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET Name = ?, Address = ?, Email = ?, Type = ?, BillingAddress = ?, IsDeleted = ? WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getAddress());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getType().toString());
            pstmt.setString(5, customer.getBillingAddress());
            pstmt.setBoolean(6, customer.isDeleted());
            pstmt.setInt(7, customer.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Customer updateCustomerByEmail(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET Name = ?, Address = ?, Type = ?, BillingAddress = ?, IsDeleted = ? WHERE Email = ?";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getAddress());
            pstmt.setString(3, customer.getType().toString());
            pstmt.setString(4, customer.getBillingAddress());
            pstmt.setBoolean(5, customer.isDeleted());
            pstmt.setString(6, customer.getEmail());

            pstmt.executeUpdate();
        }

        return customer;
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        String sql = "UPDATE Customer SET IsDeleted = 1 WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    private Customer constructCustomer(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String name = resultSet.getString("Name");
        String address = resultSet.getString("Address");
        String email = resultSet.getString("Email");
        CustomerType type = CustomerType.valueOf(resultSet.getString("Type"));
        boolean isDeleted = resultSet.getBoolean("IsDeleted");

        Customer customer = new Customer(id, name, address, email, type, isDeleted);
        customer.setBillingAddress(resultSet.getString("BillingAddress"));

        return customer;
    }
}
