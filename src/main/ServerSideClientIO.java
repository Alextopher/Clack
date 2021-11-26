package main;

//The ServerSideClientIO.java class handles sending and receiving data. So it takes over the
//        following tasks that were originally performed by ClackServer.java:
//        - It receives data from its associated client (which means ‘receiveData()’ in the server
//class is now transferred to this class). It does so as long as the connection from the
//        associated client is open. The moment the associated client closes the connection, the
//        method tells the server to remove the client. The italicized part uses a new method
//        called ‘remove’ in ClackServer.java.
//        - It sends data to its associated client (which means ‘sendData()’ in the server class is
//        now transferred to this class).
//        - When its run() method is executed, it creates object output and input streams using the
//        client socket’s input and output streams, receives data from the client, and tells the
//        server to broadcast the data to the client. The italicized part uses a new method called
//        ‘broadcast’ in ClackServer.java.

import data.ClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSideClientIO implements Runnable {

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
     * Object representing the master server
     */
    private final ClackServer server;

    /**
     * Object representing the socket accepted from the client
     */
    private final Socket clientSocket;

    public ServerSideClientIO(ClackServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;

        this.closeConnection = false;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
        this.inFromClient = null;
        this.outToClient = null;
    }

    /**
     * This is a simple mutator that sets the variable this.dataToSendToClient
     * @param dataToSendToClient new dataToSendToClient
     */
    public void setDataToSendToClient(ClackData dataToSendToClient) {
        this.dataToSendToClient = dataToSendToClient;
    }

    /**
     * This method takes over some functionalities from the start() method
     * in ClackServer in Part 3. The run() method
     *
     * gets the object output and input streams of the client from ‘clientSocket’,
     *
     * and contains a while loop that runs as long as the connection is open.
     *
     * In the loop, the method calls the ‘receiveData()’ method to get data
     * from the associated client (which will now be in ServerSideClientIO.java) and calls a new
     * method ‘broadcast()’ in the ‘this.server’ object to broadcast the data from the associated
     * client to all clients.
     */
    @Override
    public void run() {
        // Create listener socket
        try {
            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());
        } catch (Exception e) {
            // Printing the stack trace here to better debug an issue if it arrives
            System.err.println(e.getMessage());
            return;
        }

        // send and receive data forever
        while (!closeConnection) {
            receiveData();
            if (dataToReceiveFromClient == null || dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                server.remove(this);
                break;
            }

            server.broadcast(dataToReceiveFromClient);
        }

        try {
            clientSocket.close();
            server.remove(this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Receives the data from client and sets dataToReceiveFromClient
     */
    public void receiveData() {
        if (closeConnection) {
            server.remove(this);
            return;
        }

        try {
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();
        } catch (IOException e) {
            closeConnection = true;
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
            // We don't care about errors here
        }
    }
}
