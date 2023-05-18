package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.InstallationType;

import java.sql.SQLException;
import java.util.List;

public interface IInstallationTypeDAO {
    InstallationType createInstallationType(InstallationType installationType) throws SQLException;
    InstallationType getInstallationType(int id) throws SQLException;
    List<InstallationType> getAllInstallationTypes() throws SQLException;
    void updateInstallationType(InstallationType installationType) throws SQLException;
    void deleteInstallationType(int id) throws SQLException;
}