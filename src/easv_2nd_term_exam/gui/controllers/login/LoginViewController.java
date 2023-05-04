package easv_2nd_term_exam.gui.controllers.login;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv_2nd_term_exam.be.Technician;
import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import easv_2nd_term_exam.gui.models.LogInModel;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private ModelManagerLoader modelManagerLoader;
    private ModelManager modelManager;
    private User loggedUser;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelManagerLoader = ModelManagerLoader.getInstance();
        modelManager = modelManagerLoader.getModelManager();

        ControllerManager.getInstance().setLoginViewController(this);


    }

    @FXML
    private void handleLogin(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = null;
        try {
            user = modelManager.getLogInModel().userLogIn(username, password);
            loggedUser = user;

            if (user != null) {
                FXMLLoader loader;
                switch (user.getRole()) {
                    case ADMIN:
                        loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/admin/AdminDashboard.fxml"));
                        break;
                    case TECHNICIAN:
                        loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/technician/TechnicianDashboard.fxml"));
                        break;
                    case PROJECT_MANAGER:
                        loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/projectmanager/ProjectManagerDashboard.fxml"));
                        break;
                    case SALES_PERSON:
                        loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/salesPerson/SalesPersonDashboard.fxml"));
                        break;
                    default:
                        throw new RuntimeException("Unknown user role.");
                }
                Parent root;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                System.out.println("This is " + user.getRole() + " " + user.getName() + "\nContact: " + user.getEmail());
                // Get a reference to the current window
                Window currentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();

                // Close the current window
                currentWindow.hide();
            }
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }
}
