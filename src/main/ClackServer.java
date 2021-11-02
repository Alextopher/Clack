package main;

import data.ClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
     * Stream to receive information from client, initialized to null
     */
    private ObjectInputStream inFromClient;

    /**
     * Stream to send information to client, initialized to null
     */
    private ObjectOutputStream outToClient;

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
        this.inFromClient = null;
        this.outToClient = null;
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
        // Create listener socket
        try {
            ServerSocket listener = new ServerSocket(port);
            Socket socket = listener.accept();
            inFromClient = new ObjectInputStream(socket.getInputStream());
            outToClient = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            // Printing the stack trace here to better debug an issue if it arrives
            e.printStackTrace();
            System.err.println(e.getMessage());
            return;
        }

        System.out.print("Server started listening on localhost:");
        System.out.println(port);

        // send and receive data forever
        while (!closeConnection) {
            receiveData();
            dataToSendToClient = dataToReceiveFromClient;
            sendData();
        }
    }

    /**
     * Receives the data from client and sets dataToReceiveFromClient
     */
    public void receiveData() {
        try {
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Sends data to client
     */
    public void sendData() {
        try {
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
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