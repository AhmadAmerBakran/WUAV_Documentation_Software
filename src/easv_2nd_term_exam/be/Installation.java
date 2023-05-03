package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.InstallationType;

import java.time.LocalDateTime;

public class Installation {

    private int id, customerId, technicianId;
    private String username, password, description;

    private InstallationType installationType;

    public Installation(int id, int customerId, int technicianId, String username, String password, String description, InstallationType installationType) {
        this.id = id;
        this.customerId = customerId;
        this.technicianId = technicianId;
        this.username = username;
        this.password = password;
        this.description = description;
        this.installationType = installationType;
    }

    public Installation(int customerId, int technicianId, String username, String password, String description, InstallationType installationType) {
        this.customerId = customerId;
        this.technicianId = technicianId;
        this.username = username;
        this.password = password;
        this.description = description;
        this.installationType = installationType;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InstallationType getInstallationType() {
        return installationType;
    }

    public void setInstallationType(InstallationType installationType) {
        this.installationType = installationType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}