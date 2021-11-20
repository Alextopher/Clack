package data;

import main.ClackClient;

/**
 * first we write a class ‘ClientSideServerListener’ that implements Runnable.
 */
public class ClientSideServerListener implements Runnable {
    /**
     * ClackClient object used as IO for the client
      */
    ClackClient client;

    /**
     * The constructor should set the ClackClient instance variable ‘this.client’ in
     * this class to the value of the ClackClient object ‘client’ fed as a parameter to it.
     * @param client
     */
    public ClientSideServerListener(ClackClient client) {
        this.client = client;
    }

    /**
     * This method should have a while loop similar to the client that loops till the
     * connection is closed.
     */
    @Override
    public void run() {
        while (!client.getCloseConnection())
            client.receiveData();
            client.printData();
    }
}