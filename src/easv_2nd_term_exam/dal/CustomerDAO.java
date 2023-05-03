package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.Customer;
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
        String sql = "INSERT INTO Customer (Name, Address, Email, Type) VALUES (?, ?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getAddress());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getType().toString());

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
    public Customer getCustomer(int id) throws SQLException {
        Customer customer = null;
        String sql = "SELECT * FROM Customer WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("Address"),
                            rs.getString("Email"),
                            CustomerType.valueOf(rs.getString("Type"))
                    );
                }
            }
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (Connection connection = dbConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Email"),
                        CustomerType.valueOf(rs.getString("Type"))
                ));
            }
        }
        return customers;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET Name = ?, Address = ?, Email = ?, Type = ? WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getAddress());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getType().toString());
            pstmt.setInt(5, customer.getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM Customer WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
