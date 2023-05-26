package easv_2nd_term_exam.be;

/**
 * InstallationType is a class representing a type of installation in the system.
 */
public class InstallationType {

    private int id;
    private String name;
    private boolean isDeleted;

    /**
     * Constructor for the InstallationType class.
     *
     * @param id The installation type's ID
     * @param name The name of the installation type
     * @param isDeleted The deleted status of the installation type
     */
    public InstallationType(int id, String name, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
    }

    /**
     * Default constructor for the InstallationType class.
     */
    public InstallationType() {
    }

    /**
     * Gets the installation type's ID.
     *
     * @return The installation type's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the installation type's ID.
     *
     * @param id The installation type's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the installation type.
     *
     * @return The name of the installation type
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the installation type.
     *
     * @param name The name of the installation type
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the deleted status of the installation type.
     *
     * @return The deleted status of the installation type
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets the deleted status of the installation type.
     *
     * @param deleted The deleted status of the installation type
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * Returns a string representation of the InstallationType object.
     *
     * @return The name of the installation type
     */
    @Override
    public String toString() {
        return name;
    }
}
