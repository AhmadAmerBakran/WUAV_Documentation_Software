package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.UserRole;

/**
 * User is an abstract class representing a user in the system.
 */
public abstract class User {

    private int id;
    private String name, email, username, password;
    private UserRole role;
    private boolean isDeleted;

    /**
     * Constructor for the User class.
     *
     * @param id The user's ID
     * @param name The user's name
     * @param email The user's email
     * @param username The user's username
     * @param password The user's password
     * @param isDeleted A boolean indicating whether the user is deleted or not
     */
    public User(int id, String name, String email, String username, String password, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    /**
     * Constructor for the User class without the id and isDeleted parameters.
     *
     * @param name The user's name
     * @param email The user's email
     * @param username The user's username
     * @param password The user's password
     */
    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isDeleted = false;
    }

    /**
     * Checks whether the user is deleted.
     *
     * @return A boolean indicating whether the user is deleted or not
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets the deleted status of the user.
     *
     * @param deleted A boolean indicating whether the user is deleted or not
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * Gets the user's ID.
     *
     * @return The user's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id The user's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user's name.
     *
     * @return The user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name The user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's email.
     *
     * @return The user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email The user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's username.
     *
     * @return The user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username The user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password.
     *
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's role.
     *
     * @return The user's role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role The user's role
     */
    public void setRole(UserRole role) {
        this.role = role;
    }
}
