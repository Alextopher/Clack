package data;

import java.util.Objects;

/**
 * String representing instant message
 */
public class MessageClackData extends ClackData {

    /**
     * String representing message of client user.
     */
    private String message;


    /**
     * Constructor to set up username, message, and type
     */
    public MessageClackData(String userName, String message, int type) {
        super(userName, type);
        this.message = message;
    }

    /**
     * Constructor to set up username, message, and type
     * message is encrypted with key
     * @param message plain text
     * @param key encryption key
     */
    public MessageClackData(String userName, String message, String key, int type ){
        this(userName, encrypt(message, key), type);
    }

    /**
     * default constructor that call another constructor. userName is set to "Anon".
     */
    public MessageClackData() {
        new MessageClackData("Anon", null, CONSTANT_LISTUSERS);
    }

    /**
     * implemented here to return instant message.
     * @return instant message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Return instant message
     * @return String instant message
     */
    @Override
    public String getData() {
        return getMessage();
    }

    /**
     * decrypt the message using a key
     * @param key decryption key
     * @return Plain text message
     */
    @Override
    public String getData(String key) {
        return decrypt(getMessage(), key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MessageClackData that = (MessageClackData) o;
        return Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMessage());
    }

    @Override
    public String toString() {
        return "MessageClackData{" +
                "userName='" + this.getUserName() + '\'' +
                ", type=" + this.getType() +
                ", date=" + this.getDate() +
                ", message='" + message + '\'' +
                '}';
    }
}
