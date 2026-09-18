package java_assignments.assignment13;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class StudentFeedbackForm extends JFrame implements ActionListener {

    private static final String STUDENT_NAME = "Aaryan";

    private JTextField nameField;
    private JTextField courseField;
    private JTextField ratingField;
    private JTextField commentsField;

    private JTextArea feedbackArea;

    private JButton submitButton;

    private JMenuItem saveFeedbackItem;
    private JMenuItem clearFormItem;
    private JMenuItem exitItem;
    private JMenuItem aboutItem;

    public StudentFeedbackForm() {

        setTitle("Student Feedback Form");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main window uses BorderLayout.
        setLayout(new BorderLayout(10, 10));

        createMenuBar();
        createForm();
        createButtons();
        createFeedbackArea();

        setVisible(true);
    }

    private void createMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        saveFeedbackItem = new JMenuItem("Save Feedback");
        clearFormItem = new JMenuItem("Clear Form");
        exitItem = new JMenuItem("Exit");
        aboutItem = new JMenuItem("About");

        fileMenu.add(saveFeedbackItem);
        fileMenu.add(clearFormItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        // ActionListener for every menu item.
        saveFeedbackItem.addActionListener(this);
        clearFormItem.addActionListener(this);
        exitItem.addActionListener(this);
        aboutItem.addActionListener(this);
    }

    private void createForm() {

        // GridLayout arranges labels and fields in rows.
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(
                BorderFactory.createTitledBorder("Student Feedback Details")
        );

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        formPanel.add(courseField);

        formPanel.add(new JLabel("Rating (1-5):"));
        ratingField = new JTextField();
        formPanel.add(ratingField);

        formPanel.add(new JLabel("Comments:"));
        commentsField = new JTextField();
        formPanel.add(commentsField);

        add(formPanel, BorderLayout.NORTH);
    }

    private void createButtons() {

        JPanel buttonPanel = new JPanel(new FlowLayout());

        submitButton = new JButton("Submit Feedback");
        buttonPanel.add(submitButton);

        submitButton.addActionListener(this);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createFeedbackArea() {

        feedbackArea = new JTextArea();
        feedbackArea.setEditable(false);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Submitted Feedback")
        );
        scrollPane.setPreferredSize(new Dimension(650, 250));

        add(scrollPane, BorderLayout.CENTER);
    }

    private void submitFeedback() {

        String name = nameField.getText().trim();
        String course = courseField.getText().trim();
        String ratingText = ratingField.getText().trim();
        String comments = commentsField.getText().trim();

        if (name.isEmpty()) {
            showError("Name cannot be empty.");
            nameField.requestFocus();
            return;
        }

        if (course.isEmpty()) {
            showError("Course cannot be empty.");
            courseField.requestFocus();
            return;
        }

        if (comments.isEmpty()) {
            showError("Comments cannot be empty.");
            commentsField.requestFocus();
            return;
        }

        try {
            int rating = Integer.parseInt(ratingText);

            if (rating < 1 || rating > 5) {
                showError("Rating must be between 1 and 5.");
                ratingField.requestFocus();
                return;
            }

            String feedback =
                    "Name: " + name
                    + " | Course: " + course
                    + " | Rating: " + rating + "/5"
                    + " | Comments: " + comments;

            feedbackArea.append(feedback + "\n");

            JOptionPane.showMessageDialog(
                    this,
                    "Feedback added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } catch (NumberFormatException e) {
            showError("Rating must be a valid number.");
            ratingField.requestFocus();
        }
    }

    private void clearFields() {

        nameField.setText("");
        courseField.setText("");
        ratingField.setText("");
        commentsField.setText("");

        nameField.requestFocus();
    }

    private void clearForm() {

        clearFields();

        JOptionPane.showMessageDialog(
                this,
                "Form fields cleared.",
                "Clear Form",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void exitApplication() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    private void showAbout() {

        JOptionPane.showMessageDialog(
                this,
                "Student Feedback Form v1.0\n"
                        + "Student Name: " + STUDENT_NAME + "\n"
                        + "Purpose: Collect and display student feedback.",
                "About",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == submitButton || source == saveFeedbackItem) {
            submitFeedback();
        } else if (source == clearFormItem) {
            clearForm();
        } else if (source == exitItem) {
            exitApplication();
        } else if (source == aboutItem) {
            showAbout();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new StudentFeedbackForm();
        });
    }
}

