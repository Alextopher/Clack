package data;

import java.io.*;
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
        return fileContents;
    }

    /**
     * Decrypt and return file contents
     * @param key decryption key
     * @return plaintext file contents
     */
    @Override
    public String getData(String key) {
        return decrypt(fileContents, key);
    }

    /**
     * Reads contents of fileName and saves them to fileContents
     * @throws IOException file not found or there was an error while reading file
     */
    public void readFileContents() throws IOException {
        fileContents = readFile();
    }

    /**
     * Reads the contents of fileName, encrypts it, and saves it to fileContents
     * @param key encryption key
     * @throws IOException file not found or there was an error while reading file
     */
    public void readFileContents(String key) throws IOException {
        fileContents = encrypt(readFile(), key);
    }

    /**
     * Writes fileContents to fileName
     * @throws IOException file writing failed
     */
    public void writeFileContents() throws IOException {
        FileWriter file = new FileWriter(fileName);
        file.write(fileContents);
        file.close();
    }

    /**
     * Decrypts fileContents and writes to fileName
     * @param key Encryption key
     * @throws IOException file writing failed
     */
    public void writeFileContents(String key) throws IOException {
        FileWriter file = new FileWriter(fileName);
        file.write(decrypt(fileContents, key));
        file.close();
    }

    /**
     * Reads a file from disk as a String
     * @return String file contents
     * @throws IOException file reading failed
     */
    private String readFile() throws IOException {
        FileReader file = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(file);
        StringBuilder content = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        reader.close();

        return content.toString();
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
