package easv_2nd_term_exam.bll;

import easv_2nd_term_exam.be.Report;
import easv_2nd_term_exam.dal.ReportDAO;

import java.util.List;

public class ProjectManager {

    private ReportDAO reportDAO;

    public ProjectManager() {
        reportDAO = new ReportDAO();
    }

    public List<Report> getAllTechnicianReports(int technicianId) {
        return reportDAO.getAllTechnicianReports(technicianId);
    }

    public List<Report> getAllReports() {
        return reportDAO.getAllReports();
    }

    public boolean updateReport(Report report) {
        return reportDAO.updateReport(report);
    }

}
