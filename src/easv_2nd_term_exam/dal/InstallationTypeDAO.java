package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.InstallationType;
import easv_2nd_term_exam.dal.connector.DBConnector;
import easv_2nd_term_exam.dal.interfaces.IInstallationTypeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstallationTypeDAO implements IInstallationTypeDAO {
    private DBConnector dbConnector;

    public InstallationTypeDAO() {
        dbConnector = new DBConnector();
    }

    @Override
    public InstallationType createInstallationType(InstallationType installationType) throws SQLException {
        String sql = "INSERT INTO InstallationType (Name, IsDeleted) VALUES (?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, installationType.getName());
            pstmt.setBoolean(2, installationType.isDeleted());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    installationType.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating installation type failed, no ID obtained.");
                }
            }
        }
        return installationType;
    }

    @Override
    public InstallationType getInstallationType(int id) throws SQLException {
        InstallationType installationType = null;
        String sql = "SELECT * FROM InstallationType WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    installationType = new InstallationType(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getBoolean("IsDeleted")
                    );
                }
            }
        }
        return installationType;
    }

    @Override
    public List<InstallationType> getAllInstallationTypes() throws SQLException {
        List<InstallationType> installationTypes = new ArrayList<>();
        String sql = "SELECT * FROM InstallationType";
        try (Connection connection = dbConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                InstallationType installationType = new InstallationType(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getBoolean("IsDeleted")
                );
                installationTypes.add(installationType);
            }
        }
        return installationTypes;
    }

    @Override
    public void updateInstallationType(InstallationType installationType) throws SQLException {
        String sql = "UPDATE InstallationType SET Name = ?, IsDeleted = ? WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, installationType.getName());
            pstmt.setBoolean(2, installationType.isDeleted());
            pstmt.setInt(3, installationType.getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteInstallationType(int id) throws SQLException {
        String sql = "DELETE FROM InstallationType WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

}
