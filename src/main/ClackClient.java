package main;

import data.ClackData;

public class ClackClient {
    /**
     * String representing name of the client
     */
    private String userName;
    /**
     * String representing name of the computer representing the server
     */
    private String hostName;
    /**
     * Integer representing port number on server connected to
     */
    private int port;
    /**
     * boolean representing whether connection is closed or not. true is closed.
     */
    private boolean closeConnection;
    /**
     * ClackData object representing data sent to server
     */
    private ClackData dataToSendToServer;
    /**
     * ClackData object representing data received from the server
     */
    private ClackData dataToReceiveFromServer;

    /**
     * Default port Clark runs on
     */
    public static final int DEFAULT_PORT = 7000;

    /**
     * Constructor that sets username, hostname and port. closeConnection defaults to true
     * @param userName String representing name of the client
     * @param hostName String representing name of the computer representing the server
     * @param port Integer representing port number on server connected to
     */
    public ClackClient(String userName, String hostName, int port) {
        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        this.closeConnection = true;
    }

    /**
     * Constructor that sets username and hostname. closeConnection defaults to true and port is set to the default.
     * @param userName String representing name of the client
     * @param hostName String representing name of the computer representing the server
     */
    public ClackClient(String userName, String hostName) {
        new ClackClient(userName, hostName, DEFAULT_PORT);
    }

    /**
     * Starts the client
     */
    public void start() {

    }

    /**
     * Reads the data from the client
     */
    public void readClientData() {

    }

    /**
     * Sends data to server
     */
    public void sendData() {

    }

    /**
     * Receives data from the server, does not return anything
     */
    public void receiveData() {

    }

    /**
     * prints the received data to standard output
     */
    public void printData() {

    }

    /**
     * Returns the client's userName
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the server's hostName
     * @return hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Returns the port the client connects to
     * @return port
     */
    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ClackClient) {
            ClackClient other = (ClackClient) obj;
            // Not sure if we should compare dataToSendToServer and dataToReceiveFromServer
            // Seems like it shouldn't be of any benefit because 2 clients can't connect on the same port
            return other.hostName.equals(this.hostName)
                    && other.userName.equals(this.userName)
                    && other.port == this.port
                    && other.closeConnection == this.closeConnection;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + super.hashCode();
        if (hostName != null) result = 37 * result + hostName.hashCode();
        if (userName != null) result = 37 * result + userName.hashCode();
        result = 37 * result + Boolean.hashCode(closeConnection);
        result = 37 * result + port;
        // Not sure if we should compare dataToSendToServer and dataToReceiveFromServer
        // Seems like it shouldn't be of any benefit because 2 clients can't connect on the same port
        return result;
    }

    @Override
    public String toString() {
        return "ClackClient{" +
                "userName='" + userName + '\'' +
                ", hostName='" + hostName + '\'' +
                ", port=" + port +
                ", closeConnection=" + closeConnection +
                ", dataToSendToServer=" + dataToSendToServer.toString() + '\'' +
                ", dataToReceiveFromServer=" + dataToReceiveFromServer.toString() + '\'' +
                '}';
    }
}
