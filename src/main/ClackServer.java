package main;

import data.ClackData;

public class ClackServer {

    /**
     * Integer representing port number on server connected to
     */
    private int port;

    /**
     * boolean representing whether connection is closed or not
     */
    private boolean closeConnection;

    /**
     * ClackData object representing data received from the client
     */
    private ClackData dataToReceiveFromClient;

    /**
     * ClackData object representing data sent to client
     */
    private ClackData dataToSendToClient;

    /**
     * default constructor that sets port to default port number 7000
     */
    public static final int DEFAULT_PORT = 7000;

    /**
     * Constructor that sets port number
     * set dataToReceiveFromClient and dataToSendToClient as null.
     * @param port port number
     */
    public ClackServer(int port) {
        this.port = port;
        this.closeConnection = true;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
    }

    /**
     * Default constructor. Set the port to 7000
     * set dataToReceiveFromClient and dataToSendToClient as null.
     */
    public ClackServer() {
        this(DEFAULT_PORT);
    }

    /**
     * Starts the Server
     */
    public void start() {

    }

    /**
     * receives the data from client
     */
    public void receiveData() {

    }

    /**
     * Sends data to client
     */
    public void sendData() {

    }

    /**
     * Returns the port
     *
     * @return port
     */
    public int getPort() {
        return port;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + port;
        return result;
    }

    @Override
    public boolean equals(Object ob) {
        if (this == ob) {
            return true;
        }
        if (ob instanceof ClackServer) {
            ClackServer other = (ClackServer) ob;
            return other.port == this.port;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "ClackServer{" +
                "port=" + port +
                ", closeConnection=" + closeConnection +
                ", dataToReceiveFromClient=" + dataToReceiveFromClient +
                ", dataToSendToClient=" + dataToSendToClient +
                '}';
    }
}