package java_assignments.assignment9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class StudentRecordFileManager {

    static final String DIRECTORY_NAME = "StudentRecords";
    static final String FILE_NAME = "student.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        File directory = new File(DIRECTORY_NAME);
        File recordFile = new File(directory, FILE_NAME);

        int choice = 0;

        System.out.println("===== Student Record File Manager =====");

        do {
            System.out.println();
            System.out.println("1. Create Records Directory");
            System.out.println("2. Create Record File");
            System.out.println("3. Write Student Record");
            System.out.println("4. Display File Information");
            System.out.println("5. Read File Content");
            System.out.println("6. Append New Record");
            System.out.println("7. Delete Record File");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number from 1 to 8.");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    createRecordsDirectory(directory);
                    break;

                case 2:
                    createRecordFile(directory, recordFile);
                    break;

                case 3:
                    writeStudentRecord(recordFile, scanner);
                    break;

                case 4:
                    displayFileInformation(recordFile);
                    break;

                case 5:
                    readFileContent(recordFile);
                    break;

                case 6:
                    appendNewRecord(recordFile, scanner);
                    break;

                case 7:
                    deleteRecordFile(recordFile);
                    break;

                case 8:
                    System.out.println("Exiting Student Record File Manager.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1 to 8.");
            }

        } while (choice != 8);

        scanner.close();
    }

    // 1. Create the StudentRecords directory
    public static void createRecordsDirectory(File directory) {
        try {
            if (!directory.exists()) {
                if (directory.mkdir()) {
                    System.out.println("Directory created: " + directory.getPath());
                } else {
                    System.out.println("Failed to create directory.");
                }
            } else {
                System.out.println("Directory already exists: " + directory.getPath());
            }
        } catch (SecurityException e) {
            System.out.println("Error creating directory: " + e.getMessage());
        }
    }

    // 2. Create the student.txt record file
    public static void createRecordFile(File directory, File recordFile) {
        try {
            if (!directory.exists()) {
                System.out.println("Records directory does not exist. Please choose option 1 first.");
                return;
            }

            if (!recordFile.exists()) {
                if (recordFile.createNewFile()) {
                    System.out.println("File created: " + recordFile.getPath());
                } else {
                    System.out.println("Failed to create record file.");
                }
            } else {
                System.out.println("Record file already exists: " + recordFile.getPath());
            }
        } catch (IOException | SecurityException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    // 3. Write a new student record and overwrite existing file content
    public static void writeStudentRecord(File recordFile, Scanner scanner) {
        if (!recordFile.exists()) {
            System.out.println("Record file does not exist. Please choose option 2 first.");
            return;
        }

        System.out.print("Enter student record: ");
        String record = scanner.nextLine();

        if (record.trim().isEmpty()) {
            System.out.println("Record cannot be empty.");
            return;
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(recordFile);

            // Convert the String into bytes and write it to the file
            outputStream.write((record + System.lineSeparator()).getBytes());
            outputStream.close();

            System.out.println("Record written successfully.");
        } catch (IOException | SecurityException e) {
            System.out.println("Error writing record: " + e.getMessage());
        }
    }

    // 4. Display file information using the required File methods
    public static void displayFileInformation(File recordFile) {
        try {
            if (!recordFile.exists()) {
                System.out.println("Record file does not exist.");
                return;
            }

            System.out.println();
            System.out.println("===== File Information =====");
            System.out.println("Name            : " + recordFile.getName());
            System.out.println("Path            : " + recordFile.getPath());
            System.out.println("Absolute Path   : " + recordFile.getAbsolutePath());
            System.out.println("Size            : " + recordFile.length() + " bytes");
            System.out.println("Is File         : " + recordFile.isFile());
            System.out.println("Is Directory    : " + recordFile.isDirectory());
        } catch (SecurityException e) {
            System.out.println("Error displaying file information: " + e.getMessage());
        }
    }

    // 5. Read and display the file byte by byte until read() returns -1
    public static void readFileContent(File recordFile) {
        if (!recordFile.exists()) {
            System.out.println("Record file does not exist.");
            return;
        }

        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(recordFile);

            System.out.println();
            System.out.println("===== File Content =====");

            int data;

            while ((data = inputStream.read()) != -1) {
                System.out.print((char) data);
            }

            System.out.println();
            System.out.println("File read successfully.");
        } catch (IOException | SecurityException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing input stream: " + e.getMessage());
            }
        }
    }

    // 6. Append a new student record without overwriting existing data
    public static void appendNewRecord(File recordFile, Scanner scanner) {
        if (!recordFile.exists()) {
            System.out.println("Record file does not exist. Please choose option 2 first.");
            return;
        }

        System.out.print("Enter new student record: ");
        String record = scanner.nextLine();

        if (record.trim().isEmpty()) {
            System.out.println("Record cannot be empty.");
            return;
        }

        try {
            // true enables append mode
            FileOutputStream outputStream = new FileOutputStream(recordFile, true);

            outputStream.write((record + System.lineSeparator()).getBytes());
            outputStream.close();

            System.out.println("Record appended successfully.");
        } catch (IOException | SecurityException e) {
            System.out.println("Error appending record: " + e.getMessage());
        }
    }

    // 7. Delete the student record file
    public static void deleteRecordFile(File recordFile) {
        try {
            if (!recordFile.exists()) {
                System.out.println("Record file does not exist.");
                return;
            }

            if (recordFile.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("File deletion failed.");
            }
        } catch (SecurityException e) {
            System.out.println("Error deleting file: " + e.getMessage());
        }
    }
}

