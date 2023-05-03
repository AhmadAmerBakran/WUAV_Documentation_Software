package easv_2nd_term_exam.gui.models;

import easv_2nd_term_exam.be.Installation;
import easv_2nd_term_exam.bll.InstallationManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class InstallationModel {

    private InstallationManager installationManager;
    private ObservableList<Installation> installations;

    public InstallationModel() throws Exception {
        installationManager = new InstallationManager();
        installations = FXCollections.observableArrayList();
        loadInstallations();
    }

    public void loadInstallations() throws Exception {
        List<Installation> installationList = installationManager.getAllInstallations();
        installations.setAll(installationList);
    }

    public ObservableList<Installation> getInstallations() {
        return installations;
    }


    public Installation createInstallation(Installation installation) throws Exception {
        Installation createdInstallation = installationManager.createInstallation(installation);
        installations.add(createdInstallation);
        return createdInstallation;
    }

    public void updateInstallation(Installation installation) throws Exception {
        installationManager.updateInstallation(installation);
        int index = installations.indexOf(installation);
        installations.set(index, installation);
    }

    public void deleteInstallation(int id) throws Exception {
        installationManager.deleteInstallation(id);
        installations.removeIf(i -> i.getId() == id);
    }
}
