package main;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.util.*;
import java.net.*;
import java.io.BufferedReader;


public class ClackClient {

    public static final String KEY = "XYPQR";

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
     * ObjectInputStream and ObjectOutputStream have been declared as instance variables.
     */
    FileInputStream iFS = new FileInputStream("null");
    ObjectInputStream inFromServer = new ObjectInputStream(iFS);
    FileOutputStream oTS = new FileOutputStream("null");
    ObjectOutputStream outToServer = new ObjectOutputStream(oTS);


    private java.util.Scanner inFromStd;


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
    public ClackClient(String userName, String hostName, int port) throws IllegalArgumentException, IOException {
        if (userName == null) {
            throw new IllegalArgumentException("userName cannot be null");
        }
        if (hostName == null) {
            throw new IllegalArgumentException("hostName cannot be null");
        }
        if (port < 1024 || port > 65535) {
            throw new IllegalArgumentException("port must be between 1024 and 65535");
        }
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
    public ClackClient(String userName, String hostName) throws IllegalArgumentException, IOException {
        this(userName, hostName, DEFAULT_PORT);
    }

    /**
     * Constructor that sets username. closeConnection defaults to true and port is set to the default.
     * The hostname is set to "localhost"
     * @param userName String representing name of the client
     */
    public ClackClient(String userName) throws IllegalArgumentException, IOException {
        this(userName, DEFAULT_HOSTNAME, DEFAULT_PORT);
    }

    /**
     * Default Constructor. username is set to "anon" closeConnection defaults to true and port is set to the default.
     * The hostname is set to "localhost"
     */
    public ClackClient() throws IOException {
        this(DEFAULT_USER_NAME, DEFAULT_HOSTNAME, DEFAULT_PORT);
    }

    /**
     * Starts the client
     */
    public void start() {
        try {
            ServerSocket SS = new ServerSocket(DEFAULT_PORT);
            System.out.println("Server is up, waiting for request");
            Socket clientSocket = SS.accept();
        }
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
        BufferedReader inFromServer = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream());

        inFromStd = new Scanner(System.in);
        closeConnection = false;
        while (!closeConnection) {
            readClientData();
            dataToReceiveFromServer = dataToSendToServer;
            if (dataToReceiveFromServer != null) {
                printData();
            }
        }
    }

    /**
     * Reads the data from the client
     */
    public void readClientData() {
        String inp;
        inp = inFromStd.next();
        if (inp.matches("DONE")){
            closeConnection = true;
            dataToSendToServer = null;
        }
        else if (inp.matches("SENDFILE")) {
            FileClackData fileClackData = new FileClackData(this.userName, ClackData.CONSTANT_SENDFILE, inFromStd.next());
            try {
                fileClackData.readFileContents(KEY);
                dataToSendToServer = fileClackData;
            } catch (IOException e) {
                System.out.println("Could not read file " + fileClackData.getFileName());
                dataToSendToServer = null;
            }
        }
        else if (inp.matches("LISTUSERS")){

        }
        else {
            dataToSendToServer = new MessageClackData(this.userName,inp + inFromStd.nextLine(), KEY,
                    MessageClackData.CONSTANT_SENDMESSAGE);
        }
    }

    /**
     * Sends data to server
     * outToServer has been initialized as null.
     */
    public void sendData() {
        outToServer = null;
        try{
            dataToSendToServer = outToServer;
        }
        catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }

    }

    /**
     * Receives data from the server, does not return anything
     * inFromServer has been initialized as null.
     */
    public void receiveData() {
        inFromServer = null;

        try{
            dataToSendToServer = new inFromServer();
        }
        catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }

    }

    /**
     * prints the received data to standard output
     */
    public void printData() {
        System.out.println(dataToReceiveFromServer.getDate() + " | "
                        + dataToReceiveFromServer.getUserName() + " :\n"
                        + dataToReceiveFromServer.getData(KEY)
                );
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
                ", dataToSendToServer=" + dataToSendToServer + '\'' +
                ", dataToReceiveFromServer=" + dataToReceiveFromServer + '\'' +
                '}';
    }
}
