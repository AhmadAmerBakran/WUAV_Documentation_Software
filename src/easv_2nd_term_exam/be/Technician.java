package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.UserType;

public class Technician extends User {


    public Technician(int id, String name, String email, String username, String password) {
        super(id, name, email, username, password, UserType.TECHNICIAN);
    }

}
