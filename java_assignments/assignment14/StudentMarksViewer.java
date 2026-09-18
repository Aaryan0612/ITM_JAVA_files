package java_assignments.assignment14;

import javax.swing.SwingUtilities;

public class StudentMarksViewer {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StudentMarksView view = new StudentMarksView();

            new StudentMarksController(view);

            view.setVisible(true);
        });
    }
}
