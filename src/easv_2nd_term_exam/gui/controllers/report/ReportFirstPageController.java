package easv_2nd_term_exam.gui.controllers.report;

import easv_2nd_term_exam.gui.controllers.ControllerManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportFirstPageController implements Initializable {

    @FXML
    private Label createDateLabel;

    @FXML
    private Label customerAddressLabel;

    @FXML
    private Label customerEmailLabel;

    @FXML
    private Label customerIdLabel;

    @FXML
    private Label customerNameLabel;

    @FXML
    private Label expiryDateLabel;

    @FXML
    private Label installationIdLabel;

    @FXML
    private Label installationTypeLabel;

    @FXML
    private Label technicianNameLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setReportFirstPageController(this);

    }

    public Label getCreateDateLabel() {
        return createDateLabel;
    }

    public Label getCustomerAddressLabel() {
        return customerAddressLabel;
    }

    public Label getCustomerEmailLabel() {
        return customerEmailLabel;
    }

    public Label getCustomerIdLabel() {
        return customerIdLabel;
    }

    public Label getCustomerNameLabel() {
        return customerNameLabel;
    }

    public Label getExpiryDateLabel() {
        return expiryDateLabel;
    }

    public Label getInstallationIdLabel() {
        return installationIdLabel;
    }

    public Label getInstallationTypeLabel() {
        return installationTypeLabel;
    }

    public Label getTechnicianNameLabel() {
        return technicianNameLabel;
    }
}
