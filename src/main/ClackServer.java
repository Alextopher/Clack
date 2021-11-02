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
     * Runs the program as a server.
     * Running with zero arguments will host on DEFAULT_PORT
     * One argument will be translated to a port number and the server will listen on that port.
     * @param args either 0 or 1 args
     */
    public static void main(String[] args) {
        ClackServer server;

        if (args.length == 0) {
            // No input means run on the default port
            server = new ClackServer();
        } else if (args.length == 1) {
            // One input should be treated as a port
            try {
                int port = Integer.parseInt(args[0]);
                server = new ClackServer(port);
            } catch (NumberFormatException e) {
                System.err.println("ERROR: Argument `port` could not be converted to an integer.");
                System.exit(1);
                return;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                System.exit(1);
                return;
            }
        } else {
            System.err.println("ERROR: Invalid number of arguments. Try:\n\tjava ClackServer [port]");
            System.exit(1);
            return;
        }

        server.start();
    }

    /**
     * Constructor that sets port number
     * set dataToReceiveFromClient and dataToSendToClient as null.
     * @param port port number
     */
    public ClackServer(int port) throws IllegalArgumentException {
        if (port < 1024 || port > 65535) {
            throw new IllegalArgumentException("port must be between 1024 and 65535");
        }
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