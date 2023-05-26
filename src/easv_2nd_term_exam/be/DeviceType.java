package easv_2nd_term_exam.be;

/**
 * DeviceType is a class representing a type of device in the system.
 */
public class DeviceType {

    private int id;
    private String name;
    private boolean isDeleted;

    /**
     * Constructor for the DeviceType class.
     *
     * @param id The device type's ID
     * @param name The device type's name
     * @param isDeleted A boolean indicating whether the device type is deleted or not
     */
    public DeviceType(int id, String name, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
    }

    /**
     * Default constructor for the DeviceType class.
     */
    public DeviceType() {
    }

    /**
     * Gets the device type's ID.
     *
     * @return The device type's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the device type's ID.
     *
     * @param id The device type's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the device type's name.
     *
     * @return The device type's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the device type's name.
     *
     * @param name The device type's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks whether the device type is deleted.
     *
     * @return A boolean indicating whether the device type is deleted or not
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets the deleted status of the device type.
     *
     * @param deleted A boolean indicating whether the device type is deleted or not
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
