/**
 * Represents a report containing information about a customer, installation, technician and associated pictures.
 */
package easv_2nd_term_exam.be;

import java.time.LocalDate;

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
    private LocalDate createdDate;
    private LocalDate expiryDate;

    /**
     * Default constructor for creating a new Report object.
     */
    public Report() {

    }
    /**
     * Gets the created date of the report/Installation.
     * @return the created date as a LocalDate
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date of the report/Installation.
     * @param createdDate the created date as a LocalDate
     */
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the expiry date of the report/Installation (can be the warranty).
     * @return the expiry date as a LocalDate
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the report.
     * @param expiryDate the expiry date as a LocalDate
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the username associated with the report/ Installation's device.
     * @return the username as a String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the report/ Installation's device.
     * @param username the username as a String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password associated with the report/ Installation's device.
     * @return the password as a String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password associated with the report/ Installation's device.
     * @param password the password as a String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the description of the report/ Installation.
     * @return the description as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the report/ Installation.
     * @param description the description as a String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the customer name.
     * @return the customer name as a String
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the customer name.
     * @param customerName the customer name as a String
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the customer address.
     * @return the customer address as a String
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * Sets the customer address.
     * @param customerAddress the customer address as a String
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * Gets the customer email.
     * @return the customer email as a String
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * Sets the customer email.
     * @param customerEmail the customer email as a String
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     *  Gets the customer type.
     * @return the customer type as a String
     */
    public String getCustomerType() {
    return customerType;
    }

    /**
     * Sets the customer type.
     * @param customerType the customer type as a String
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    /**
     * Gets the installation ID.
     * @return the installation ID as an int
     */
    public int getInstallationId() {
        return installationId;
    }

    /**
     * Sets the installation ID.
     * @param installationId the installation ID as an int
     */
    public void setInstallationId(int installationId) {
        this.installationId = installationId;
    }

    /**
     * Gets the technician ID.
     * @return the technician ID as an int
     */
    public int getTechnicianId() {
        return technicianId;
    }

    /**
     * Sets the technician ID.
     * @param technicianId the technician ID as an int
     */
    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }

    /**
     * Gets the technician name.
     * @return the technician name as a String
     */
    public String getTechnicianName() {
        return technicianName;
    }

    /**
     * Sets the technician name.
     * @param technicianName the technician name as a String
     */
    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    /**
     * Gets the installation type.
     * @return the installation type as a String
     */
    public String getInstallationType() {
        return installationType;
    }

    /**
     * Sets the installation type.
     * @param installationType the installation type as a String
     */
    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }

    /**
     * Gets the picture1 name.
     * @return the picture1 name as a String
     */
    public String getPicture1Name() {
        return picture1Name;
    }

    /**
     * Sets the picture1 name.
     * @param picture1Name the picture1 name as a String
     */
    public void setPicture1Name(String picture1Name) {
        this.picture1Name = picture1Name;
    }

    /**
     * Gets the picture1 data.
     * @return the picture1 data as a byte array
     */
    public byte[] getPicture1Data() {
        return picture1Data;
    }

    /**
     * Sets the picture1 data.
     * @param picture1Data the picture1 data as a byte array
     */
    public void setPicture1Data(byte[] picture1Data) {
        this.picture1Data = picture1Data;
    }

    /**
     * Gets the picture2 name.
     * @return the picture2 name as a String
     */
    public String getPicture2Name() {
        return picture2Name;
    }

    /**
     * Sets the picture2 name.
     * @param picture2Name the picture2 name as a String
     */
    public void setPicture2Name(String picture2Name) {
        this.picture2Name = picture2Name;
    }

    /**
     * Gets the picture2 data.
     * @return the picture2 data as a byte array
     */
    public byte[] getPicture2Data() {
        return picture2Data;
    }

    /**
     * Sets the picture2 data.
     * @param picture2Data the picture2 data as a byte array
     */
    public void setPicture2Data(byte[] picture2Data)
    {
        this.picture2Data = picture2Data;
    }

    /**
     * Gets the customer ID.
     * @return the customer ID as an int
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     * @param customerId the customer ID as an int
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
