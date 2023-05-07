/**
 * Represents a project manager in the system.
 * This class is a subclass of User with a specific role of UserRole.PROJECT_MANAGER.
 */
package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.UserRole;

public class ProjectManager extends User {

    /**
     * Constructor for creating a project manager with a specified ID.
     * @param id The unique identifier for the project manager
     * @param name The name of the project manager
     * @param email The email address of the project manager
     * @param username The username of the project manager
     * @param password The password of the project manager
     */
    public ProjectManager(int id, String name, String email, String username, String password) {
        super(id, name, email, username, password);
        setRole(UserRole.PROJECT_MANAGER);
    }

    /**
     * Constructor for creating a new project manager without specifying an ID.
     * @param name The name of the project manager
     * @param email The email address of the project manager
     * @param username The username of the project manager
     * @param password The password of the project manager
     */
    public ProjectManager(String name, String email, String username, String password) {
        super(name, email, username, password);
        setRole(UserRole.PROJECT_MANAGER);
    }
}
