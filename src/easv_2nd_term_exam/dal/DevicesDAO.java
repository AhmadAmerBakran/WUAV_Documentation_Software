package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.Device;
import easv_2nd_term_exam.dal.connector.DBConnector;
import easv_2nd_term_exam.dal.interfaces.IDevicesDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DevicesDAO implements IDevicesDAO {

    private DBConnector dbConnector;

    public DevicesDAO() {
        dbConnector = new DBConnector();
    }

    @Override
    public Device createDevice(Device device) throws SQLException {
        String sql = "INSERT INTO Devices (Name, DeviceTypeId, InstallationId, Username, Password) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, device.getName());
            pstmt.setInt(2, device.getDeviceTypeId());
            pstmt.setInt(3, device.getInstallationId());
            pstmt.setString(4, device.getUsername());
            pstmt.setString(5, device.getPassword());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    device.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating device failed, no ID obtained.");
                }
            }
        }
        return device;
    }

    @Override
    public Device getDevice(int id) throws SQLException {
        Device device = null;
        String sql = "SELECT * FROM Devices WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    device = new Device(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getInt("DeviceTypeId"),
                            rs.getInt("InstallationId"),
                            rs.getString("Username"),
                            rs.getString("Password")
                    );
                }
            }
        }
        return device;
    }

    @Override
    public List<Device> getAllDevices() throws SQLException {
        List<Device> devices = new ArrayList<>();
        String sql = "SELECT * FROM Devices";
        try (Connection connection = dbConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Device device = new Device(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("DeviceTypeId"),
                        rs.getInt("InstallationId"),
                        rs.getString("Username"),
                        rs.getString("Password")
                );
                devices.add(device);
            }
        }
        return devices;
    }

    @Override
    public void updateDevice(Device device) throws SQLException {
        String sql = "UPDATE Devices SET Name = ?, DeviceTypeId = ?, InstallationId = ?, Username = ?, Password = ? WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, device.getName());
            pstmt.setInt(2, device.getDeviceTypeId());
            pstmt.setInt(3, device.getInstallationId());
            pstmt.setString(4, device.getUsername());
            pstmt.setString(5, device.getPassword());
            pstmt.setInt(6, device.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteDevice(int id) throws SQLException {
        String sql = "DELETE FROM Devices WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Device> getDevicesByInstallationId(int installationId) throws SQLException {
        List<Device> devices = new ArrayList<>();
        String sql = "SELECT * FROM Devices WHERE InstallationId = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, installationId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Device device = new Device(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getInt("DeviceTypeId"),
                            rs.getInt("InstallationId"),
                            rs.getString("Username"),
                            rs.getString("Password")
                    );
                    devices.add(device);
                }
            }
        }
        return devices;
    }

    @Override
    public List<Device> createDevices(List<Device> devices) throws SQLException {
        List<Device> createdDevices = new ArrayList<>();
        String sql = "INSERT INTO Devices (Name, DeviceTypeId, InstallationId, Username, Password) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            for (Device device : devices) {
                pstmt.setString(1, device.getName());
                pstmt.setInt(2, device.getDeviceTypeId());
                pstmt.setInt(3, device.getInstallationId());
                pstmt.setString(4, device.getUsername());
                pstmt.setString(5, device.getPassword());

                pstmt.executeUpdate(); // Execute each prepared statement individually

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        device.setId(generatedKeys.getInt(1));
                        createdDevices.add(device);
                    } else {
                        throw new SQLException("Creating device failed, no ID obtained.");
                    }
                }

                pstmt.clearParameters(); // Clear the parameters for the next iteration
            }
        }
        return createdDevices;
    }

}
