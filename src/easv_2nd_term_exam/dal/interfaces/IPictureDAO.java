package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.Picture;
import java.util.List;

public interface IPictureDAO {
    List<Picture> createPictures(List<Picture> pictures) throws Exception;
    Picture createPicture(Picture picture) throws Exception;
    Picture getPicture(int id) throws Exception;
    List<Picture> getAllPictures() throws Exception;
    List<Picture> getPicturesByInstallationId(int installationId) throws Exception;
    void updatePicture(Picture picture) throws Exception;
    void deletePicture(int id) throws Exception;
}
