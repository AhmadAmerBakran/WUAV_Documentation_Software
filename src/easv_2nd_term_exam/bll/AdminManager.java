package easv_2nd_term_exam.bll;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.dal.AdminDAO;

import java.util.List;

public class AdminManager {

    private AdminDAO adminDAO;

    public AdminManager() {
        adminDAO = new AdminDAO();
    }

    public List<User> getAllUsers() {
        return adminDAO.getAllUsers();
    }

    public List<Technician> getAllTechnicians() {
        return adminDAO.getAllTechnicians();
    }

    public List<ProjectManager> getAllProjectManagers() {
        return adminDAO.getAllProjectManagers();
    }

    public List<SalesPerson> getAllSalesPersons() {
        return adminDAO.getAllSalesPersons();
    }

    public User addUser(User user) {
        return adminDAO.addUser(user);
    }

    public boolean updateUser(User user) {
        return adminDAO.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return adminDAO.deleteUser(id);
    }
}
