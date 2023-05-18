package easv_2nd_term_exam.bll;

import easv_2nd_term_exam.dal.InstallationTypeDAO;
import easv_2nd_term_exam.be.InstallationType;

import java.sql.SQLException;
import java.util.List;

public class InstallationTypeManager {

    private InstallationTypeDAO installationTypeDAO;

    public InstallationTypeManager() {
        installationTypeDAO = new InstallationTypeDAO();
    }

    public InstallationType createInstallationType(InstallationType installationType) {
        try {
            return installationTypeDAO.createInstallationType(installationType);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public InstallationType getInstallationTypeById(int id) {
        try {
            return installationTypeDAO.getInstallationType(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<InstallationType> getAllInstallationTypes() {
        try {
            return installationTypeDAO.getAllInstallationTypes();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void updateInstallationType(InstallationType installationType) {
        try {
            installationTypeDAO.updateInstallationType(installationType);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteInstallationType(int id) {
        try {
            installationTypeDAO.deleteInstallationType(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
