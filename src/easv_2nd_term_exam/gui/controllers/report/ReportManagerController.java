package easv_2nd_term_exam.gui.controllers.report;

import easv_2nd_term_exam.gui.controllers.ControllerManager;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportManagerController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setReportManagerController(this);
    }
}
