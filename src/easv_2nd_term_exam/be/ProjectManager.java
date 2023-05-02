package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.UserRole;

public class ProjectManager extends User {


    public ProjectManager(int id, String name, String email, String username, String password) {
        super(id, name, email, username, password);
        setRole(UserRole.PROJECT_MANAGER);
    }

    public ProjectManager(String name, String email, String username, String password) {
        super(name, email, username, password);
    }
}
