package easv_2nd_term_exam.gui.models;

import easv_2nd_term_exam.be.Report;
import easv_2nd_term_exam.bll.ProjectManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReportModel {

    private final ProjectManager projectManager;
    private final ObservableList<Report> technicianReports;
    private final ObservableList<Report> allReports;

    public ReportModel() {
        projectManager = new ProjectManager();
        technicianReports = FXCollections.observableArrayList();
        allReports = FXCollections.observableArrayList();
    }

    public ObservableList<Report> getAllTechnicianReports(int technicianId) {
        technicianReports.setAll(projectManager.getAllTechnicianReports(technicianId));
        return technicianReports;
    }

    public ObservableList<Report> getAllReports() {
        allReports.setAll(projectManager.getAllReports());
        return allReports;
    }

    public boolean updateReport(Report report) {
        boolean result = projectManager.updateReport(report);
        if (result) {
            int index = allReports.indexOf(report);
            if (index >= 0) {
                allReports.set(index, report);
            }
        }
        return result;
    }
}
