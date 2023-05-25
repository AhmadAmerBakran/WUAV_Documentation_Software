package easv_2nd_term_exam.dal.interfaces;

import easv_2nd_term_exam.be.Picture;

import java.sql.SQLException;
import java.util.List;

public interface IPictureDAO {
    List<Picture> createPictures(List<Picture> pictures) throws SQLException;
    Picture createPicture(Picture picture) throws SQLException;
    Picture getPicture(int id) throws SQLException;
    List<Picture> getAllPictures() throws SQLException;
    List<Picture> getPicturesByInstallationId(int installationId) throws SQLException;
    void updatePicture(Picture picture) throws SQLException;
    void deletePicture(int id) throws SQLException;
}
