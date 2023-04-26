package easv_2nd_term_exam.be;

import easv_2nd_term_exam.enums.UserType;

public class SalesPerson extends User{
    public SalesPerson(int id, String name, String email, String username, String password) {
        super(id, name, email, username, password, UserType.SALES_PERSON);
    }
}
