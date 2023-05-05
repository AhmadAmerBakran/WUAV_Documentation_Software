package easv_2nd_term_exam.gui.controllers.report;

import easv_2nd_term_exam.gui.controllers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportHolderController implements Initializable {

    @FXML
    private VBox reportPagesHolder;
    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setReportHolderController(this);
        loadFXMLPages();
    }

    private void loadFXMLPages() {
        try {
            Parent reportFirstPage = FXMLLoader.load(getClass().getResource("/easv_2nd_term_exam/gui/views/report/ReportFirstPage.fxml"));
            Parent reportSecondPage = FXMLLoader.load(getClass().getResource("/easv_2nd_term_exam/gui/views/report/ReportSecondPage.fxml"));
            Parent reportThirdPage = FXMLLoader.load(getClass().getResource("/easv_2nd_term_exam/gui/views/report/ReportThirdPage.fxml"));

            reportPagesHolder.getChildren().addAll(reportFirstPage, reportSecondPage, reportThirdPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeReviewing(ActionEvent event) {

    }

    @FXML
    private void downloadPDF(ActionEvent event) {

    }
}
