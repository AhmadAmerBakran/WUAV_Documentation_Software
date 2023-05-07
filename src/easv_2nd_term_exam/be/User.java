/**
 * Represents a user in the system.
 * This class is intended to be extended by specific user types (e.g., Technician, Project Manager, Sales-Person etc..).
 */
package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.UserRole;

public abstract class User {

    private int id;
    private String name, email, username, password;
    private UserRole role;

    /**
     * Constructor for creating a user with a specified ID.
     * @param id The unique identifier for the user
     * @param name The name of the user
     * @param email The email address of the user
     * @param username The username of the user
     * @param password The password of the user
     */
    public User(int id, String name, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor for creating a new user without specifying an ID.
     * @param name The name of the user
     * @param email The email address of the user
     * @param username The username of the user
     * @param password The password of the user
     */
    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * @return The unique identifier of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     * @param id The new unique identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * @param email The new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * @param username The new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * @param password The new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The role of the user
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * @param role The new role
     */
    public void setRole(UserRole role) {
        this.role = role;
    }
}
