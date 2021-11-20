package main;

import data.ClackData;
import data.ClientSideServerListener;
import data.FileClackData;
import data.MessageClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.util.*;


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
     * Stream to receive information from server, initialized to null
     */
    private ObjectInputStream inFromServer;

    /**
     * Stream to send information to server, initialized to null
     */
    private ObjectOutputStream outToServer;


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

    public static void main(String[] args) {
        ClackClient client;
        // case 1
        try {
            if (args.length == 0) {
                client = new ClackClient();
            } else if (args.length == 1) {
                String[] s1 = args[0].split("@");
                String username = s1[0];

                if (s1.length == 1) {
                    // just the name
                    client = new ClackClient(username);
                } else if (s1.length == 2) {
                    // the name and host:port
                    String[] s2 = s1[1].split(":");

                    if (s2.length == 1) {
                        String hostname = s2[0];
                        client = new ClackClient(username, hostname);
                    } else if (s2.length == 2) {
                        String hostname = s2[0];
                        int port = Integer.parseInt(s2[1]);
                        client = new ClackClient(username, hostname, port);
                    } else {
                        throw new IllegalArgumentException(
                            "ERROR: Invalid number of arguments. Try:\n\tjava ClackClient [name[@host[:port]]]"
                        );
                    }
                } else {
                    throw new IllegalArgumentException(
                            "ERROR: Invalid number of arguments. Try:\n\tjava ClackClient [name[@host[:port]]]"
                    );
                }
            } else {
                throw new IllegalArgumentException(
                        "ERROR: Invalid number of arguments. Try:\n\tjava ClackClient [name[@host[:port]]]"
                );
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
            return;
        }

        client.start();
    }

    /**
     * Constructor that sets username, hostname and port. closeConnection defaults to true
     * @param userName String representing name of the client
     * @param hostName String representing name of the computer representing the server
     * @param port Integer representing port number on server connected to
     */
    public ClackClient(String userName, String hostName, int port) throws IllegalArgumentException {
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
    public ClackClient(String userName, String hostName) throws IllegalArgumentException {
        this(userName, hostName, DEFAULT_PORT);
    }

    /**
     * Constructor that sets username. closeConnection defaults to true and port is set to the default.
     * The hostname is set to "localhost"
     * @param userName String representing name of the client
     */
    public ClackClient(String userName) throws IllegalArgumentException {
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
        Socket socket;

        try {
            socket = new Socket(hostName, port);
            inFromServer = new ObjectInputStream(socket.getInputStream());
            outToServer = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        inFromStd = new Scanner(System.in);
        closeConnection = false;

        Thread ct = new Thread(new ClientSideServerListener(this));
        ct.start();

        while (!closeConnection) {
            readClientData();
            sendData();
            if (closeConnection) {
                break;
            }

        }

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * implement the method ‘getCloseConnection() to
     * determine if the connection is closed or not by returning the ‘closeConnection’ boolean
     * variable.
     */
    public boolean getCloseConnection(){
        return closeConnection;
    }

    /**
     * Reads the data from the client
     */
    public void readClientData() {
        String inp;
        inp = inFromStd.next();
        if (inp.matches("DONE")){
            closeConnection = true;
            dataToSendToServer = new MessageClackData(this.userName, "goodbye", ClackData.CONSTANT_LOGOUT);
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
     */
    public void sendData() {
        try {
            outToServer.writeObject(dataToSendToServer);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Receives data from the server, does not return anything
     */
    public void receiveData() {
        try {
            dataToReceiveFromServer = (ClackData) inFromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
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
