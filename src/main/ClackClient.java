package main;

import data.ClackData;

import java.util.Objects;

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
     *  Default hostname
     */
    public static final String DEFAULT_HOSTNAME = "localhost";

    /**
     *  Default userName
     */
    public static final String DEFAULT_USER_NAME = "Anon";

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
        this(userName, hostName, DEFAULT_PORT);
    }

    /**
     * Constructor that sets username. closeConnection defaults to true and port is set to the default.
     * The hostname is set to "localhost"
     * @param userName String representing name of the client
     */
    public ClackClient(String userName) {
        this(userName, DEFAULT_HOSTNAME, DEFAULT_PORT);
    }

    /**
     * Default Constructor. username is set to "anon" closeConnection defaults to true and port is set to the default.
     * The hostname is set to "localhost"
     */
    public ClackClient() {
        this(DEFAULT_USER_NAME, DEFAULT_HOSTNAME, DEFAULT_PORT);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClackClient that = (ClackClient) o;
        return getPort() == that.getPort()
                && closeConnection == that.closeConnection
                && Objects.equals(getUserName(), that.getUserName())
                && Objects.equals(getHostName(), that.getHostName())
                && Objects.equals(dataToSendToServer, that.dataToSendToServer)
                && Objects.equals(dataToReceiveFromServer, that.dataToReceiveFromServer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getHostName(), getPort(), closeConnection, dataToSendToServer, dataToReceiveFromServer);
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
