package easv_2nd_term_exam.be;

public class Picture {

    private int id, installationId;
    private String pictureName;
    private byte[] imageData;

    public Picture(int id, String pictureName, byte[] imageData, int installationId) {
        this.id = id;
        this.installationId = installationId;
        this.pictureName = pictureName;
        this.imageData = imageData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInstallationId() {
        return installationId;
    }

    public void setInstallationId(int installationId) {
        this.installationId = installationId;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
