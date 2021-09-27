package data;

public class MessageClackData extends ClackData {

    /**
     * String representing instant message
     */
    private String userName;
    private String message;
    private Integer type;

    /**
     * @param userName String representing name of client user.
     * @param message String representing message of client user.
     * @param type Integer representing the type of data stored.
     */

    public MessageClackData(String userName, String message, int type) {
        super(userName, type);
        this.message = message;
    }

    /**
     * Super constructor to set up username, message, and type
     */

    public MessageClackData() {
        new MessageClackData("Anon", null, CONSTANT_UNKNOWN);
    }

    /**
     * default constructor that call another constructor. userName is set to "Anon".
     */

    public String getMessage() {
        return message;
    }

    /**
     * implemented here to return instant message.
     * @return instant message
     */


    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + super.hashCode();
        result = 37 * result * message.hashCode();
        return result;


    }

    @Override
    public boolean equals(Object ob) {
        if (this == ob) {
            return true;
        }
        if (ob instanceof MessageClackData) {
            MessageClackData other = (MessageClackData) ob;
            return other.userName.equals(this.userName)
                    && other.message.equals(this.message)
                    && other.type == this.type;
        } else {
            return false;
        }
    }
}
