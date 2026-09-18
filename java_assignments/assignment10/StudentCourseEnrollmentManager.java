package java_assignments.assignment10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentCourseEnrollmentManager {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> enrolledStudents = new ArrayList<>();
        LinkedList<String> waitingQueue = new LinkedList<>();

        int choice;

        System.out.println("===== Student Course Enrollment Manager =====");

        do {
            System.out.println();
            System.out.println("1. Add Student to Enrollment List");
            System.out.println("2. Display Enrolled Students");
            System.out.println("3. Add Student to Waiting Queue");
            System.out.println("4. Promote Student from Queue");
            System.out.println("5. Search Student in Enrollment List");
            System.out.println("6. Remove Student from Enrollment List");
            System.out.println("7. Display Waiting Queue");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 8.");
                scanner.nextLine();
                choice = 0;
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("Enter student name to enroll: ");
                    String studentName = scanner.nextLine().trim();

                    if (studentName.isEmpty()) {
                        System.out.println("Student name cannot be empty.");
                    } else {
                        enrolledStudents.add(studentName);
                        System.out.println("Student enrolled successfully.");
                    }
                    break;

                case 2:
                    System.out.println();
                    System.out.println("===== Enrolled Students =====");

                    if (enrolledStudents.isEmpty()) {
                        System.out.println("No students are currently enrolled.");
                    } else {
                        int index = 0;

                        for (String student : enrolledStudents) {
                            System.out.println((index + 1) + ". " + student);
                            index++;
                        }

                        System.out.println(
                                "Total Enrolled Students: " + enrolledStudents.size()
                        );
                    }
                    break;

                case 3:
                    System.out.print("Enter student name for waiting queue: ");
                    String waitingStudent = scanner.nextLine().trim();

                    if (waitingStudent.isEmpty()) {
                        System.out.println("Student name cannot be empty.");
                    } else {
                        waitingQueue.addLast(waitingStudent);
                        System.out.println("Student added to waiting queue.");
                    }
                    break;

                case 4:
                    System.out.println();
                    System.out.println("Promoting first student from waiting queue...");

                    try {
                        String promotedStudent = waitingQueue.removeFirst();

                        enrolledStudents.add(promotedStudent);

                        System.out.println(
                                promotedStudent + " moved to Enrollment List."
                        );

                    } catch (NoSuchElementException e) {
                        System.out.println(
                                "Error: Waiting queue is empty. No student can be promoted."
                        );
                    }
                    break;

                case 5:
                    System.out.print("Enter student name to search: ");
                    String searchName = scanner.nextLine().trim();

                    if (searchName.isEmpty()) {
                        System.out.println("Student name cannot be empty.");
                        break;
                    }

                    boolean found = false;

                    // for-each loop traverses the ArrayList
                    for (String student : enrolledStudents) {
                        if (student.equalsIgnoreCase(searchName)) {
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        // indexOf() is required to report the student's index.
                        int studentIndex = enrolledStudents.indexOf(searchName);

                        // Support case-insensitive search when exact case differs.
                        if (studentIndex == -1) {
                            for (int i = 0; i < enrolledStudents.size(); i++) {
                                if (enrolledStudents.get(i).equalsIgnoreCase(searchName)) {
                                    studentIndex = i;
                                    break;
                                }
                            }
                        }

                        // contains() is also demonstrated as required.
                        boolean exactMatch = enrolledStudents.contains(searchName);

                        if (exactMatch || studentIndex >= 0) {
                            System.out.println(
                                    "Student found at index: " + studentIndex
                            );
                        }
                    } else {
                        System.out.println("Student not found in enrollment list.");
                    }
                    break;

                case 6:
                    System.out.print("Enter student name to remove: ");
                    String removeName = scanner.nextLine().trim();

                    if (removeName.isEmpty()) {
                        System.out.println("Student name cannot be empty.");
                        break;
                    }

                    try {
                        boolean removed = enrolledStudents.remove(removeName);

                        if (removed) {
                            System.out.println("Student removed successfully.");
                        } else {
                            System.out.println(
                                    "Student not found in enrollment list."
                            );
                        }

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(
                                "Error: Invalid enrollment list index."
                        );
                    }
                    break;

                case 7:
                    System.out.println();
                    System.out.println("===== Waiting Queue =====");

                    if (waitingQueue.isEmpty()) {
                        System.out.println("Waiting queue is empty.");
                    } else {
                        int queuePosition = 1;

                        // for-each loop traverses the LinkedList
                        for (String student : waitingQueue) {
                            System.out.println(queuePosition + ". " + student);
                            queuePosition++;
                        }

                        System.out.println(
                                "Total Waiting Students: " + waitingQueue.size()
                        );
                    }
                    break;

                case 8:
                    System.out.println(
                            "Exiting Student Course Enrollment Manager."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please enter a number from 1 to 8."
                    );
            }

        } while (choice != 8);

        scanner.close();
    }
}

