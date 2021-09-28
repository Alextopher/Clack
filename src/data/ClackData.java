package data;

import java.util.Date;

public class ClackData {
    /**
     * String representing name of client user
     */
    private String userName;
    /**
     * Integer representing the type of data stored. One of CONSTANT_LISTUSERS, CONSTANT_LOGOUT,
     * CONSTANT_SENDMESSAGE, CONSTANT_SENDFILE
     */
    private int type;
    /**
     * java.util.Date object representing the date when object was created
     */
    private Date date;

    /**
     * Type that represents unknown data type
      */
    public static final int CONSTANT_UNKNOWN = -1;
    /**
     * Type that represents a listing of all users connected to this session
      */
    public static final int CONSTANT_LISTUSERS = 0;
    /**
     * Type that requests closure of this client’s connection
     */
    public static final int CONSTANT_LOGOUT = 1;
    /**
     * Type that signals client wants to send a message
     */
    public static final int CONSTANT_SENDMESSAGE = 2;
    /**
     * Type that signals client wants to send a file
     */
    public static final int CONSTANT_SENDFILE = 3;

    /**
     * Constructor to set up userName and type. Sets date to current time
     * @param userName String representing name of client user
     * @param type Integer representing the type of data stored. One of CONSTANT_LISTUSERS, CONSTANT_LOGOUT,
     * CONSTANT_SENDMESSAGE, CONSTANT_SENDFILE
     */
    public ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }

    /**
     * Constructor to set type. userName is set to "Anon". Sets date to current time
     * @param type CONSTANT_LISTUSERS, CONSTANT_LOGOUT, CONSTANT_SENDMESSAGE, CONSTANT_SENDFILE
     */
    public ClackData(int type) {
        this("Anon", type);
    }

    /**
     * Default constructor. userName is set to "Anon". date is set to current time. type is set to CONSTANT_UNKNOWN
     */
    public ClackData() {
        this(CONSTANT_UNKNOWN);
    }

    /**
     * Returns the userName of the client
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the type of data
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * Returns the time the data was created
     * @return date
     */
    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ClackData) {
            ClackData other = (ClackData) obj;
            return other.userName.equals(this.userName)
                    && other.date.equals(this.date)
                    && other.type == this.type;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + userName.hashCode();
        result = 37 * result + type;
        result = 37 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ClackData{" +
                "userName='" + userName + '\'' +
                ", type=" + type +
                ", date=" + date +
                '}';
    }
}
