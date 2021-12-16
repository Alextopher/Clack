package data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class PictureClackData extends ClackData {
    private static final long serialVersionUID = 1L;
    String fileName;
    String fileContents;

    public PictureClackData(String user, String fn, int t){
        super(user, t);
        fileName = fn;
        try{
            BufferedImage bImage = ImageIO.read(new File(fileName));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos );
            byte [] data = bos.toByteArray();
            fileContents = Base64.getEncoder().encodeToString(data);

        }catch (FileNotFoundException fnfe){
            System.err.println("File Not Found");
        }catch (IOException ioe){
            System.err.println("IO Exception: File is invalid");
        }

    }

    public PictureClackData() {
        super();
        fileName = null;
        fileContents = null;
    }
    public void setFileName(String fn){
        fileName = fn;
    }
    public String getFileName() {
        return fileName;
    }
    public String getData() {
        return fileContents;
    }

    public String getData(String dummy) {
        return fileName;
    }

    @Override
    public int hashCode(){
        return ((int) fileContents.hashCode() + (int)getUserName().hashCode() +getType());

    }
    @Override
    public boolean equals(Object other){
        PictureClackData otherFile = (PictureClackData)other;
        return this.fileContents == otherFile.fileContents && this.fileName ==otherFile.fileName&&
                this.getUserName() == otherFile.getUserName() &&this.getType() == otherFile.getType();
    }

    @Override
    public String toString(){
        return "The user " + super.getUserName() + " sent the message of type "+
                super.getType() + " and file name " + fileName + " containing " + this.getData()+ " on " + super.getDate();
    }


}
