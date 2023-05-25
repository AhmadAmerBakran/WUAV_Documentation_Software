package easv_2nd_term_exam.gui.models;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv_2nd_term_exam.be.Technician;
import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.bll.LogInManager;

import java.sql.SQLException;

public class LogInModel {

    private LogInManager logInManager;

    public LogInModel() {
        logInManager = new LogInManager();
    }

    public User userLogIn(String username, String password) throws SQLException {
        return logInManager.userLogIn(username, password);
    }
}