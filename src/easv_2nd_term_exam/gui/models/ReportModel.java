package easv_2nd_term_exam.gui.models;

import easv_2nd_term_exam.be.Report;
import easv_2nd_term_exam.bll.ReportManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReportModel {

    private final ReportManager reportManager;
    private final ObservableList<Report> technicianReports;
    private final ObservableList<Report> allReports;

    public ReportModel() {
        reportManager = new ReportManager();
        technicianReports = FXCollections.observableArrayList();
        allReports = FXCollections.observableArrayList();
    }

    public ObservableList<Report> getAllTechnicianReports(int technicianId) {
        technicianReports.setAll(reportManager.getAllTechnicianReports(technicianId));
        return technicianReports;
    }

    public ObservableList<Report> getAllReports() {
        allReports.setAll(reportManager.getAllReports());
        return allReports;
    }

    public boolean updateReport(Report report) {
        boolean result = reportManager.updateReport(report);
        if (result) {
            int index = allReports.indexOf(report);
            if (index >= 0) {
                allReports.set(index, report);
            }
        }
        return result;
    }
}
