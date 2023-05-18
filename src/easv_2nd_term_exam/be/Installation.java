
package easv_2nd_term_exam.be;

import java.time.LocalDate;

public class Installation {

    private int id, customerId, technicianId, installationTypeId;
    private String description;
    private LocalDate createdDate, expiryDate;


    public Installation(int id, int customerId, int technicianId, String description, int installationTypeId) {
        this.id = id;
        this.customerId = customerId;
        this.technicianId = technicianId;
        this.description = description;
        this.installationTypeId = installationTypeId;
    }

    public Installation(int customerId, int technicianId, String description, int installationTypeId) {
        this.customerId = customerId;
        this.technicianId = technicianId;
        this.description = description;
        this.installationTypeId = installationTypeId;
    }

    public int getInstallationTypeId() {
        return installationTypeId;
    }

    public void setInstallationTypeId(int installationTypeId) {
        this.installationTypeId = installationTypeId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
   
