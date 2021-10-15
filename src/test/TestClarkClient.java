package test;

import main.ClackClient;

public class TestClarkClient {
    public static final String TEST_USER_NAME_1 = "user";
    public static final String TEST_HOST_NAME_1 = "host";
    public static final int TEST_PORT_1 = 3000;

    public static void main(String[] args) {
        ClackClient client1 = new ClackClient(TEST_USER_NAME_1, TEST_HOST_NAME_1, TEST_PORT_1);
        System.out.println(client1);
        System.out.println("userName " + client1.getUserName());
        System.out.println("hostName " + client1.getHostName());
        System.out.println("port " + client1.getPort());
        System.out.println("hash " + client1.hashCode());
        System.out.println();

        ClackClient client2 = new ClackClient(TEST_USER_NAME_1, TEST_HOST_NAME_1);
        System.out.println("userName " + client2.getUserName());
        System.out.println("hostName " + client2.getHostName());
        System.out.println("port " + client2.getPort());
        System.out.println("hash " + client2.hashCode());
        System.out.println();

        ClackClient client3 = new ClackClient(TEST_USER_NAME_1);
        System.out.println("userName " + client3.getUserName());
        System.out.println("hostName " + client3.getHostName());
        System.out.println("port " + client3.getPort());
        System.out.println("hash " + client3.hashCode());
        System.out.println();

        ClackClient client4 = new ClackClient();
        System.out.println("userName " + client4.getUserName());
        System.out.println("hostName " + client4.getHostName());
        System.out.println("port " + client4.getPort());
        System.out.println("hash " + client4.hashCode());
        System.out.println();

        client1.start();
    }
}
