package test;

import data.ClackData;
import data.FileClackData;

public class TestClackData {
    public static final String TEST_USER_NAME = "test";
    public static final int TEST_TYPE = ClackData.CONSTANT_LISTUSERS;

    public static final String TEST_FILE_NAME_1 = "test1";
    public static final String TEST_FILE_NAME_2 = "test2";

    public static void main(String[] args) {
        // Clack Data
        ClackData data1 = new ClackData(TEST_USER_NAME, TEST_TYPE);
        System.out.println(data1);
        System.out.println("getUserName " + data1.getUserName());
        System.out.println("getType " + data1.getType());
        System.out.println("getDate " + data1.getDate());
        System.out.println("hash " + data1.hashCode());
        System.out.println();

        ClackData data2 = new ClackData(TEST_TYPE);
        System.out.println(data2);
        System.out.println("getUserName " + data2.getUserName());
        System.out.println("getType " + data2.getType());
        System.out.println("getDate " + data2.getDate());
        System.out.println("hash " + data2.hashCode());
        System.out.println();

        ClackData data3 = new ClackData();
        System.out.println(data3);
        System.out.println("getUserName " + data3.getUserName());
        System.out.println("getType " + data3.getType());
        System.out.println("getDate " + data3.getDate());
        System.out.println("hash " + data3.hashCode());
        System.out.println();

        // File Data
        FileClackData fileData1 = new FileClackData(TEST_USER_NAME, TEST_TYPE, TEST_FILE_NAME_1);
        System.out.println(fileData1);
        System.out.println("getUserName " + fileData1.getUserName());
        System.out.println("getType " + fileData1.getType());
        System.out.println("getDate " + fileData1.getDate());
        System.out.println("getFileName " + fileData1.getFileName());
        System.out.println("hash " + fileData1.hashCode());
        System.out.println();

        FileClackData fileData2 = new FileClackData(TEST_USER_NAME, TEST_TYPE, TEST_FILE_NAME_1);
        fileData2.setFileName(TEST_FILE_NAME_2);
        System.out.println(fileData2);
        System.out.println("getUserName " + fileData2.getUserName());
        System.out.println("getType " + fileData2.getType());
        System.out.println("getDate " + fileData2.getDate());
        System.out.println("getFileName " + fileData2.getFileName());
        System.out.println("hash " + fileData2.hashCode());
        System.out.println();

        FileClackData fileData3 = new FileClackData();
        System.out.println(fileData3);
        System.out.println("getUserName " + fileData3.getUserName());
        System.out.println("getType " + fileData3.getType());
        System.out.println("getDate " + fileData3.getDate());
        System.out.println("getFileName " + fileData3.getFileName());
        System.out.println("hash " + fileData3.hashCode());
        System.out.println();
    }
}
