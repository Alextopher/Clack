package main;

import data.ClackData;
import data.ListUsersClackData;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClackServer {
    /**
     * default constructor that sets port to default port number 7000
     */
    public static final int DEFAULT_PORT = 7000;

    /**
     * Integer representing port number on server connected to
     */
    private int port;

    /**
     * boolean represents whether the server remains open or not to handle any client.
     */
    private boolean closeConnection;

    /**
     * ArrayList consisting of all ServerSideClientIO objects
     */
    private ArrayList<ServerSideClientIO> serverSideClientIOList;

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
        this.closeConnection = false;
        this.serverSideClientIOList = new ArrayList<>();
    }

    /**
     * Default constructor. Set the port to 7000
     * set dataToReceiveFromClient and dataToSendToClient as null.
     */
    public ClackServer() {
        this(DEFAULT_PORT);
    }

    public void start() {
        ServerSocket listener;

        // Create listener socket
        try {
            listener = new ServerSocket(port);
        } catch (Exception e) {
            // Printing the stack trace here to better debug an issue if it arrives
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("Waiting for connections");
        while (!closeConnection) {
            try {
                Socket socket = listener.accept();
                System.out.println(socket.toString());
                ServerSideClientIO client = new ServerSideClientIO(this, socket);
                serverSideClientIOList.add(client);

                // Spawn a new thread to handle IO for the client
                Thread t = new Thread(client);
                t.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.print("Stopping Server");
        try {
            listener.close();
        } catch (IOException e) {
            System.err.println("IOException: Failed to close the server listener");
            System.err.println(e.getMessage());
        }
    }

    /**
     * This is a synchronized method that takes in a single ClackData object
     * ‘dataToBroadcastToClients’. It should iterate through the list ‘serverSideClientIOList’. For
     * every object in the list, call the object’s setDataToSendToClient() method to set the
     * instance variable ‘dataToSendToClient’ in that object, and then call the object’s
     * sendData() method to force the object to send the data to the corresponding client
     */
    synchronized public void broadcast(ClackData dataToBroadcastToClients) {
        System.out.println(dataToBroadcastToClients.toString());

        for (ServerSideClientIO client: serverSideClientIOList) {
            client.setDataToSendToClient(dataToBroadcastToClients);
            client.sendData();
        }
    }

    /**
     * This is a synchronized method that takes in a single ServerSideClientIO
     * object, and removes this object from the list ‘serverSideClientIOList’
     * @param client ServerSideClientIO to remove
     */
    synchronized public void remove(ServerSideClientIO client) {
        serverSideClientIOList.remove(client);
    }

    /**
     * This is a synchronized method that takes in a single ServerSideClientIO
     * object, generates the current user list and sends it to that client
     */
    synchronized public void listUsers(ServerSideClientIO client) {
        ArrayList<String> usernames = new ArrayList<>(serverSideClientIOList.size());

        for (ServerSideClientIO c: serverSideClientIOList) {
            usernames.add(c.getUserName());
        }

        System.out.println("LISTUSERS " + usernames);
        client.setDataToSendToClient(new ListUsersClackData("SERVER", usernames));
        client.sendData();
    }

    /**
     * Returns the port the server is listening on
     *
     * @return port
     */
    public int getPort() {
        return port;
    }
}