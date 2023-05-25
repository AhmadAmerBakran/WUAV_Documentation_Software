package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.*;
import easv_2nd_term_exam.enums.UserRole;

import java.util.List;

public interface IAdminDAO {

    List<User> getAllUsers();

    User addUser(User user);

    boolean hasAdmins();

    void createAdminUser(String name, String email, String username, String password);

    boolean updateUser(User user);

    boolean deleteUser(int id);

    boolean restoreUser(int id);


    List<User> getDeletedUsers();

    List<Technician> getAllActiveTechnicians();


    List<ProjectManager> getAllActiveProjectManagers();


    List<SalesPerson> getAllActiveSalesPersons();

}
