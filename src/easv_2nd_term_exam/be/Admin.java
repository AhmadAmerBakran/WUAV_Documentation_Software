/**
 * Represents an admin in the system.
 * This class is a subclass of User with a specific role of UserRole.ADMIN.
 */
package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.UserRole;

public class Admin extends User {

    /**
     * Constructor for creating an admin with a specified ID.
     * @param id The unique identifier for the admin
     * @param name The name of the admin
     * @param email The email address of the admin
     * @param username The username of the admin
     * @param password The password of the admin
     */
    public Admin(int id, String name, String email, String username, String password, boolean isDeleted) {
        super(id, name, email, username, password, isDeleted);
        setRole(UserRole.ADMIN);
    }

    /**
     * Constructor for creating a new admin without specifying an ID.
     * @param name The name of the admin
     * @param email The email address of the admin
     * @param username The username of the admin
     * @param password The password of the admin
     */
    public Admin(String name, String email, String username, String password) {
        super(name, email, username, password);
        setRole(UserRole.ADMIN);
    }
}
