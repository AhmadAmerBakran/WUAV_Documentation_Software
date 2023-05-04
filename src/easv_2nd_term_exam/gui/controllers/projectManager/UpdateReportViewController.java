package easv_2nd_term_exam.gui.controllers.projectManager;

import easv_2nd_term_exam.gui.controllers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateReportViewController implements Initializable {

    @FXML
    private TextField customerAddressField;

    @FXML
    private TextField customerEmailField;

    @FXML
    private TextField customerNameField;

    @FXML
    private ComboBox<?> customerTypeBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField devicePasswordField;

    @FXML
    private TextField deviceUsernameField;

    @FXML
    private Label diagramPathLabel;

    @FXML
    private ImageView drawingView;

    @FXML
    private ComboBox<?> installationTypeBox;

    @FXML
    private Button removeDiagramBtn;

    @FXML
    private TextField techEmailField;

    @FXML
    private TextField techIdField;

    @FXML
    private TextField techNameField;

    @FXML
    private Label uploadedPictureLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerManager.getInstance().setUpdateReportViewController(this);
    }
    @FXML
    void cancelUpdating(ActionEvent event) {

    }

    @FXML
    void openDrawingLayout(ActionEvent event) {

    }

    @FXML
    void openFileChooser(ActionEvent event) {

    }

    @FXML
    void removeAllPhotos(ActionEvent event) {

    }

    @FXML
    void submitUpdating(ActionEvent event) {

    }


}
