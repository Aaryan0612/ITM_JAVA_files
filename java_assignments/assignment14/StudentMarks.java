package java_assignments.assignment14;

import java.util.ArrayList;
import java.util.List;

public class StudentMarks {

    private int rollNo;
    private String name;
    private double marks;

    // Model data is kept here and is shared by the table and average calculation.
    private static final List<StudentMarks> students = new ArrayList<>();

    public StudentMarks(int rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public static void addStudent(StudentMarks student) {
        students.add(student);
    }

    public static StudentMarks getStudent(int index) {
        return students.get(index);
    }

    public static void removeStudent(int index) {
        students.remove(index);
    }

    public static double calculateAverage() {
        if (students.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (StudentMarks student : students) {
            total += student.getMarks();
        }

        return total / students.size();
    }
}
