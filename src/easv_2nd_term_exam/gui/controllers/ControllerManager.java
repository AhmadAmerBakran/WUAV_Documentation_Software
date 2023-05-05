package easv_2nd_term_exam.gui.controllers;

import easv_2nd_term_exam.gui.controllers.admin.AddUserController;
import easv_2nd_term_exam.gui.controllers.admin.AdminDashboardController;
import easv_2nd_term_exam.gui.controllers.admin.EditUserController;
import easv_2nd_term_exam.gui.controllers.login.LoginViewController;
import easv_2nd_term_exam.gui.controllers.projectManager.ProjectManagerDashboardController;
import easv_2nd_term_exam.gui.controllers.projectManager.UpdateReportViewController;
import easv_2nd_term_exam.gui.controllers.report.*;
import easv_2nd_term_exam.gui.controllers.salesPerson.SalesPersonDashboard;
import easv_2nd_term_exam.gui.controllers.technician.DrawingLayoutController;
import easv_2nd_term_exam.gui.controllers.technician.TechnicianDashboardController;

public class ControllerManager {

    private static ControllerManager instance;
    private ProjectManagerDashboardController projectManagerDashboardController;
    private UpdateReportViewController updateReportViewController;
    private AddUserController addUserController;
    private EditUserController editUserController;
    private SalesPersonDashboard salesPersonDashboardController;
    private TechnicianDashboardController technicianDashboardController;
    private DrawingLayoutController drawingLayoutController;
    private LoginViewController loginViewController;

    private AdminDashboardController adminDashboardController;
    private ReportManagerController reportManagerController;
    private ReportHolderController reportHolderController;
    private ReportFirstPageController reportFirstPageController;
    private ReportSecondPageController reportSecondPageController;
    private ReportThirdPageController reportThirdPageController;

    public ReportManagerController getReportManagerController() {
        return reportManagerController;
    }

    public void setReportManagerController(ReportManagerController reportManagerController) {
        this.reportManagerController = reportManagerController;
    }

    public ReportHolderController getReportHolderController() {
        return reportHolderController;
    }

    public void setReportHolderController(ReportHolderController reportHolderController) {
        this.reportHolderController = reportHolderController;
    }

    public ReportFirstPageController getReportFirstPageController() {
        return reportFirstPageController;
    }

    public void setReportFirstPageController(ReportFirstPageController reportFirstPageController) {
        this.reportFirstPageController = reportFirstPageController;
    }

    public ReportSecondPageController getReportSecondPageController() {
        return reportSecondPageController;
    }

    public void setReportSecondPageController(ReportSecondPageController reportSecondPageController) {
        this.reportSecondPageController = reportSecondPageController;
    }

    public ReportThirdPageController getReportThirdPageController() {
        return reportThirdPageController;
    }

    public void setReportThirdPageController(ReportThirdPageController reportThirdPageController) {
        this.reportThirdPageController = reportThirdPageController;
    }

    private ControllerManager() {
    }

    public static ControllerManager getInstance() {
        if (instance == null) {
            instance = new ControllerManager();
        }
        return instance;
    }

    public ProjectManagerDashboardController getProjectManagerDashboardController() {
        return projectManagerDashboardController;
    }

    public void setProjectManagerDashboardController(ProjectManagerDashboardController projectManagerDashboardController) {
        this.projectManagerDashboardController = projectManagerDashboardController;
    }

    public UpdateReportViewController getUpdateReportViewController() {
        return updateReportViewController;
    }

    public void setUpdateReportViewController(UpdateReportViewController updateReportViewController) {
        this.updateReportViewController = updateReportViewController;
    }

    public AddUserController getAddUserController() {
        return addUserController;
    }

    public void setAddUserController(AddUserController addUserController) {
        this.addUserController = addUserController;
    }

    public EditUserController getEditUserController() {
        return editUserController;
    }

    public void setEditUserController(EditUserController editUserController) {
        this.editUserController = editUserController;
    }

    public SalesPersonDashboard getSalesPersonDashboardController() {
        return salesPersonDashboardController;
    }

    public void setSalesPersonDashboardController(SalesPersonDashboard salesPersonDashboardController) {
        this.salesPersonDashboardController = salesPersonDashboardController;
    }

    public void setTechnicianDashboardController(TechnicianDashboardController controller) {
        this.technicianDashboardController = controller;
    }


    public AdminDashboardController getAdminDashboardController() {
        return adminDashboardController;
    }

    public void setAdminDashboardController(AdminDashboardController adminDashboardController) {
        this.adminDashboardController = adminDashboardController;
    }

    public void setLoginViewController(LoginViewController loginViewController) {
        this.loginViewController = loginViewController;
    }

    public void setDrawingLayoutController(DrawingLayoutController controller) {
        this.drawingLayoutController = controller;
    }

    public TechnicianDashboardController getTechnicianDashboardController() {
        return technicianDashboardController;
    }

    public DrawingLayoutController getDrawingLayoutController() {
        return drawingLayoutController;
    }

    public LoginViewController getLoginViewController() {
        return loginViewController;
    }
    // Add any other methods for interacting between the controllers here
}
