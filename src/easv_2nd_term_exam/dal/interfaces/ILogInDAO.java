package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.User;

import java.sql.SQLException;

public interface ILogInDAO {

    User userLogIn(String username, String password) throws SQLException;
}
