package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.dal.connector.DBConnector;
import easv_2nd_term_exam.dal.interfaces.IReportDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO implements IReportDAO {
    private  DBConnector dbConnector;

    public ReportDAO() {
        dbConnector = new DBConnector();
    }

    public void setDbConnector(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public List<Report> getAllTechnicianReports(int technicianId) {
        List<Report> reportList = new ArrayList<>();
        String query1 = "SELECT c.ID AS customerId, c.Name AS customerName, c.Address AS customerAddress, " +
                "c.Email AS customerEmail, c.Type AS customerType, c.BillingAddress AS billingAddress, " +
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
                report.setBillingAddress(rs1.getString("billingAddress"));
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
                    device.setId(rs2.getInt("ID"));
                    device.setName(rs2.getString("Name"));
                    device.setUsername(rs2.getString("Username"));
                    device.setPassword(rs2.getString("Password"));
                    devices.add(device);
                }
                report.setDevices(devices);


                ps3.setInt(1, report.getInstallationId());
                ResultSet rs3 = ps3.executeQuery();
                List<Picture> pictures = new ArrayList<>();
                while (rs3.next()) {
                    Picture picture = new Picture();

                    pictures.add(picture);
                }
                report.setPictures(pictures);


                reportList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportList;
    }



    @Override
    public List<Report> getAllReports() {
        List<Report> reportList = new ArrayList<>();
        String query1 = "SELECT c.ID AS customerId, c.Name AS customerName, c.Address AS customerAddress, " +
                "c.Email AS customerEmail, c.Type AS customerType, c.BillingAddress AS billingAddress, " +
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

    @Override
    public List<Report> getExpiringReports(int daysBeforeExpiry) {
        List<Report> reportList = new ArrayList<>();
        String query1 = "SELECT c.ID AS customerId, c.Name AS customerName, c.Address AS customerAddress, " +
                "c.Email AS customerEmail, c.Type AS customerType, c.BillingAddress AS billingAddress, " +
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
        report.setBillingAddress(rs1.getString("billingAddress"));
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
            device.setId(rs2.getInt("ID"));
            device.setName(rs2.getString("Name"));
            device.setUsername(rs2.getString("Username"));
            device.setPassword(rs2.getString("Password"));
            devices.add(device);
        }

        report.setDevices(devices);

        ps3.setInt(1, report.getInstallationId());
        ResultSet rs3 = ps3.executeQuery();
        List<Picture> pictures = new ArrayList<>();
        while (rs3.next()) {
            Picture picture = new Picture();

            pictures.add(picture);
        }
        report.setPictures(pictures);

        return report;
    }


    @Override
    public boolean updateReport(Report report) {
        Connection connection = null;
        String sqlDeleteDevices = "DELETE FROM Devices WHERE InstallationId = ?";
        String sqlInsertDevice = "INSERT INTO Devices (Name, DeviceTypeId, Username, Password, InstallationId) VALUES (?, ?, ?, ?, ?)";
        String sqlDeletePictures = "DELETE FROM Picture WHERE InstallationId = ?";
        String sqlInsertPicture = "INSERT INTO Picture (PictureName, ImageData, InstallationId) VALUES (?, ?, ?)";
        String sqlUpdateInstallation = "UPDATE Installation SET Description = ?, InstallationDate = ?, ExpiryDate = ?, InstallationTypeId = ? WHERE ID = ?";
        String sqlUpdateCustomer = "UPDATE Customer SET Name = ?, Email = ?, Address = ?, Type = ?, BillingAddress = ? WHERE ID = ?";

        try {
            connection = dbConnector.getConnection();

            try (PreparedStatement statementDeleteDevices = connection.prepareStatement(sqlDeleteDevices);
                 PreparedStatement statementInsertDevice = connection.prepareStatement(sqlInsertDevice);
                 PreparedStatement statementDeletePictures = connection.prepareStatement(sqlDeletePictures);
                 PreparedStatement statementInsertPicture = connection.prepareStatement(sqlInsertPicture);
                 PreparedStatement statementUpdateInstallation = connection.prepareStatement(sqlUpdateInstallation);
                 PreparedStatement statementUpdateCustomer = connection.prepareStatement(sqlUpdateCustomer)) {


                connection.setAutoCommit(false);


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


                if (report.getPictures() != null) {
                    statementDeletePictures.setInt(1, report.getInstallationId());
                    statementDeletePictures.executeUpdate();

                    for (Picture picture : report.getPictures()) {
                        statementInsertPicture.setString(1, picture.getPictureName());
                        statementInsertPicture.setBytes(2, picture.getImageData());
                        statementInsertPicture.setInt(3, report.getInstallationId());
                        statementInsertPicture.addBatch();
                    }
                    statementInsertPicture.executeBatch();
                }


                statementUpdateCustomer.setString(1, report.getCustomerName());
                statementUpdateCustomer.setString(2, report.getCustomerEmail());
                statementUpdateCustomer.setString(3, report.getCustomerAddress());
                statementUpdateCustomer.setString(4, report.getCustomerType());
                statementUpdateCustomer.setString(5, report.getBillingAddress());
                statementUpdateCustomer.setInt(6, report.getCustomerId());
                statementUpdateCustomer.executeUpdate();


                statementUpdateInstallation.setString(1, report.getDescription());
                statementUpdateInstallation.setDate(2, java.sql.Date.valueOf(report.getCreatedDate()));
                statementUpdateInstallation.setDate(3, java.sql.Date.valueOf(report.getExpiryDate()));
                statementUpdateInstallation.setInt(4, report.getInstallationTypeId());
                statementUpdateInstallation.setInt(5, report.getInstallationId());
                int affectedRows = statementUpdateInstallation.executeUpdate();


                connection.commit();

                return affectedRows == 1;

            }
        } catch (SQLException e) {
            if (connection != null) {
                try {

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

                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    @Override
    public boolean deleteReport(int installationId) {
        Connection connection = null;
        try {
            connection = dbConnector.getConnection();


            connection.setAutoCommit(false);


            String sqlDeleteDevices = "DELETE FROM Devices WHERE InstallationId = ?";
            PreparedStatement statementDeleteDevices = connection.prepareStatement(sqlDeleteDevices);
            statementDeleteDevices.setInt(1, installationId);
            statementDeleteDevices.executeUpdate();


            String sqlDeletePictures = "DELETE FROM Picture WHERE InstallationId = ?";
            PreparedStatement statementDeletePictures = connection.prepareStatement(sqlDeletePictures);
            statementDeletePictures.setInt(1, installationId);
            statementDeletePictures.executeUpdate();


            String sqlDeleteInstallation = "DELETE FROM Installation WHERE ID = ?";
            PreparedStatement statementDeleteInstallation = connection.prepareStatement(sqlDeleteInstallation);
            statementDeleteInstallation.setInt(1, installationId);
            int affectedRows = statementDeleteInstallation.executeUpdate();

            connection.commit();

            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}


