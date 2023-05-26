package easv_2nd_term_exam.be;

/**
 * Device is a class representing a device in the system.
 */
public class Device {

    private int id;
    private String name;
    private int deviceTypeId;
    private int installationId;
    private String username;
    private String password;

    /**
     * Constructor for the Device class.
     *
     * @param id The device's ID
     * @param name The device's name
     * @param deviceTypeId The device type's ID
     * @param installationId The installation's ID
     * @param username The device's username
     * @param password The device's password
     */
    public Device(int id, String name, int deviceTypeId, int installationId, String username, String password) {
        this.id = id;
        this.name = name;
        this.deviceTypeId = deviceTypeId;
        this.installationId = installationId;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor for the Device class without the id and installationId parameters.
     *
     * @param name The device's name
     * @param deviceTypeId The device type's ID
     * @param username The device's username
     * @param password The device's password
     */
    public Device(String name, int deviceTypeId, String username, String password) {
        this.name = name;
        this.deviceTypeId = deviceTypeId;
        this.username = username;
        this.password = password;
    }

    /**
     * Default constructor for the Device class.
     */
    public Device() {
    }

    /**
     * Gets the device's ID.
     *
     * @return The device's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the device's ID.
     *
     * @param id The device's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the device's name.
     *
     * @return The device's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the device's name.
     *
     * @param name The device's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the device type's ID.
     *
     * @return The device type's ID
     */
    public int getDeviceTypeId() {
        return deviceTypeId;
    }

    /**
     * Sets the device type's ID.
     *
     * @param deviceTypeId The device type's ID
     */
    public void setDeviceTypeId(int deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    /**
     * Gets the installation's ID.
     *
     * @return The installation's ID
     */
    public int getInstallationId() {
        return installationId;
    }

    /**
     * Sets the installation's ID.
     *
     * @param installationId The installation's ID
     */
    public void setInstallationId(int installationId) {
        this.installationId = installationId;
    }

    /**
     * Gets the device's username.
     *
     * @return The device's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the device's username.
     *
     * @param username The device's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the device's password.
     *
     * @return The device's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the device's password.
     *
     * @param password The device's password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
