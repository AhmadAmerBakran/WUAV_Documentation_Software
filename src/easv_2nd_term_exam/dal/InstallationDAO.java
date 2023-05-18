package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.Installation;
import easv_2nd_term_exam.dal.connector.DBConnector;
import easv_2nd_term_exam.dal.interfaces.IInstallationDAO;

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
        String sql = "INSERT INTO Installation (CustomerId, TechnicianId, Description, InstallationTypeId, InstallationDate, ExpiryDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, installation.getCustomerId());
            pstmt.setInt(2, installation.getTechnicianId());
            pstmt.setString(3, installation.getDescription());
            pstmt.setInt(4, installation.getInstallationTypeId());
            pstmt.setDate(5, java.sql.Date.valueOf(installation.getCreatedDate()));
            pstmt.setDate(6, java.sql.Date.valueOf(installation.getExpiryDate()));

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
        String sql = "SELECT * FROM Installation WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    installation = new Installation(
                            rs.getInt("ID"),
                            rs.getInt("CustomerId"),
                            rs.getInt("TechnicianId"),
                            rs.getString("Description"),
                            rs.getInt("InstallationTypeId")
                    );
                    installation.setCreatedDate(rs.getDate("InstallationDate").toLocalDate());
                    installation.setExpiryDate(rs.getDate("ExpiryDate").toLocalDate());
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
                Installation installation = new Installation(
                        rs.getInt("ID"),
                        rs.getInt("CustomerId"),
                        rs.getInt("TechnicianId"),
                        rs.getString("Description"),
                        rs.getInt("InstallationTypeId")
                );
                installation.setCreatedDate(rs.getDate("InstallationDate").toLocalDate());
                installation.setExpiryDate(rs.getDate("ExpiryDate").toLocalDate());
                installations.add(installation);
            }
        }
        return installations;

    }

    @Override
    public void updateInstallation(Installation installation) throws SQLException {
        String sql = "UPDATE Installation SET CustomerId = ?, TechnicianId = ?, Description = ?, InstallationTypeId = ?, InstallationDate = ?, ExpiryDate = ? WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, installation.getCustomerId());
            pstmt.setInt(2, installation.getTechnicianId());
            pstmt.setString(3, installation.getDescription());
            pstmt.setInt(4, installation.getInstallationTypeId());
            pstmt.setDate(5, java.sql.Date.valueOf(installation.getCreatedDate()));
            pstmt.setDate(6, java.sql.Date.valueOf(installation.getExpiryDate()));
            pstmt.setInt(7, installation.getId());
            pstmt.executeUpdate();
        }
    }
    @Override
    public void deleteInstallation(int id) throws SQLException {
        String sql = "DELETE FROM Installation WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

}