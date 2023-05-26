package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.CustomerType;

/**
 * Customer is a class representing a customer in the system.
 */
public class Customer {

    private int id;
    private String name, address, email, billingAddress;
    private CustomerType type;
    private boolean isDeleted;

    /**
     * Constructor for the Customer class.
     *
     * @param id The customer's ID
     * @param name The customer's name
     * @param address The customer's address
     * @param email The customer's email
     * @param type The customer's type
     * @param isDeleted A boolean indicating whether the customer is deleted or not
     */
    public Customer(int id, String name, String address, String email, CustomerType type, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.type = type;
        this.isDeleted = isDeleted;
    }

    /**
     * Constructor for the Customer class without the id and isDeleted parameters.
     *
     * @param name The customer's name
     * @param address The customer's address
     * @param email The customer's email
     * @param type The customer's type
     */
    public Customer(String name, String address, String email, CustomerType type) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.type = type;
        this.isDeleted = false;
    }

    /**
     * Checks whether the customer is deleted.
     *
     * @return A boolean indicating whether the customer is deleted or not
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets the deleted status of the customer.
     *
     * @param deleted A boolean indicating whether the customer is deleted or not
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * Gets the customer's ID.
     *
     * @return The customer's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the customer's ID.
     *
     * @param id The customer's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the customer's name.
     *
     * @return The customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the customer's billing address.
     *
     * @return The customer's billing address
     */
    public String getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the customer's billing address.
     *
     * @param billingAddress The customer's billing address
     */
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * Sets the customer's name.
     *
     * @param name The customer's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the customer's address.
     *
     * @return The customer's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the customer's address.
     *
     * @param address The customer's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the customer's email.
     *
     * @return The customer's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the customer's email.
     *
     * @param email The customer's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the customer's type.
     *
     * @return The customer's type
     */
    public CustomerType getType() {
        return type;
    }

    /**
     * Sets the customer's type.
     *
     * @param type The customer's type
     */
    public void setType(CustomerType type) {
        this.type = type;
    }
}
