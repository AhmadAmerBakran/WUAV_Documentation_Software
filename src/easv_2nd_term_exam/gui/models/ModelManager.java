package easv_2nd_term_exam.gui.models;

public class ModelManager {

    private AdminModel adminModel;
    private CustomerModel customerModel;
    private InstallationModel installationModel;
    private LogInModel logInModel;
    private PictureModel pictureModel;

    private ReportModel reportModel;

    public ModelManager() throws Exception {
        adminModel = new AdminModel();
        customerModel = new CustomerModel();
        installationModel = new InstallationModel();
        logInModel = new LogInModel();
        pictureModel = new PictureModel();
        reportModel = new ReportModel();
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public ReportModel getReportModel() {
        return reportModel;
    }

    public void setReportModel(ReportModel reportModel) {
        this.reportModel = reportModel;
    }

    public void setAdminModel(AdminModel adminModel) {
        this.adminModel = adminModel;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public InstallationModel getInstallationModel() {
        return installationModel;
    }

    public void setInstallationModel(InstallationModel installationModel) {
        this.installationModel = installationModel;
    }

    public LogInModel getLogInModel() {
        return logInModel;
    }

    public void setLogInModel(LogInModel logInModel) {
        this.logInModel = logInModel;
    }

    public PictureModel getPictureModel() {
        return pictureModel;
    }

    public void setPictureModel(PictureModel pictureModel) {
        this.pictureModel = pictureModel;
    }
}
