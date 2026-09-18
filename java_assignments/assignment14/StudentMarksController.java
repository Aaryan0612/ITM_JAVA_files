package java_assignments.assignment14;

import javax.swing.JOptionPane;

public class StudentMarksController {

    private final StudentMarksView view;

    public StudentMarksController(StudentMarksView view) {
        this.view = view;

        // ActionListener for all four buttons.
        view.addButton.addActionListener(e -> addStudent());
        view.updateButton.addActionListener(e -> updateStudent());
        view.deleteButton.addActionListener(e -> deleteStudent());
        view.averageButton.addActionListener(e -> showAverage());
    }

    private void addStudent() {

        try {
            String rollText = view.rollField.getText().trim();
            String name = view.nameField.getText().trim();
            String marksText = view.marksField.getText().trim();

            if (rollText.isEmpty() || name.isEmpty() || marksText.isEmpty()) {
                showError("Please fill all fields.");
                return;
            }

            int rollNo = Integer.parseInt(rollText);
            double marks = Double.parseDouble(marksText);

            if (marks < 0 || marks > 100) {
                showError("Marks must be between 0 and 100.");
                return;
            }

            StudentMarks.addStudent(
                    new StudentMarks(rollNo, name, marks)
            );

            view.tableModel.addRow(
                    new Object[]{rollNo, name, marks}
            );

            updateAverage();

            JOptionPane.showMessageDialog(
                    view,
                    "Student added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            view.clearFields();

        } catch (NumberFormatException e) {
            showError("Roll No and Marks must be numeric.");
        }
    }

    private void updateStudent() {

        try {
            int row = view.table.getSelectedRow();

            if (row < 0) {
                throw new ArrayIndexOutOfBoundsException(
                        "No student row selected."
                );
            }

            String rollText = view.rollField.getText().trim();
            String name = view.nameField.getText().trim();
            String marksText = view.marksField.getText().trim();

            if (rollText.isEmpty() || name.isEmpty() || marksText.isEmpty()) {
                showError("Please enter all details before updating.");
                return;
            }

            int rollNo = Integer.parseInt(rollText);
            double marks = Double.parseDouble(marksText);

            if (marks < 0 || marks > 100) {
                showError("Marks must be between 0 and 100.");
                return;
            }

            // Update Model.
            StudentMarks student = StudentMarks.getStudent(row);
            student.setRollNo(rollNo);
            student.setName(name);
            student.setMarks(marks);

            // Update View using setValueAt().
            view.tableModel.setValueAt(rollNo, row, 0);
            view.tableModel.setValueAt(name, row, 1);
            view.tableModel.setValueAt(marks, row, 2);

            updateAverage();

            JOptionPane.showMessageDialog(
                    view,
                    "Student record updated successfully.",
                    "Update Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            view.clearFields();

        } catch (ArrayIndexOutOfBoundsException e) {
            showError("Please select a student row first.");
        } catch (NumberFormatException e) {
            showError("Roll No and Marks must be numeric.");
        }
    }

    private void deleteStudent() {

        try {
            int row = view.table.getSelectedRow();

            if (row < 0) {
                throw new ArrayIndexOutOfBoundsException(
                        "No student row selected."
                );
            }

            StudentMarks.removeStudent(row);
            view.tableModel.removeRow(row);

            updateAverage();

            JOptionPane.showMessageDialog(
                    view,
                    "Student deleted successfully.",
                    "Delete Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            view.clearFields();

        } catch (ArrayIndexOutOfBoundsException e) {
            showError("Please select a student row first.");
        }
    }

    private void showAverage() {

        double average = StudentMarks.calculateAverage();

        updateAverage();

        // Show the same average in the JavaFX preview.
        JavaFXAveragePreview.showAverage(average);
    }

    private void updateAverage() {

        double average = StudentMarks.calculateAverage();

        view.averageLabel.setText(
                String.format("Class Average: %.2f", average)
        );
    }

    private void showError(String message) {

        JOptionPane.showMessageDialog(
                view,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
