package easv_2nd_term_exam.bll;

import easv_2nd_term_exam.be.Technician;
import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.dal.LogInDAO;

public class LogInManager {

    private LogInDAO logInDAO;

    public LogInManager() {

        logInDAO = new LogInDAO();
    }

    public User userLogIn(String username, String password) {
        return logInDAO.userLogIn(username, password);
    }

}