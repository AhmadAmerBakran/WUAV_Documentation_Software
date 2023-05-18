// easv_2nd_term_exam/gui/models/AdminModel.java
package easv_2nd_term_exam.gui.models;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.bll.AdminManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.IntStream;

public class AdminModel {

    private AdminManager adminManager;
    private ObservableList<User> users;

    public AdminModel() {
        adminManager = new AdminManager();
        users = FXCollections.observableArrayList();
        loadUsers();
    }

    private void loadUsers() {
        users.clear();
        users.addAll(adminManager.getAllUsers());
    }

    public ObservableList<User> getUsers() {
        return FXCollections.observableArrayList(adminManager.getAllUsers());
    }

    public List<User> getDeletedUsers()
    {
        return FXCollections.observableArrayList(adminManager.getDeletedUsers());

    }

    public ObservableList<Technician> getTechnicians() {
        return FXCollections.observableArrayList(adminManager.getAllTechnicians());
    }

    public ObservableList<ProjectManager> getProjectManagers() {
        return FXCollections.observableArrayList(adminManager.getAllProjectManagers());
    }

    public ObservableList<SalesPerson> getSalesPersons() {
        return FXCollections.observableArrayList(adminManager.getAllSalesPersons());
    }

    public User addUser(User user) {
        User newUser = adminManager.addUser(user);
        if (newUser != null) {
            users.add(newUser);
        }
        return newUser;
    }

    public boolean updateUser(User user) {
        boolean updated = adminManager.updateUser(user);
        if (updated) {
            IntStream.range(0, users.size())
                    .filter(i -> users.get(i).getId() == user.getId())
                    .findFirst()
                    .ifPresent(index -> users.set(index, user));
        }
        return updated;
    }


    public boolean deleteUser(int id) {
        boolean deleted = adminManager.deleteUser(id);
        if (deleted) {
            users.removeIf(user -> user.getId() == id);
        }
        return deleted;
    }

    public boolean restoreUser(int id) {
        boolean deleted = adminManager.restoreUser(id);
        if (deleted) {
            users.removeIf(user -> user.getId() == id);
        }
        return deleted;
    }


}
