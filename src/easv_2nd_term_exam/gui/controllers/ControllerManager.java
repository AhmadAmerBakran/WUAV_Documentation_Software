package easv_2nd_term_exam.gui.controllers;

import easv_2nd_term_exam.gui.controllers.login.LoginViewController;
import easv_2nd_term_exam.gui.controllers.technician.DrawingLayoutController;
import easv_2nd_term_exam.gui.controllers.technician.TechnicianDashboardController;

public class ControllerManager {

    private static ControllerManager instance;
    private TechnicianDashboardController technicianDashboardController;
    private DrawingLayoutController drawingLayoutController;
    private LoginViewController loginViewController;

    private ControllerManager() {
    }

    public static ControllerManager getInstance() {
        if (instance == null) {
            instance = new ControllerManager();
        }
        return instance;
    }

    public void setTechnicianDashboardController(TechnicianDashboardController controller) {
        this.technicianDashboardController = controller;
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
