package easv_2nd_term_exam.bll;

import easv_2nd_term_exam.be.Picture;
import easv_2nd_term_exam.dal.PictureDAO;
import easv_2nd_term_exam.dal.interfaces.IPictureDAO;

import java.sql.SQLException;
import java.util.List;

public class PictureManager {

    private IPictureDAO pictureDAO;

    public PictureManager() {
        pictureDAO = new PictureDAO();
    }

    public Picture createPicture(Picture picture) throws Exception {
        return pictureDAO.createPicture(picture);
    }

    public Picture getPicture(int id) throws Exception {
        return pictureDAO.getPicture(id);
    }

    public List<Picture> getAllPictures() throws Exception {
        return pictureDAO.getAllPictures();
    }

    public void updatePicture(Picture picture) throws Exception {
        pictureDAO.updatePicture(picture);
    }

    public void deletePicture(int id) throws Exception {
        pictureDAO.deletePicture(id);
    }

    public List<Picture> getPicturesByInstallationId(int installationId) throws Exception {
        return pictureDAO.getPicturesByInstallationId(installationId);
    }
}
