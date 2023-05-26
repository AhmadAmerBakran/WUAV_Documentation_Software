package easv_2nd_term_exam.be;

import java.time.LocalDate;
import java.util.List;

/**
 * The Report class represents a report of a customer's installation.
 */
public class Report {

    private int customerId;
    private String customerName;
    private String customerAddress, billingAddress;
    private String customerEmail;
    private String customerType;
    private int installationId;
    private int technicianId;
    private int installationTypeId;
    private String technicianName;
    private String installationType;
    private List<Device> devices;
    private String description;
    private List<Picture> pictures;
    private LocalDate createdDate;
    private LocalDate expiryDate;

    /**
     * Default constructor for the Report class.
     */
    public Report() {
    }

    /**
     * Gets the ID of the customer.
     *
     * @return An integer representing the customer's ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the ID of the customer.
     *
     * @param customerId An integer containing the customer's ID.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the name of the customer.
     *
     * @return A string representing the customer's name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the name of the customer.
     *
     * @param customerName A string containing the customer's name.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the customer's address.
     *
     * @return A string representing the customer's address.
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * Sets the customer's address.
     *
     * @param customerAddress A string containing the customer's address.
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * Gets the billing address of the customer.
     *
     * @return A string representing the customer's billing address.
     */
    public String getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the billing address of the customer.
     *
     * @param billingAddress A string containing the customer's billing address.
     */
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * Gets the email of the customer.
     *
     * @return A string representing the customer's email.
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * Sets the email of the customer.
     *
     * @param customerEmail A string containing the customer's email.
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * Gets the type of the customer.
     *
     * @return A string representing the customer's type.
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * Sets the type of the customer.
     *
     * @param customerType A string containing the customer's type.
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    /**
     * Gets the ID of the installation.
     *
     * @return An integer representing the installation's ID.
     */
    public int getInstallationId() {
        return installationId;
    }

    /**
     * Sets the ID of the installation.
     *
     * @param installationId An integer containing the installation's ID.
     */
    public void setInstallationId(int installationId) {
        this.installationId = installationId;
    }

    /**
     * Gets the ID of the technician.
     *
     * @return An integer representing the technician's ID.
     */
    public int getTechnicianId() {
        return technicianId;
    }

    /**
     * Sets the ID of the technician.
     *
     * @param technicianId An integer containing the technician's ID.
     */
    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }

    /**
     * Gets the name of the technician.
     *
     * @return A string representing the technician's name.
     */
    public String getTechnicianName() {
        return technicianName;
    }

    /**
     * Sets the name of the technician.
     *
     * @param technicianName A string containing the technician's name.
     */
    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    /**
     * Gets the type of the installation.
     *
     * @return A string representing the installation's type.
     */
    public String getInstallationType() {
        return installationType;
    }

    /**
     * Sets the type of the installation.
     *
     * @param installationType A string containing the installation's type.
     */
    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }

    /**
     * Gets the list of devices.
     *
     * @return A List of Device objects representing the devices.
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * Sets the list of devices.
     *
     * @param devices A List of Device objects containing the devices.
     */
    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    /**
     * Gets the description.
     *
     * @return A string representing the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description A string containing the description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the list of pictures.
     *
     * @return A List of Picture objects representing the pictures.
     */
    public List<Picture> getPictures() {
        return pictures;
    }

    /**
     * Sets the list of pictures.
     *
     * @param pictures A List of Picture objects containing the pictures.
     */
    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    /**
     * Gets the created date.
     *
     * @return A LocalDate object representing the created date.
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate A LocalDate object containing the created date.
     */
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the expiry date.
     *
     * @return A LocalDate object representing the expiry date.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date.
     *
     * @param expiryDate A LocalDate object containing the expiry date.
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the ID of the installation's type.
     *
     * @return An integer representing the installation type's ID.
     */
    public int getInstallationTypeId() {
        return installationTypeId;
    }

    /**
     * Sets the ID of the installation's type.
     *
     * @param installationTypeId An integer containing the installation type's ID.
     */
    public void setInstallationTypeId(int installationTypeId) {
        this.installationTypeId = installationTypeId;
    }

}
