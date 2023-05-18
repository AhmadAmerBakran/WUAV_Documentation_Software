package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.DeviceType;
import easv_2nd_term_exam.dal.connector.DBConnector;
import easv_2nd_term_exam.dal.interfaces.IDeviceTypeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceTypeDAO implements IDeviceTypeDAO {

    private DBConnector dbConnector;

    public DeviceTypeDAO() {
        dbConnector = new DBConnector();
    }

    @Override
    public DeviceType createDeviceType(DeviceType deviceType) throws SQLException {
        String sql = "INSERT INTO DeviceType (Name, IsDeleted) VALUES (?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, deviceType.getName());
            pstmt.setBoolean(2, deviceType.isDeleted());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    deviceType.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating device type failed, no ID obtained.");
                }
            }
        }
        return deviceType;
    }

    @Override
    public DeviceType getDeviceType(int id) throws SQLException {
        DeviceType deviceType = null;
        String sql = "SELECT * FROM DeviceType WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    deviceType = new DeviceType(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getBoolean("IsDeleted")
                    );
                }
            }
        }
        return deviceType;
    }

    @Override
    public List<DeviceType> getAllDeviceTypes() throws SQLException {
        List<DeviceType> deviceTypes = new ArrayList<>();
        String sql = "SELECT * FROM DeviceType";
        try (Connection connection = dbConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DeviceType deviceType = new DeviceType(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getBoolean("IsDeleted")
                );
                deviceTypes.add(deviceType);
            }
        }
        return deviceTypes;
    }

    @Override
    public void updateDeviceType(DeviceType deviceType) throws SQLException {
        String sql = "UPDATE DeviceType SET Name = ?, IsDeleted = ? WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, deviceType.getName());
            pstmt.setBoolean(2, deviceType.isDeleted());
            pstmt.setInt(3, deviceType.getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteDeviceType(int id) throws SQLException {
        String sql = "DELETE FROM DeviceType WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
