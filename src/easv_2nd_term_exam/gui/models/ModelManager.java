package easv_2nd_term_exam.gui.models;

import java.sql.SQLException;

public class ModelManager {

    private AdminModel adminModel;
    private CustomerModel customerModel;
    private InstallationModel installationModel;
    private LogInModel logInModel;
    private PictureModel pictureModel;

    private ReportModel reportModel;
    private DeviceTypeModel deviceTypeModel;
    private DevicesModel devicesModel;
    private InstallationTypeModel installationTypeModel;

    public ModelManager() throws Exception {
    }

    public AdminModel getAdminModel() {
        if (adminModel == null) {
            adminModel = new AdminModel();
        }
        return adminModel;
    }

    public CustomerModel getCustomerModel() throws SQLException {
        if (customerModel == null) {
            customerModel = new CustomerModel();
        }
        return customerModel;
    }

    public InstallationModel getInstallationModel() throws Exception {
        if (installationModel == null) {
            installationModel = new InstallationModel();
        }
        return installationModel;
    }

    public LogInModel getLogInModel() {
        if (logInModel == null) {
            logInModel = new LogInModel();
        }
        return logInModel;
    }

    public PictureModel getPictureModel() throws Exception {
        if (pictureModel == null) {
            pictureModel = new PictureModel();
        }
        return pictureModel;
    }

    public ReportModel getReportModel() {
        if (reportModel == null) {
            reportModel = new ReportModel();
        }
        return reportModel;
    }

    public DeviceTypeModel getDeviceTypeModel() {
        if (deviceTypeModel == null) {
            deviceTypeModel = new DeviceTypeModel();
        }
        return deviceTypeModel;
    }

    public DevicesModel getDevicesModel() {
        if (devicesModel == null) {
            devicesModel = new DevicesModel();
        }
        return devicesModel;
    }

    public InstallationTypeModel getInstallationTypeModel() {
        if (installationTypeModel == null) {
            installationTypeModel = new InstallationTypeModel();
        }
        return installationTypeModel;
    }

}
