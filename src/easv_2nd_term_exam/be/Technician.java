/**
 * Represents a technician in the system.
 * This class is a subclass of User with a specific role of UserRole.TECHNICIAN.
 */
package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.UserRole;

public class Technician extends User {

    /**
     * Constructor for creating a technician with a specified ID.
     * @param id The unique identifier for the technician
     * @param name The name of the technician
     * @param email The email address of the technician
     * @param username The username of the technician
     * @param password The password of the technician
     */
    public Technician(int id, String name, String email, String username, String password) {
        super(id, name, email, username, password);
        setRole(UserRole.TECHNICIAN);
    }

    /**
     * Constructor for creating a new technician without specifying an ID.
     * @param name The name of the technician
     * @param email The email address of the technician
     * @param username The username of the technician
     * @param password The password of the technician
     */
    public Technician(String name, String email, String username, String password) {
        super(name, email, username, password);
        setRole(UserRole.TECHNICIAN);
    }
}