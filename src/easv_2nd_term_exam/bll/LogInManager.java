package easv_2nd_term_exam.bll;

import easv_2nd_term_exam.be.Technician;
import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.dal.LogInDAO;
import easv_2nd_term_exam.dal.interfaces.ILogInDAO;

import java.sql.SQLException;

public class LogInManager {

    private ILogInDAO logInDAO;

    public LogInManager() {

        logInDAO = new LogInDAO();
    }

    public User userLogIn(String username, String password) throws SQLException {
        return logInDAO.userLogIn(username, password);
    }

}