package main;

import data.ClackData;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClackServer that = (ClackServer) o;
        return getPort() == that.getPort()
                && closeConnection == that.closeConnection
                && Objects.equals(dataToReceiveFromClient, that.dataToReceiveFromClient)
                && Objects.equals(dataToSendToClient, that.dataToSendToClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPort(), closeConnection, dataToReceiveFromClient, dataToSendToClient);
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