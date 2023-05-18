package easv_2nd_term_exam.bll;

import easv_2nd_term_exam.be.Report;
import easv_2nd_term_exam.dal.ReportDAO;

import java.sql.SQLException;
import java.util.List;

public class ReportManager {

    private ReportDAO reportDAO;

    public ReportManager() {
        reportDAO = new ReportDAO();
    }

    public List<Report> getAllTechnicianReports(int technicianId) throws SQLException {
        return reportDAO.getAllTechnicianReports(technicianId);
    }

    public List<Report> getAllReports() throws SQLException {
        return reportDAO.getAllReports();
    }

    public List<Report> getExpiringReports(int daysBeforeExpiry) throws SQLException {
        return reportDAO.getExpiringReports(daysBeforeExpiry);
    }

    public boolean updateReport(Report report) throws SQLException {
        return reportDAO.updateReport(report);
    }

    public boolean deleteReport(int installationId) throws SQLException {
        return reportDAO.deleteReport(installationId);
    }

}
