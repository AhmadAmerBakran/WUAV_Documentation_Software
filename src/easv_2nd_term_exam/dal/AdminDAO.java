package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.dal.connector.DBConnector;
import easv_2nd_term_exam.enums.UserRole;
import org.mindrot.jbcrypt.BCrypt;

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
        String sql = "SELECT * FROM Employee WHERE IsDeleted = 0";
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
                boolean isDeleted = result.getBoolean("IsDeleted");

                switch (UserRole.valueOf(role)) {
                    case TECHNICIAN:
                        users.add(new Technician(id, name, email, username, password, isDeleted));
                        break;
                    case PROJECT_MANAGER:
                        users.add(new ProjectManager(id, name, email, username, password, isDeleted));
                        break;
                    case SALES_PERSON:
                        users.add(new SalesPerson(id, name, email, username, password, isDeleted));
                        break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to get all users.", e);
        }

        return users;
    }

    public User addUser(User user) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(user.getPassword(), salt);

        String sql = "INSERT INTO Employee (Name, Email, Username, Password, Role, IsDeleted) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUsername());
            statement.setString(4, hashedPassword);
            statement.setString(5, user.getRole().toString());
            statement.setBoolean(6, user.isDeleted());

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

    public boolean hasAdmins() {
        String sql = "SELECT COUNT(*) FROM Employee WHERE Role = ? AND IsDeleted = 0;";

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, UserRole.ADMIN.toString());

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    int count = result.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while checking for existing admins.", e);
        }

        return false;
    }

    public void createAdminUser(String name, String email, String username, String password) {
        User adminUser = new Admin(0, name, email, username, password, false);
        adminUser.setRole(UserRole.ADMIN);
        addUser(adminUser);
    }



    public boolean updateUser(User user) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(user.getPassword(), salt);
        String sql = "UPDATE Employee SET Name = ?, Email = ?, Username = ?, Password = ?, Role = ?, IsDeleted = ? WHERE ID = ?";

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUsername());
            statement.setString(4, hashedPassword);
            statement.setString(5, user.getRole().toString());
            statement.setBoolean(6, user.isDeleted());
            statement.setInt(7, user.getId());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to update user.", e);
        }
    }

    public boolean deleteUser(int id) {
        String sql = "UPDATE Employee SET IsDeleted = 1 WHERE ID = ?";

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to delete user.", e);
        }
    }
    public boolean restoreUser(int id) {
        String sql = "UPDATE Employee SET IsDeleted = 0 WHERE ID = ?";

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to delete user.", e);
        }
    }
    private List<User> getUsers(String sql) {
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
                boolean isDeleted = result.getBoolean("IsDeleted");

                switch (UserRole.valueOf(role)) {
                    case TECHNICIAN:
                        users.add(new Technician(id, name, email, username, password, isDeleted));
                        break;
                    case PROJECT_MANAGER:
                        users.add(new ProjectManager(id, name, email, username, password, isDeleted));
                        break;
                    case SALES_PERSON:
                        users.add(new SalesPerson(id, name, email, username, password, isDeleted));
                        break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to get users.", e);
        }

        return users;
    }

    public List<User> getActiveUsers() {
        return getUsers("SELECT * FROM Employee WHERE IsDeleted = 0");
    }

    public List<User> getDeletedUsers() {
        return getUsers("SELECT * FROM Employee WHERE IsDeleted = 1");
    }

    public List<Technician> getAllActiveTechnicians() {
        return getUsersByRole(UserRole.TECHNICIAN, false);
    }

    public List<Technician> getAllDeletedTechnicians() {
        return getUsersByRole(UserRole.TECHNICIAN, true);
    }

    public List<ProjectManager> getAllActiveProjectManagers() {
        return getUsersByRole(UserRole.PROJECT_MANAGER, false);
    }

    public List<ProjectManager> getAllDeletedProjectManagers() {
        return getUsersByRole(UserRole.PROJECT_MANAGER, true);
    }

    public List<SalesPerson> getAllActiveSalesPersons() {
        return getUsersByRole(UserRole.SALES_PERSON, false);
    }

    public List<SalesPerson> getAllDeletedSalesPersons() {
        return getUsersByRole(UserRole.SALES_PERSON, true);
    }


    private <T extends User> List<T> getUsersByRole(UserRole role, boolean isDeleted) {
        String sql = "SELECT * FROM Employee WHERE Role = ? AND IsDeleted = ?";
        List<T> users = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.toString());
            statement.setBoolean(2, isDeleted);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("ID");
                String name = result.getString("Name");
                String email = result.getString("Email");
                String username = result.getString("Username");
                String password = result.getString("Password");

                switch (role) {
                    case TECHNICIAN:
                        users.add((T) new Technician(id, name, email, username, password, isDeleted));
                        break;
                    case PROJECT_MANAGER:
                        users.add((T) new ProjectManager(id, name, email, username, password, isDeleted));
                        break;
                    case SALES_PERSON:
                        users.add((T) new SalesPerson(id, name, email, username, password, isDeleted));
                        break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to get users with role: " + role, e);
        }

        return users;
    }


}
