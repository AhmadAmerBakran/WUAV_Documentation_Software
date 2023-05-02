// easv_2nd_term_exam/dal/AdminDAO.java
package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.dal.connector.DBConnector;
import easv_2nd_term_exam.enums.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private DBConnector dbConnector;

    public AdminDAO() {
        dbConnector = new DBConnector();
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Employee";
        List<User> users = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("ID");
                String name = result.getString("Name");
                String email = result.getString("Email");
                String username = result.getString("Username");
                String password = result.getString("Password");
                String role = result.getString("Role");

                switch (UserRole.valueOf(role)) {
                    case TECHNICIAN:
                        users.add(new Technician(id, name, email, username, password));
                        break;
                    case PROJECT_MANAGER:
                        users.add(new ProjectManager(id, name, email, username, password));
                        break;
                    case SALES_PERSON:
                        users.add(new SalesPerson(id, name, email, username, password));
                        break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to get all users.", e);
        }

        return users;
    }

    public User addUser(User user) {
        String sql = "INSERT INTO Employee (Name, Email, Username, Password, Role) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().toString());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to add user.", e);
        }

        return user;
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE Employee SET Name = ?, Email = ?, Username = ?, Password = ?, Role = ? WHERE ID = ?";

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().toString());
            statement.setInt(6, user.getId());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to update user.", e);
        }
    }

    public boolean deleteUser(int id) {
        String sql = "DELETE FROM Employee WHERE ID = ?";

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to delete user.", e);
        }
    }

    public List<Technician> getAllTechnicians() {
        return getUsersByRole(UserRole.TECHNICIAN);
    }

    public List<ProjectManager> getAllProjectManagers() {
        return getUsersByRole(UserRole.PROJECT_MANAGER);
    }

    public List<SalesPerson> getAllSalesPersons() {
        return getUsersByRole(UserRole.SALES_PERSON);
    }

    private <T extends User> List<T> getUsersByRole(UserRole role) {
        String sql = "SELECT * FROM Employee WHERE Role = ?";
        List<T> users = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.toString());
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("ID");
                String name = result.getString("Name");
                String email = result.getString("Email");
                String username = result.getString("Username");
                String password = result.getString("Password");

                switch (role) {
                    case TECHNICIAN:
                        users.add((T) new Technician(id, name, email, username, password));
                        break;
                    case PROJECT_MANAGER:
                        users.add((T) new ProjectManager(id, name, email, username, password));
                        break;
                    case SALES_PERSON:
                        users.add((T) new SalesPerson(id, name, email, username, password));
                        break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to get users with role: " + role, e);
        }

        return users;
    }

}
