package easv_2nd_term_exam.gui.controllers.report;

import easv_2nd_term_exam.gui.controllers.ControllerManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportThirdPageController implements Initializable {

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
    private ImageView firstImageView;

    @FXML
    private Label installationIdLabel;

    @FXML
    private ImageView secondImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setReportThirdPageController(this);
    }
}
