/**
 * Represents an installation in the system.
 */
package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.InstallationType;

import java.time.LocalDate;

public class Installation {

    private int id, customerId, technicianId;
    private String username, password, description;
    private InstallationType installationType;
    private LocalDate createdDate, expiryDate;

    /**
     * Constructor for creating an installation with a specified ID.
     *
     * @param id               The unique identifier for the installation
     * @param customerId       The unique identifier for the associated customer
     * @param technicianId     The unique identifier for the associated technician
     * @param username         The username for the installation
     * @param password         The password for the installation
     * @param description      A description of the installation
     * @param installationType The type of the installation
     */
    public Installation(int id, int customerId, int technicianId, String username, String password, String description, InstallationType installationType) {
        this.id = id;
        this.customerId = customerId;
        this.technicianId = technicianId;
        this.username = username;
        this.password = password;
        this.description = description;
        this.installationType = installationType;
    }

    /**
     * Constructor for creating a new installation without specifying an ID.
     *
     * @param customerId       The unique identifier for the associated customer
     * @param technicianId     The unique identifier for the associated technician
     * @param username         The username for the installation
     * @param password         The password for the installation
     * @param description      A description of the installation
     * @param installationType The type of the installation
     */
    public Installation(int customerId, int technicianId, String username, String password, String description, InstallationType installationType) {
        this.customerId = customerId;
        this.technicianId = technicianId;
        this.username = username;
        this.password = password;
        this.description = description;
        this.installationType = installationType;
    }

    /**
     * @return The creation date of the installation
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the creation date of the installation.
     *
     * @param createdDate The new creation date
     */
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return The expiry date of the installation
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the installation.
     *
     * @param expiryDate The new expiry date
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return The unique identifier of the associated customer
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the unique identifier of the associated customer.
     *
     * @param customerId The new unique identifier
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return The unique identifier of the associated technician
     */
    public int getTechnicianId() {
        return technicianId;
    }

    /**
     * Sets the unique identifier of the associated technician.
     *
     * @param technicianId The new unique identifier
     */
    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }

    /**
     * @return The username of the installation
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username of the installation.
     * @param username The new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The password of the installation
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the installation.
     * @param password The new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The description of the installation
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the installation.
     * @param description The new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The type of the installation
     */
    public InstallationType getInstallationType() {
        return installationType;
    }

    /**
     * Sets the type of the installation.
     * @param installationType The new installation type
     */
    public void setInstallationType(InstallationType installationType) {
        this.installationType = installationType;
    }

    /**
     * @return The unique identifier of the installation
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the installation.
     * @param id The new unique identifier
     */
    public void setId(int id) {
        this.id = id;
    }


}
   
