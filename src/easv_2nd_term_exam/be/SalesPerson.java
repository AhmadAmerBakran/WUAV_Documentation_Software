package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.UserRole;

public class SalesPerson extends User{
    public SalesPerson(int id, String name, String email, String username, String password) {
        super(id, name, email, username, password);
        setRole(UserRole.SALES_PERSON);
    }
}
