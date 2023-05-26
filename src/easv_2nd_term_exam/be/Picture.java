package easv_2nd_term_exam.be;

/**
 * The Picture class represents a picture associated with an installation.
 */
public class Picture {

    private int id, installationId;
    private String pictureName;
    private byte[] imageData;

    /**
     * Main constructor for the Picture class.
     *
     * @param id The picture's ID
     * @param pictureName The name of the picture
     * @param imageData The byte array representing the image data
     * @param installationId The ID of the associated installation
     */
    public Picture(int id, String pictureName, byte[] imageData, int installationId) {
        this.id = id;
        this.installationId = installationId;
        this.pictureName = pictureName;
        this.imageData = imageData;
    }

    /**
     * Overloaded constructor for the Picture class, without ID (for new picture creation).
     *
     * @param installationId The ID of the associated installation
     * @param pictureName The name of the picture
     * @param imageData The byte array representing the image data
     */
    public Picture(int installationId, String pictureName, byte[] imageData) {
        this.installationId = installationId;
        this.pictureName = pictureName;
        this.imageData = imageData;
    }

    /**
     * Default constructor for the Picture class.
     */
    public Picture() {
    }

    /**
     * Gets the picture's ID.
     *
     * @return The picture's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the picture's ID.
     *
     * @param id The picture's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the associated installation.
     *
     * @return The ID of the associated installation
     */
    public int getInstallationId() {
        return installationId;
    }

    /**
     * Sets the ID of the associated installation.
     *
     * @param installationId The ID of the associated installation
     */
    public void setInstallationId(int installationId) {
        this.installationId = installationId;
    }

    /**
     * Gets the name of the picture.
     *
     * @return The name of the picture
     */
    public String getPictureName() {
        return pictureName;
    }

    /**
     * Sets the name of the picture.
     *
     * @param pictureName The name of the picture
     */
    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    /**
     * Gets the image data of the picture.
     *
     * @return The byte array representing the image data
     */
    public byte[] getImageData() {
        return imageData;
    }

    /**
     * Sets the image data of the picture.
     *
     * @param imageData The byte array representing the image data
     */
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
