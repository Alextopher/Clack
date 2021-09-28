package test;

import main.ClackServer;

public class TestClackServer {
    private static final int TEST_PORT = 3000;

    public static void main(String[] args) {
        // Default constructor
        ClackServer server1 = new ClackServer();
        System.out.println(server1);
        System.out.println("port " + server1.getPort());
        System.out.println("hashCode " + server1.hashCode());
        System.out.println();

        // Default constructor
        ClackServer server2 = new ClackServer(TEST_PORT);
        System.out.println(server2);
        System.out.println("port " + server2.getPort());
        System.out.println("hashCode " + server2.hashCode());
        System.out.println();
    }
}
