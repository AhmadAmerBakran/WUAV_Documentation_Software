package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.dal.connector.DBConnector;
import easv_2nd_term_exam.enums.UserRole;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInDAO {
    private DBConnector dbConnector;

    public LogInDAO() {
        dbConnector = new DBConnector();
    }


    public User userLogIn(String username, String password) {
        String sql = "SELECT * FROM Employee WHERE Username = ?;";

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("ID");
                    username = result.getString("Username");
                    String hashedPassword = result.getString("Password");
                    String name = result.getString("Name");
                    String email = result.getString("Email");
                    String role = result.getString("Role");

                    // Check if the provided password matches the hashed password from the database
                    if (!BCrypt.checkpw(password, hashedPassword)) {
                        return null;
                    }

                    UserRole userRole = UserRole.valueOf(role);
                    switch (userRole) {
                        case ADMIN:
                            return new Admin(id, name, email, username, password);
                        case TECHNICIAN:
                            return new Technician(id, name, email, username, password);
                        case PROJECT_MANAGER:
                            return new ProjectManager(id, name, email, username, password);
                        case SALES_PERSON:
                            return new SalesPerson(id, name, email, username, password);
                        default:
                            return null;
                    }

                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to login.", e);
        }
    }


}