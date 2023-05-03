package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.Installation;
import java.util.List;

public interface IInstallationDAO {
    Installation createInstallation(Installation installation) throws Exception;
    Installation getInstallation(int id) throws Exception;
    List<Installation> getAllInstallations() throws Exception;
    void updateInstallation(Installation installation) throws Exception;
    void deleteInstallation(int id) throws Exception;
}
