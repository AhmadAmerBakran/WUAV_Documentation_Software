package easv_2nd_term_exam.be;

public class Picture {

    private int id;
    private String name, filePath, metadata;

    public Picture(int id, String name, String filePath, String metadata) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
        this.metadata = metadata;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

}
