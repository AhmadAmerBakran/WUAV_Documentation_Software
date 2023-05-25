package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.Report;

import java.sql.SQLException;
import java.util.List;

public interface IReportDAO {
    List<Report> getAllTechnicianReports(int technicianId) throws SQLException;
    List<Report> getAllReports() throws SQLException;
    List<Report> getExpiringReports(int daysBeforeExpiry) throws SQLException;
    boolean updateReport(Report report) throws SQLException;
    boolean deleteReport(int installationId) throws SQLException;
}
