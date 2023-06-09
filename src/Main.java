import easv_2nd_term_exam.dal.AdminDAO;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAO();

        if (!adminDAO.hasAdmins()) {
            adminDAO.createAdminUser("Ahmad Amer Bakran", "nights.maestro@gmail.com", "admin", "admin");
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv_2nd_term_exam/gui/views/login/LoginView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login to WUAV");
        primaryStage.setScene(scene);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        primaryStage.show();
        fadeIn.play();
    }
}
