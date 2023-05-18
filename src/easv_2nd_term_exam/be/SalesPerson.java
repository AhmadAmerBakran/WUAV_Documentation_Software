/**
 * Represents a salesperson in the system.
 * This class is a subclass of User with a specific role of UserRole.SALES_PERSON.
 */
package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.UserRole;

public class SalesPerson extends User {

    /**
     * Constructor for creating a salesperson with a specified ID.
     * @param id The unique identifier for the salesperson
     * @param name The name of the salesperson
     * @param email The email address of the salesperson
     * @param username The username of the salesperson
     * @param password The password of the salesperson
     */
    public SalesPerson(int id, String name, String email, String username, String password, boolean isDeleted) {
        super(id, name, email, username, password, isDeleted);
        setRole(UserRole.SALES_PERSON);
    }

    /**
     * Constructor for creating a new salesperson without specifying an ID.
     * @param name The name of the salesperson
     * @param email The email address of the salesperson
     * @param username The username of the salesperson
     * @param password The password of the salesperson
     */
    public SalesPerson(String name, String email, String username, String password) {
        super(name, email, username, password);
        setRole(UserRole.SALES_PERSON);
    }


}
