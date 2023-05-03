package easv_2nd_term_exam.bll;

import easv_2nd_term_exam.be.Installation;
import easv_2nd_term_exam.dal.InstallationDAO;
import easv_2nd_term_exam.dal.interfaces.IInstallationDAO;

import java.sql.SQLException;
import java.util.List;

public class InstallationManager {

    private IInstallationDAO installationDAO;

    public InstallationManager() {
        installationDAO = new InstallationDAO();
    }

    public Installation createInstallation(Installation installation) throws Exception {
        return installationDAO.createInstallation(installation);
    }

    public Installation getInstallation(int id) throws Exception {
        return installationDAO.getInstallation(id);
    }

    public List<Installation> getAllInstallations() throws Exception {
        return installationDAO.getAllInstallations();
    }

    public void updateInstallation(Installation installation) throws Exception {
        installationDAO.updateInstallation(installation);
    }

    public void deleteInstallation(int id) throws Exception {
        installationDAO.deleteInstallation(id);
    }
}
