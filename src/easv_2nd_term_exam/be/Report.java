package easv_2nd_term_exam.be;

public class Report {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerType;
    private int installationId;
    private int technicianId;
    private String technicianName;
    private String installationType, username, password, description;
    private String picture1Name;
    private byte[] picture1Data;
    private String picture2Name;
    private byte[] picture2Data;

    public Report() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public int getInstallationId() {
        return installationId;
    }

    public void setInstallationId(int installationId) {
        this.installationId = installationId;
    }

    public int getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getInstallationType() {
        return installationType;
    }

    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }

    public String getPicture1Name() {
        return picture1Name;
    }

    public void setPicture1Name(String picture1Name) {
        this.picture1Name = picture1Name;
    }

    public byte[] getPicture1Data() {
        return picture1Data;
    }

    public void setPicture1Data(byte[] picture1Data) {
        this.picture1Data = picture1Data;
    }

    public String getPicture2Name() {
        return picture2Name;
    }

    public void setPicture2Name(String picture2Name) {
        this.picture2Name = picture2Name;
    }

    public byte[] getPicture2Data() {
        return picture2Data;
    }

    public void setPicture2Data(byte[] picture2Data) {
        this.picture2Data = picture2Data;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
