package easv_2nd_term_exam.be;

import java.time.LocalDateTime;

public class Installation {

    private int id;
    private String description, setupInfo, username, password;
    private byte[] layoutDrawing;
    private LocalDateTime date;
    private Customer customer;
    private User technician;

    public Installation(int id, String description, String setupInfo, String username, String password, byte[] layoutDrawing,
                        LocalDateTime date, Customer customer, User technician) {
        this.id = id;
        this.description = description;
        this.setupInfo = setupInfo;
        this.username = username;
        this.password = password;
        this.layoutDrawing = layoutDrawing;
        this.date = date;
        this.customer = customer;
        this.technician = technician;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSetupInfo() {
        return setupInfo;
    }

    public void setSetupInfo(String setupInfo) {
        this.setupInfo = setupInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getLayoutDrawing() {
        return layoutDrawing;
    }

    public void setLayoutDrawing(byte[] layoutDrawing) {
        this.layoutDrawing = layoutDrawing;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getTechnician() {
        return technician;
    }

    public void setTechnician(User technician) {
        this.technician = technician;
    }

}
