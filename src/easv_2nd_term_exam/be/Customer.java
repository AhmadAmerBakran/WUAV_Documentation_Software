package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.CustomerType;

public class Customer {

    private int id;
    private String name, address, email, billingAddress;
    private CustomerType type;

    private boolean isDeleted;
    public Customer(int id, String name, String address, String email, CustomerType type, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.type = type;
        this.isDeleted = isDeleted;
    }
    public Customer(String name, String address, String email, CustomerType type) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.type = type;
        this.isDeleted = false;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }
}
