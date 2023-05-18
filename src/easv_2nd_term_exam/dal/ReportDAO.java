package easv_2nd_term_exam.dal;

        import easv_2nd_term_exam.be.*;
        import easv_2nd_term_exam.dal.connector.DBConnector;

        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class ReportDAO {
    private final DBConnector dbConnector;

    public ReportDAO() {
        dbConnector = new DBConnector();
    }

    public List<Report> getAllTechnicianReports(int technicianId) {
        List<Report> reportList = new ArrayList<>();
        String query1 = "SELECT c.ID AS customerId, c.Name AS customerName, c.Address AS customerAddress, " +
                "c.Email AS customerEmail, c.Type AS customerType, " +
                "i.ID AS installationId, e.ID AS technicianId, e.Name AS technicianName, " +
                "it.Name AS installationType, i.Description AS description, " +
                "i.InstallationDate AS createdDate, i.ExpiryDate AS expiryDate " +
                "FROM Customer c " +
                "INNER JOIN Installation i ON c.ID = i.CustomerId " +
                "INNER JOIN Employee e ON e.ID = i.TechnicianId " +
                "INNER JOIN InstallationType it ON it.ID = i.InstallationTypeId " +
                "WHERE e.ID = ?";
        String query2 = "SELECT d.ID, d.Name, dt.Name AS deviceType, d.Username, d.Password " +
                "FROM Devices d " +
                "INNER JOIN DeviceType dt ON dt.ID = d.DeviceTypeId " +
                "WHERE d.InstallationId = ?";
        String query3 = "SELECT p.ID, p.PictureName, p.ImageData " +
                "FROM Picture p " +
                "WHERE p.InstallationId = ?";

        try (
                Connection conn = dbConnector.getConnection();
                PreparedStatement ps1 = conn.prepareStatement(query1);
                PreparedStatement ps2 = conn.prepareStatement(query2);
                PreparedStatement ps3 = conn.prepareStatement(query3)
        ) {
            ps1.setInt(1, technicianId);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Report report = new Report();
                report.setCustomerId(rs1.getInt("customerId"));
                report.setCustomerName(rs1.getString("customerName"));
                report.setCustomerAddress(rs1.getString("customerAddress"));
                report.setCustomerEmail(rs1.getString("customerEmail"));
                report.setCustomerType(rs1.getString("customerType"));
                report.setInstallationId(rs1.getInt("installationId"));
                report.setTechnicianId(rs1.getInt("technicianId"));
                report.setTechnicianName(rs1.getString("technicianName"));
                report.setInstallationType(rs1.getString("installationType"));
                report.setDescription(rs1.getString("description"));
                report.setCreatedDate(rs1.getDate("createdDate").toLocalDate());
                report.setExpiryDate(rs1.getDate("expiryDate").toLocalDate());

                // Fetch Devices related to the Installation
                ps2.setInt(1, report.getInstallationId());
                ResultSet rs2 = ps2.executeQuery();
                List<Device> devices = new ArrayList<>();
                while (rs2.next()) {
                    Device device = new Device();
                    // Populate device object
                    devices.add(device);
                }
                report.setDevices(devices);

                // Fetch Pictures related to the Installation
                ps3.setInt(1, report.getInstallationId());
                ResultSet rs3 = ps3.executeQuery();
                List<Picture> pictures = new ArrayList<>();
                while (rs3.next()) {
                    Picture picture = new Picture();
                    // Populate picture object
                    pictures.add(picture);
                }
                report.setPictures(pictures);

                // Add report to list
                reportList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportList;
    }



    public List<Report> getAllReports() {
        List<Report> reportList = new ArrayList<>();
        String query1 = "SELECT c.ID AS customerId, c.Name AS customerName, c.Address AS customerAddress, " +
                "c.Email AS customerEmail, c.Type AS customerType, " +
                "i.ID AS installationId, e.ID AS technicianId, e.Name AS technicianName, " +
                "it.Name AS installationType, i.Description AS description, " +
                "i.InstallationDate AS createdDate, i.ExpiryDate AS expiryDate " +
                "FROM Customer c " +
                "INNER JOIN Installation i ON c.ID = i.CustomerId " +
                "INNER JOIN Employee e ON e.ID = i.TechnicianId " +
                "INNER JOIN InstallationType it ON it.ID = i.InstallationTypeId";
        String query2 = "SELECT d.ID, d.Name, dt.Name AS deviceType, d.Username, d.Password " +
                "FROM Devices d " +
                "INNER JOIN DeviceType dt ON dt.ID = d.DeviceTypeId " +
                "WHERE d.InstallationId = ?";
        String query3 = "SELECT p.ID, p.PictureName, p.ImageData " +
                "FROM Picture p " +
                "WHERE p.InstallationId = ?";

        try (
                Connection conn = dbConnector.getConnection();
                PreparedStatement ps1 = conn.prepareStatement(query1);
                PreparedStatement ps2 = conn.prepareStatement(query2);
                PreparedStatement ps3 = conn.prepareStatement(query3)
        ) {
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Report report = fetchAndSetReportData(rs1, ps2, ps3);
                reportList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportList;
    }

    public List<Report> getExpiringReports(int daysBeforeExpiry) {
        List<Report> reportList = new ArrayList<>();
        String query1 = "SELECT c.ID AS customerId, c.Name AS customerName, c.Address AS customerAddress, " +
                "c.Email AS customerEmail, c.Type AS customerType, " +
                "i.ID AS installationId, e.ID AS technicianId, e.Name AS technicianName, " +
                "it.Name AS installationType, i.Description AS description, " +
                "i.InstallationDate AS createdDate, i.ExpiryDate AS expiryDate " +
                "FROM Customer c " +
                "INNER JOIN Installation i ON c.ID = i.CustomerId " +
                "INNER JOIN Employee e ON e.ID = i.TechnicianId " +
                "INNER JOIN InstallationType it ON it.ID = i.InstallationTypeId " +
                "WHERE DATEDIFF(DAY, GETDATE(), i.ExpiryDate) <= ?";
        String query2 = "SELECT d.ID, d.Name, dt.Name AS deviceType, d.Username, d.Password " +
                "FROM Devices d " +
                "INNER JOIN DeviceType dt ON dt.ID = d.DeviceTypeId " +
                "WHERE d.InstallationId = ?";
        String query3 = "SELECT p.ID, p.PictureName, p.ImageData " +
                "FROM Picture p " +
                "WHERE p.InstallationId = ?";

        try (
                Connection conn = dbConnector.getConnection();
                PreparedStatement ps1 = conn.prepareStatement(query1);
                PreparedStatement ps2 = conn.prepareStatement(query2);
                PreparedStatement ps3 = conn.prepareStatement(query3)
        ) {
            ps1.setInt(1, daysBeforeExpiry);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Report report = fetchAndSetReportData(rs1, ps2, ps3);
                reportList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportList;
    }

    private Report fetchAndSetReportData(ResultSet rs1, PreparedStatement ps2, PreparedStatement ps3) throws SQLException {
        Report report = new Report();
        report.setCustomerId(rs1.getInt("customerId"));
        report.setCustomerName(rs1.getString("customerName"));
        report.setCustomerAddress(rs1.getString("customerAddress"));
        report.setCustomerEmail(rs1.getString("customerEmail"));
        report.setCustomerType(rs1.getString("customerType"));
        report.setInstallationId(rs1.getInt("installationId"));
        report.setTechnicianId(rs1.getInt("technicianId"));
        report.setTechnicianName(rs1.getString("technicianName"));
        report.setInstallationType(rs1.getString("installationType"));
        report.setDescription(rs1.getString("description"));
        report.setCreatedDate(rs1.getDate("createdDate").toLocalDate());
        report.setExpiryDate(rs1.getDate("expiryDate").toLocalDate());

        ps2.setInt(1, report.getInstallationId());
        ResultSet rs2 = ps2.executeQuery();
        List<Device> devices = new ArrayList<>();
        while (rs2.next()) {
            Device device = new Device();
            // Populate device object
            devices.add(device);
        }
        report.setDevices(devices);

        ps3.setInt(1, report.getInstallationId());
        ResultSet rs3 = ps3.executeQuery();
        List<Picture> pictures = new ArrayList<>();
        while (rs3.next()) {
            Picture picture = new Picture();
            // Populate picture object
            pictures.add(picture);
        }
        report.setPictures(pictures);

        return report;
    }


    public boolean updateReport(Report report) {
        Connection connection = null;
        String sqlDeleteDevices = "DELETE FROM Devices WHERE InstallationId = ?";
        String sqlInsertDevice = "INSERT INTO Devices (Name, DeviceTypeId, Username, Password, InstallationId) VALUES (?, ?, ?, ?, ?)";
        String sqlDeletePictures = "DELETE FROM Picture WHERE InstallationId = ?";
        String sqlInsertPicture = "INSERT INTO Picture (PictureName, ImageData, InstallationId) VALUES (?, ?, ?)";
        String sqlUpdateInstallation = "UPDATE Installation SET Description = ? WHERE ID = ?";

        try {
            connection = dbConnector.getConnection();

            try (PreparedStatement statementDeleteDevices = connection.prepareStatement(sqlDeleteDevices);
                 PreparedStatement statementInsertDevice = connection.prepareStatement(sqlInsertDevice);
                 PreparedStatement statementDeletePictures = connection.prepareStatement(sqlDeletePictures);
                 PreparedStatement statementInsertPicture = connection.prepareStatement(sqlInsertPicture);
                 PreparedStatement statementUpdateInstallation = connection.prepareStatement(sqlUpdateInstallation)) {

                // Turn off auto-commit
                connection.setAutoCommit(false);

                // Delete existing devices and add new ones if new devices are not null
                if (report.getDevices() != null) {
                    statementDeleteDevices.setInt(1, report.getInstallationId());
                    statementDeleteDevices.executeUpdate();

                    for (Device device : report.getDevices()) {
                        statementInsertDevice.setString(1, device.getName());
                        statementInsertDevice.setInt(2, device.getDeviceTypeId());
                        statementInsertDevice.setString(3, device.getUsername());
                        statementInsertDevice.setString(4, device.getPassword());
                        statementInsertDevice.setInt(5, report.getInstallationId());
                        statementInsertDevice.addBatch();
                    }
                    statementInsertDevice.executeBatch();
                }

                // Delete existing pictures and add new ones if new pictures are not null
                if (report.getPictures() != null) {
                    statementDeletePictures.setInt(1, report.getInstallationId());
                    statementDeletePictures.executeUpdate();

                    for (Picture picture : report.getPictures()) {
                        statementInsertPicture.setString(1, picture.getPictureName());
                        statementInsertPicture.setBytes(2, picture.getImageData()); // Assuming byte array for picture
                        statementInsertPicture.setInt(3, report.getInstallationId());
                        statementInsertPicture.addBatch();
                    }
                    statementInsertPicture.executeBatch();
                }

                // Update report
                statementUpdateInstallation.setString(1, report.getDescription());
                statementUpdateInstallation.setInt(2, report.getInstallationId());
                int affectedRows = statementUpdateInstallation.executeUpdate();

                // Commit changes
                connection.commit();

                return affectedRows == 1;

            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    // Rollback changes on exception
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                try {
                    // Close the connection
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }





    public boolean deleteReport(int installationId) {
        Connection connection = null;
        try {
            connection = dbConnector.getConnection();

            // Turn off auto-commit
            connection.setAutoCommit(false);

            // Delete linked devices
            String sqlDeleteDevices = "DELETE FROM Devices WHERE InstallationId = ?";
            PreparedStatement statementDeleteDevices = connection.prepareStatement(sqlDeleteDevices);
            statementDeleteDevices.setInt(1, installationId);
            statementDeleteDevices.executeUpdate();

            // Delete linked pictures
            String sqlDeletePictures = "DELETE FROM Picture WHERE InstallationId = ?";
            PreparedStatement statementDeletePictures = connection.prepareStatement(sqlDeletePictures);
            statementDeletePictures.setInt(1, installationId);
            statementDeletePictures.executeUpdate();

            // Delete installation
            String sqlDeleteInstallation = "DELETE FROM Installation WHERE ID = ?";
            PreparedStatement statementDeleteInstallation = connection.prepareStatement(sqlDeleteInstallation);
            statementDeleteInstallation.setInt(1, installationId);
            int affectedRows = statementDeleteInstallation.executeUpdate();

            // Commit changes
            connection.commit();

            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    // Rollback changes on exception
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    // Turn auto-commit back on
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}


