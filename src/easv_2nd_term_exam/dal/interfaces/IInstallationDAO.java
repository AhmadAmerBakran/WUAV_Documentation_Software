package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.Installation;

import java.sql.SQLException;
import java.util.List;

public interface IInstallationDAO {
    Installation createInstallation(Installation installation) throws SQLException;
    Installation getInstallation(int id) throws SQLException;
    List<Installation> getAllInstallations() throws SQLException;
    void updateInstallation(Installation installation) throws SQLException;
    void deleteInstallation(int id) throws SQLException;
}
