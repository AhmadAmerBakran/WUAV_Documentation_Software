package easv_2nd_term_exam.gui.models;

import easv_2nd_term_exam.be.Picture;
import easv_2nd_term_exam.bll.PictureManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PictureModel {

    private PictureManager pictureManager;
    private ObservableList<Picture> pictures;

    public PictureModel() throws Exception {
        pictureManager = new PictureManager();
        pictures = FXCollections.observableArrayList();
        loadPictures();
    }

    public void loadPictures() throws Exception {
        List<Picture> pictureList = pictureManager.getAllPictures();
        pictures.setAll(pictureList);
    }

    public ObservableList<Picture> getPictures() {
        return pictures;
    }

    public Picture createPicture(Picture picture) throws Exception {
        Picture createdPicture = pictureManager.createPicture(picture);
        pictures.add(createdPicture);
        return createdPicture;
    }

    public void updatePicture(Picture picture) throws Exception {
        pictureManager.updatePicture(picture);
        int index = pictures.indexOf(picture);
        pictures.set(index, picture);
    }

    public void deletePicture(int id) throws Exception {
        pictureManager.deletePicture(id);
        pictures.removeIf(p -> p.getId() == id);
    }

    public List<Picture> getPicturesByInstallationId(int installationId) throws Exception {
        return pictureManager.getPicturesByInstallationId(installationId);
    }
    public List<Picture> createPictures(List<Picture> pictureList) throws Exception {
        List<Picture> createdPictures = pictureManager.createPictures(pictureList);
        pictures.addAll(createdPictures);
        return createdPictures;
    }

}
