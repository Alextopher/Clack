package test;

import data.ClackData;
import data.FileClackData;

import java.io.IOException;

public class TestClackData {
    public static final String TEST_USER_NAME = "test";
    public static final int TEST_TYPE = ClackData.CONSTANT_LISTUSERS;
    public static final String TEST_FILE_NAME_BAD = "bad_file.txt";
    public static final String TEST_FILE_NAME_GOOD = "/Users/mahonec/IdeaProjects/Clack/src/test/Part2_document.txt";
    public static final String TEST_FILE_OUT_1 = "/tmp/clack_test1.txt";
    public static final String TEST_FILE_OUT_2 = "/tmp/clack_test2.txt";
    public static final String TEST_FILE_OUT_3 = "/tmp/clack_test3.txt";
    public static final String TEST_FILE_OUT_4 = "/tmp/clack_test4.txt";

    public static void main(String[] args) throws IOException {
        // File Clack Data (no encryption)
        System.out.println("FileClackData - no key - constructor with 3 args");
        FileClackData fileData1 = new FileClackData(TEST_USER_NAME, TEST_TYPE, TEST_FILE_NAME_BAD);
        printFileClackData(fileData1);
        testReadFiles(fileData1);
        fileData1.setFileName(TEST_FILE_OUT_1);
        fileData1.writeFileContents();

        // Default
        System.out.println("FileClackData - no key - default");
        FileClackData fileData2 = new FileClackData();
        printFileClackData(fileData2);
        fileData2.setFileName(TEST_FILE_NAME_BAD);
        testReadFiles(fileData2);
        fileData2.setFileName(TEST_FILE_OUT_2);
        fileData2.writeFileContents();

        // File Clack Data (encryption)
        String key = "TEST";
        System.out.println("FileClackData - yes key - constructor with 3 args");
        FileClackData fileData3 = new FileClackData(TEST_USER_NAME, TEST_TYPE, TEST_FILE_NAME_BAD);
        printFileClackData(fileData3);
        testReadFilesWithKey(key, fileData3);
        fileData3.setFileName(TEST_FILE_OUT_3);
        fileData3.writeFileContents(key);

        // Default
        System.out.println("FileClackData - yes key - default");
        FileClackData fileData4 = new FileClackData();
        printFileClackData(fileData4);
        fileData4.setFileName(TEST_FILE_NAME_BAD);
        testReadFilesWithKey(key, fileData4);
        fileData4.setFileName(TEST_FILE_OUT_4);
        fileData4.writeFileContents(key);

        // The result of all the test writes should be the same file
        System.out.println("Checking all outputs are correct");
        FileClackData fileData5 = new FileClackData(TEST_USER_NAME, TEST_TYPE, TEST_FILE_OUT_1);
        fileData5.readFileContents();
        String s1 = fileData5.getData();
        FileClackData fileData6 = new FileClackData(TEST_USER_NAME, TEST_TYPE, TEST_FILE_OUT_2);
        fileData6.readFileContents();
        String s2 = fileData6.getData();
        FileClackData fileData7 = new FileClackData(TEST_USER_NAME, TEST_TYPE, TEST_FILE_OUT_3);
        fileData7.readFileContents();
        String s3 = fileData7.getData();
        FileClackData fileData8 = new FileClackData(TEST_USER_NAME, TEST_TYPE, TEST_FILE_OUT_4);
        fileData8.readFileContents();
        String s4 = fileData8.getData();

        if (s1.equals(s2) && s2.equals(s3) && s3.equals(s4)) {
            System.out.println("Result of file writes is correct");
        } else {
            System.out.println("ERROR: Something wrong with encryption or writing");
            System.exit(1);
        }
    }

    private static void testReadFilesWithKey(String key, FileClackData fileData) throws IOException {
        try {
            fileData.readFileContents(key);
            System.out.println("ERROR: this file should not exist!");
            System.exit(1);
        } catch (IOException ignored) {}
        fileData.setFileName(TEST_FILE_NAME_GOOD);
        fileData.readFileContents(key);
        System.out.println("READ FILE");
        printFileClackData(fileData);
    }

    private static void testReadFiles(FileClackData fileData) throws IOException {
        try {
            fileData.readFileContents();
            System.out.println("ERROR: this file should not exist!");
            System.exit(1);
        } catch (IOException ignored) {}
        fileData.setFileName(TEST_FILE_NAME_GOOD);
        fileData.readFileContents();
        System.out.println("READ FILE");
        printFileClackData(fileData);
    }

    private static void printFileClackData(FileClackData fileData) {
        System.out.println(fileData);
        System.out.println("getUserName " + fileData.getUserName());
        System.out.println("getType " + fileData.getType());
        System.out.println("getDate " + fileData.getDate());
        System.out.println("getFileName " + fileData.getFileName());
        System.out.println("getData " + fileData.getData());
        System.out.println("hash " + fileData.hashCode());
        System.out.println();
    }
}
