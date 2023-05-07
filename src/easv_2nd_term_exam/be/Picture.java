/**
 * Represents a picture associated with an installation in the system.
 */
package easv_2nd_term_exam.be;

public class Picture {

    private int id, installationId;
    private String pictureName;
    private byte[] imageData;

    /**
     * Constructor for creating a picture with a specified ID.
     * @param id The unique identifier for the picture
     * @param pictureName The name of the picture
     * @param imageData The binary data of the picture
     * @param installationId The unique identifier for the associated installation
     */
    public Picture(int id, String pictureName, byte[] imageData, int installationId) {
        this.id = id;
        this.installationId = installationId;
        this.pictureName = pictureName;
        this.imageData = imageData;
    }

    /**
     * Constructor for creating a new picture without specifying an ID.
     * @param installationId The unique identifier for the associated installation
     * @param pictureName The name of the picture
     * @param imageData The binary data of the picture
     */
    public Picture(int installationId, String pictureName, byte[] imageData) {
        this.installationId = installationId;
        this.pictureName = pictureName;
        this.imageData = imageData;
    }

    /**
     * @return The unique identifier of the picture
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the picture.
     * @param id The new unique identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The unique identifier of the associated installation
     */
    public int getInstallationId() {
        return installationId;
    }

    /**
     * Sets the unique identifier of the associated installation.
     * @param installationId The new unique identifier
     */
    public void setInstallationId(int installationId) {
        this.installationId = installationId;
    }

    /**
     * @return The name of the picture
     */
    public String getPictureName() {
        return pictureName;
    }

    /**
     * Sets the name of the picture.
     * @param pictureName The new picture name
     */
    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    /**
     * @return The binary data of the picture
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * Sets the binary data of the picture.
     * @param imageData The new binary data
     */
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
