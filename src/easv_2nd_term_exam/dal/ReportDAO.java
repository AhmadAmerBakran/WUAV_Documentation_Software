package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.Report;
import easv_2nd_term_exam.dal.connector.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {
    private DBConnector dbConnector;
    private Connection conn;

    public ReportDAO() {
        dbConnector = new DBConnector();
        try {
            conn = dbConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not establish connection", e);
        }
    }

    public List<Report> getAllTechnicianReports(int technicianId) {
        List<Report> reports = new ArrayList<>();

        String sql = "SELECT c.ID as CustomerId, c.Name as CustomerName, c.Address as CustomerAddress, c.Email as CustomerEmail, " +
                "c.Type as CustomerType, i.ID as InstallationId, i.TechnicianId, e.Name as TechnicianName, " +
                "i.InstallationType, p1.PictureName as Picture1Name, p1.ImageData as Picture1Data, p2.PictureName as Picture2Name, p2.ImageData as Picture2Data " +
                "FROM Customer c " +
                "JOIN Installation i ON c.ID = i.CustomerId " +
                "JOIN Employee e ON i.TechnicianId = e.ID " +
                "JOIN (SELECT *, ROW_NUMBER() OVER (PARTITION BY InstallationId ORDER BY ID) as rn FROM Picture) p1 ON i.ID = p1.InstallationId AND p1.rn = 1 " +
                "LEFT JOIN (SELECT *, ROW_NUMBER() OVER (PARTITION BY InstallationId ORDER BY ID) as rn FROM Picture) p2 ON i.ID = p2.InstallationId AND p2.rn = 2 " +
                "WHERE i.TechnicianId = ?";



        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, technicianId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Report report = new Report();
                report.setCustomerId(rs.getInt("CustomerId"));
                report.setCustomerName(rs.getString("CustomerName"));
                report.setCustomerAddress(rs.getString("CustomerAddress"));
                report.setCustomerEmail(rs.getString("CustomerEmail"));
                report.setCustomerType(rs.getString("CustomerType"));
                report.setInstallationId(rs.getInt("InstallationId"));
                report.setTechnicianId(rs.getInt("TechnicianId"));
                report.setTechnicianName(rs.getString("TechnicianName"));
                report.setInstallationType(rs.getString("InstallationType"));
                report.setPicture1Name(rs.getString("Picture1Name"));
                report.setPicture1Data(rs.getBytes("Picture1Data"));
                report.setPicture2Name(rs.getString("Picture2Name"));
                report.setPicture2Data(rs.getBytes("Picture2Data"));
                reports.add(report);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return reports;
    }

    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();

        String sql = "SELECT c.ID as CustomerId, c.Name as CustomerName, c.Address as CustomerAddress, c.Email as CustomerEmail, " +
                "c.Type as CustomerType, i.ID as InstallationId, i.TechnicianId, e.Name as TechnicianName, " +
                "i.InstallationType, p1.PictureName as Picture1Name, p1.ImageData as Picture1Data, p2.PictureName as Picture2Name, p2.ImageData as Picture2Data " +
                "FROM Customer c " +
                "JOIN Installation i ON c.ID = i.CustomerId " +
                "JOIN Employee e ON i.TechnicianId = e.ID " +
                "JOIN (SELECT *, ROW_NUMBER() OVER (PARTITION BY InstallationId ORDER BY ID) as rn FROM Picture) p1 ON i.ID = p1.InstallationId AND p1.rn = 1 " +
                "LEFT JOIN (SELECT *, ROW_NUMBER() OVER (PARTITION BY InstallationId ORDER BY ID) as rn FROM Picture) p2 ON i.ID = p2.InstallationId AND p2.rn = 2";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Report report = new Report();
                report.setCustomerId(rs.getInt("CustomerId"));
                report.setCustomerName(rs.getString("CustomerName"));
                report.setCustomerAddress(rs.getString("CustomerAddress"));
                report.setCustomerEmail(rs.getString("CustomerEmail"));
                report.setCustomerType(rs.getString("CustomerType"));
                report.setInstallationId(rs.getInt("InstallationId"));
                report.setTechnicianId(rs.getInt("TechnicianId"));
                report.setTechnicianName(rs.getString("TechnicianName"));
                report.setInstallationType(rs.getString("InstallationType"));
                report.setPicture1Name(rs.getString("Picture1Name"));
                report.setPicture1Data(rs.getBytes("Picture1Data"));
                report.setPicture2Name(rs.getString("Picture2Name"));
                report.setPicture2Data(rs.getBytes("Picture2Data"));
                reports.add(report);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reports;
    }


    public boolean updateReport(Report report) {
        try {
            conn.setAutoCommit(false);

            // Update Customer
            String sqlCustomer = "UPDATE Customer " +
                    "SET Name = ?, Address = ?, Email = ?, Type = ? " +
                    "WHERE ID = ?";
            try (PreparedStatement stmtCustomer = conn.prepareStatement(sqlCustomer)) {
                stmtCustomer.setString(1, report.getCustomerName());
                stmtCustomer.setString(2, report.getCustomerAddress());
                stmtCustomer.setString(3, report.getCustomerEmail());
                stmtCustomer.setString(4, report.getCustomerType());
                stmtCustomer.setInt(5, report.getCustomerId());
                stmtCustomer.executeUpdate();
            }

            // Update Installation
            String sqlInstallation = "UPDATE Installation " +
                    "SET TechnicianId = ?, InstallationType = ? " +
                    "WHERE ID = ?";
            try (PreparedStatement stmtInstallation = conn.prepareStatement(sqlInstallation)) {
                stmtInstallation.setInt(1, report.getTechnicianId());
                stmtInstallation.setString(2, report.getInstallationType());
                stmtInstallation.setInt(3, report.getInstallationId());
                stmtInstallation.executeUpdate();
            }

            // Update Picture 1
            String sqlPicture1 = "UPDATE Picture " +
                    "SET PictureName = ?, ImageData = ? " +
                    "WHERE InstallationId = ? AND PictureName = ?";
            try (PreparedStatement stmtPicture1 = conn.prepareStatement(sqlPicture1)) {
                stmtPicture1.setString(1, report.getPicture1Name());
                stmtPicture1.setBytes(2, report.getPicture1Data());
                stmtPicture1.setInt(3, report.getInstallationId());
                stmtPicture1.setString(4, report.getPicture1Name());
                stmtPicture1.executeUpdate();
            }

            // Update Picture 2
            String sqlPicture2 = "UPDATE Picture " +
                    "SET PictureName = ?, ImageData = ? " +
                    "WHERE InstallationId = ? AND PictureName = ?";
            try (PreparedStatement stmtPicture2 = conn.prepareStatement(sqlPicture2)) {
                stmtPicture2.setString(1, report.getPicture2Name());
                stmtPicture2.setBytes(2, report.getPicture2Data());
                stmtPicture2.setInt(3, report.getInstallationId());
                stmtPicture2.setString(4, report.getPicture2Name());
                stmtPicture2.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


}
