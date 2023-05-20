package easv_2nd_term_exam.gui.controllers;

import easv_2nd_term_exam.gui.controllers.admin.*;
import easv_2nd_term_exam.gui.controllers.login.LoginViewController;
import easv_2nd_term_exam.gui.controllers.projectManager.ProjectManagerDashboardController;
import easv_2nd_term_exam.gui.controllers.projectManager.UpdateDevicesController;
import easv_2nd_term_exam.gui.controllers.projectManager.UpdateReportViewController;
import easv_2nd_term_exam.gui.controllers.salesPerson.SalesPersonDashboard;
import easv_2nd_term_exam.gui.controllers.technician.AddDeviceController;
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
    private AddCustomerController addCustomerController;
    private AddInstallationTypeController addInstallationTypeController;
    private EditInstallationTypeController editInstallationTypeController;
    private AddDeviceTypeController addDeviceTypeController;
    private AddDeviceController addDeviceController;
    private UpdateDevicesController updateDevicesController;


    private ControllerManager() {
    }

    public static ControllerManager getInstance() {
        if (instance == null) {
            instance = new ControllerManager();
        }
        return instance;
    }

    public UpdateDevicesController getUpdateDevicesController() {
        return updateDevicesController;
    }

    public void setUpdateDevicesController(UpdateDevicesController updateDevicesController) {
        this.updateDevicesController = updateDevicesController;
    }

    public AddDeviceController getAddDeviceController() {
        return addDeviceController;
    }

    public void setAddDeviceController(AddDeviceController addDeviceController) {
        this.addDeviceController = addDeviceController;
    }

    public AddDeviceTypeController getAddDeviceTypeController() {
        return addDeviceTypeController;
    }

    public void setAddDeviceTypeController(AddDeviceTypeController addDeviceTypeController) {
        this.addDeviceTypeController = addDeviceTypeController;
    }

    public EditInstallationTypeController getEditInstallationTypeController() {
        return editInstallationTypeController;
    }

    public void setEditInstallationTypeController(EditInstallationTypeController editInstallationTypeController) {
        this.editInstallationTypeController = editInstallationTypeController;
    }

    public AddInstallationTypeController getAddInstallationTypeController() {
        return addInstallationTypeController;
    }

    public void setAddInstallationTypeController(AddInstallationTypeController addInstallationTypeController) {
        this.addInstallationTypeController = addInstallationTypeController;
    }

    public AddCustomerController getAddCustomerController() {
        return addCustomerController;
    }

    public void setAddCustomerController(AddCustomerController addCustomerController) {
        this.addCustomerController = addCustomerController;
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
