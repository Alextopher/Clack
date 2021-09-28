package data;

public class FileClackData extends ClackData {
    /**
     * String representing name of file.
     */
    private String fileName;
    /**
     * String representing contents of file
     */
    private String fileContents;

    /**
     * Sets up userName, type, and fileName. fileContents is defaults to null
     * @param userName String representing name of client user
     * @param type Integer representing the type of data stored
     * @param fileName String representing name of file
     */
    public FileClackData(String userName, int type, String fileName) {
        super(userName, type);
        this.fileName = fileName;
    }

    /**
     * Default constructor. userName is set to "Anon". date is set to current time. type is set to CONSTANT_UNKNOWN
     * fileName and fileContents default to null
     */
    public FileClackData() {
        super();
    }

    /**
     * Returns the name of the file.
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Changes the name of the file.
     * @param fileName new name.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // TODO learn how to do IO
    public void readFileContents() {

    }

    // TODO learn how to do IO
    public void writeFileContents() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FileClackData) {
            FileClackData other = (FileClackData) obj;

            // We don't compare file contents because it would be expensive and "filename" should be good enough
            return super.equals(obj) && other.fileName.equals(this.fileName);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + super.hashCode();
        if (fileName != null) result = 37 * result + fileName.hashCode();
        // We don't compare file contents because it would be expensive and "filename" should be good enough
        // if this is a wrong assumptions please let us know!
        return result;
    }

    @Override
    public String toString() {
        return "FileClackData{" +
                "userName='" + this.getUserName() + '\'' +
                ", type=" + this.getType() +
                ", date=" + this.getDate() +
                ", fileName='" + fileName + '\'' +
                ", fileContents='" + fileContents + '\'' +
                '}';
    }
}
