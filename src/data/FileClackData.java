package data;

import java.util.Objects;

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

    /**
     * Return file contents (currently null)
     * @return String file contents
     */
    @Override
    public String getData() {
        return null;
    }

    /**
     * decrypt and return file contents
     * @param key decryption key
     * @return plaintext file contents
     */
    @Override
    public String getData(String key) {
        return decrypt(fileContents, key);
    }

    // TODO learn how to do IO
    public void readFileContents() {

    }

    // TODO learn how to do IO
    public void writeFileContents() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FileClackData that = (FileClackData) o;
        return Objects.equals(getFileName(), that.getFileName()) && Objects.equals(fileContents, that.fileContents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFileName(), fileContents);
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
