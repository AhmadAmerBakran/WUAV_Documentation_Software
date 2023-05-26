package easv_2nd_term_exam.be;

import java.time.LocalDate;

/**
 * Installation is a class representing an installation in the system.
 */
public class Installation {

    private int id, customerId, technicianId, installationTypeId;
    private String description;
    private LocalDate createdDate, expiryDate;

    /**
     * Constructor for the Installation class.
     *
     * @param id The installation's ID
     * @param customerId The customer's ID
     * @param technicianId The technician's ID
     * @param description The installation's description
     * @param installationTypeId The installation type's ID
     */
    public Installation(int id, int customerId, int technicianId, String description, int installationTypeId) {
        this.id = id;
        this.customerId = customerId;
        this.technicianId = technicianId;
        this.description = description;
        this.installationTypeId = installationTypeId;
    }

    /**
     * Constructor for the Installation class without the id parameter.
     *
     * @param customerId The customer's ID
     * @param technicianId The technician's ID
     * @param description The installation's description
     * @param installationTypeId The installation type's ID
     */
    public Installation(int customerId, int technicianId, String description, int installationTypeId) {
        this.customerId = customerId;
        this.technicianId = technicianId;
        this.description = description;
        this.installationTypeId = installationTypeId;
    }

    /**
     * Gets the installation type's ID.
     *
     * @return The installation type's ID
     */
    public int getInstallationTypeId() {
        return installationTypeId;
    }

    /**
     * Sets the installation type's ID.
     *
     * @param installationTypeId The installation type's ID
     */
    public void setInstallationTypeId(int installationTypeId) {
        this.installationTypeId = installationTypeId;
    }

    /**
     * Gets the created date of the installation.
     *
     * @return The created date of the installation
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date of the installation.
     *
     * @param createdDate The created date of the installation
     */
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the expiry date of the installation.
     *
     * @return The expiry date of the installation
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the installation.
     *
     * @param expiryDate The expiry date of the installation
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the customer's ID of the installation.
     *
     * @return The customer's ID of the installation
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer's ID of the installation.
     *
     * @param customerId The customer's ID of the installation
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the technician's ID of the installation.
     *
     * @return The technician's ID of the installation
     */
    public int getTechnicianId() {
        return technicianId;
    }

    /**
     * Sets the technician's ID of the installation.
     *
     * @param technicianId The technician's ID of the installation
     */
    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }

    /**
     * Gets the description of the installation.
     *
     * @return The description of the installation
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the installation.
     *
     * @param description The description of the installation
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the installation's ID.
     *
     * @return The installation's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the installation's ID.
     *
     * @param id The installation's ID
     */
    public void setId(int id) {
        this.id = id;
    }
}
