package easv_2nd_term_exam.gui.models;

import easv_2nd_term_exam.be.InstallationType;
import easv_2nd_term_exam.bll.InstallationTypeManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InstallationTypeModel {

    private InstallationTypeManager installationTypeManager;
    private ObservableList<InstallationType> installationTypes;
    private ObservableList<String> installationTypeNames;

    public InstallationTypeModel() {
        installationTypeManager = new InstallationTypeManager();
        installationTypes = FXCollections.observableArrayList();
        installationTypeNames = FXCollections.observableArrayList();
        loadInstallationTypes();
    }

    public void createInstallationType(InstallationType installationType) {
        installationTypeManager.createInstallationType(installationType);
        loadInstallationTypes();
    }

    public void updateInstallationType(InstallationType installationType) {
        installationTypeManager.updateInstallationType(installationType);
        loadInstallationTypes();
    }

    public void deleteInstallationType(int id) {
        installationTypeManager.deleteInstallationType(id);
        loadInstallationTypes();
    }

    private void loadInstallationTypes() {
        installationTypes.clear();
        installationTypeNames.clear();

        for (InstallationType installationType : installationTypeManager.getAllInstallationTypes()) {
            installationTypes.add(installationType);
            installationTypeNames.add(installationType.getName());
        }
    }

    public ObservableList<InstallationType> getInstallationTypes() {
        return installationTypes;
    }

    public ObservableList<String> getInstallationTypeNames() {
        return installationTypeNames;
    }
}
