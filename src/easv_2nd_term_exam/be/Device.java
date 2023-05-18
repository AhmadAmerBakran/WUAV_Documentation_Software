package easv_2nd_term_exam.be;

public class Device {

    private int id;
    private String name;
    private int deviceTypeId;
    private int installationId;
    private String username;
    private String password;

    public Device(int id, String name, int deviceTypeId, int installationId, String username, String password) {
        this.id = id;
        this.name = name;
        this.deviceTypeId = deviceTypeId;
        this.installationId = installationId;
        this.username = username;
        this.password = password;
    }

    public Device(String name, int deviceTypeId, String username, String password) {
        this.name = name;
        this.deviceTypeId = deviceTypeId;
        this.username = username;
        this.password = password;
    }

    public Device() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(int deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public int getInstallationId() {
        return installationId;
    }

    public void setInstallationId(int installationId) {
        this.installationId = installationId;
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
}
