package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.Installation;
import easv_2nd_term_exam.dal.connector.DBConnector;
import easv_2nd_term_exam.dal.interfaces.IInstallationDAO;
import easv_2nd_term_exam.enums.InstallationType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstallationDAO implements IInstallationDAO {

    private DBConnector dbConnector;

    public InstallationDAO() {
        dbConnector = new DBConnector();
    }

    @Override
    public Installation createInstallation(Installation installation) throws SQLException {
        String sql = "INSERT INTO Installation (customerId, technicianId, username, password, description, installationType) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, installation.getCustomerId());
            pstmt.setInt(2, installation.getTechnicianId());
            pstmt.setString(3, installation.getUsername());
            pstmt.setString(4, installation.getPassword());
            pstmt.setString(5, installation.getDescription());
            pstmt.setString(6, installation.getInstallationType().toString());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    installation.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating installation failed, no ID obtained.");
                }
            }
        }
        return installation;
    }

    @Override
    public Installation getInstallation(int id) throws SQLException {
        Installation installation = null;
        String sql = "SELECT * FROM Installation WHERE id = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    installation = new Installation(
                            rs.getInt("id"),
                            rs.getInt("customerId"),
                            rs.getInt("technicianId"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("description"),
                            InstallationType.valueOf(rs.getString("installationType"))
                    );
                }
            }
        }
        return installation;
    }

    @Override
    public List<Installation> getAllInstallations() throws SQLException {
        List<Installation> installations = new ArrayList<>();
        String sql = "SELECT * FROM Installation";
        try (Connection connection = dbConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                installations.add(new Installation(
                        rs.getInt("id"),
                        rs.getInt("customerId"),
                        rs.getInt("technicianId"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("description"),
                        InstallationType.valueOf(rs.getString("installationType"))
                ));
            }
        }
        return installations;
    }

    @Override
    public void updateInstallation(Installation installation) throws SQLException {
        String sql = "UPDATE Installation SET customerId = ?, technicianId = ?, username = ?, password = ?, description = ?, installationType = ? WHERE id = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, installation.getCustomerId());
            pstmt.setInt(2, installation.getTechnicianId());
            pstmt.setString(3, installation.getUsername());
            pstmt.setString(4, installation.getPassword());
            pstmt.setString(5, installation.getDescription());
            pstmt.setString(6, installation.getInstallationType().toString());
            pstmt.setInt(7, installation.getId());

            pstmt.executeUpdate();
        }
    }

    public void deleteInstallation(int id) throws SQLException {
        String sql = "DELETE FROM Installation WHERE id = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
