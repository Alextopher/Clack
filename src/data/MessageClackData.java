package data;

/**
 * String representing instant message
 */
public class MessageClackData extends ClackData {

    /**
     * String representing message of client user.
     */
    private String message;

    /**
     * Super constructor to set up username, message, and type
     */
    public MessageClackData(String userName, String message, int type) {
        super(userName, type);
        this.message = message;
    }

    /**
     * default constructor that call another constructor. userName is set to "Anon".
     */
    public MessageClackData() {
        new MessageClackData("Anon", null, CONSTANT_UNKNOWN);
    }

    /**
     * implemented here to return instant message.
     * @return instant message
     */
    public String getMessage() {
        return message;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + super.hashCode();
        if (message != null) result = 37 * result + message.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MessageClackData) {
            MessageClackData other = (MessageClackData) obj;
            // We don't compare file contents because it would be expensive and "filename" should be good enough
            return super.equals(obj) && other.message.equals(this.message);
        } else {
            return false;
        }
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
