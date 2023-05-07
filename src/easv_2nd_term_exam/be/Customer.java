/**
 * Represents a customer in the system.
 */
package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.CustomerType;

public class Customer {

    private int id;
    private String name, address, email;
    private CustomerType type;

    /**
     * Constructor for creating a customer with a specified ID.
     * @param id The unique identifier for the customer
     * @param name The name of the customer
     * @param address The address of the customer
     * @param email The email address of the customer
     * @param type The type of the customer
     */
    public Customer(int id, String name, String address, String email, CustomerType type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.type = type;
    }

    /**
     * Constructor for creating a new customer without specifying an ID.
     * @param name The name of the customer
     * @param address The address of the customer
     * @param email The email address of the customer
     * @param type The type of the customer
     */
    public Customer(String name, String address, String email, CustomerType type) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.type = type;
    }

    /**
     * @return The unique identifier of the customer
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the customer.
     * @param id The new unique identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The address of the customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer.
     * @param address The new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The email address of the customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the customer.
     * @param email The new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The type of the customer
     */
    public CustomerType getType() {
        return type;
    }

    /**
     * Sets the type of the customer.
     * @param type The new customer type
     */
    public void setType(CustomerType type) {
        this.type = type;
    }
}
