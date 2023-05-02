package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.UserRole;

public class Technician extends User {


    public Technician(int id, String name, String email, String username, String password) {
        super(id, name, email, username, password);
        setRole(UserRole.TECHNICIAN);
    }

    public Technician(String name, String email, String username, String password) {
        super(name, email, username, password);
    }
}
