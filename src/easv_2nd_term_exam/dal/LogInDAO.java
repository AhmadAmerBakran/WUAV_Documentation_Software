package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.dal.connector.DBConnector;
import easv_2nd_term_exam.enums.UserRole;

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
        String sql = "SELECT * FROM Employee WHERE Username = ? AND Password = ?";
        String name, email, role;

        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int id = result.getInt("ID");
                username = result.getString("Username");
                password = result.getString("Password");
                name = result.getString("Name");
                email = result.getString("Email");
                role = result.getString("Role");

                switch (UserRole.valueOf(role)) {
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

        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to login.", e);
        }
    }

}