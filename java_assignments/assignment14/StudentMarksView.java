package java_assignments.assignment14;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class StudentMarksView extends JFrame {

    JTextField rollField;
    JTextField nameField;
    JTextField marksField;

    JButton addButton;
    JButton updateButton;
    JButton deleteButton;
    JButton averageButton;

    JTable table;
    DefaultTableModel tableModel;

    JLabel averageLabel;

    public StudentMarksView() {

        setTitle("Student Marks Viewer");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Form
        JPanel formPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        formPanel.setBorder(
                BorderFactory.createTitledBorder("Student Details")
        );

        formPanel.add(new JLabel("Roll No:"));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(new JLabel("Marks:"));

        rollField = new JTextField();
        nameField = new JTextField();
        marksField = new JTextField();

        formPanel.add(rollField);
        formPanel.add(nameField);
        formPanel.add(marksField);

        add(formPanel, BorderLayout.NORTH);

        // JTable with DefaultTableModel
        String[] columns = {"Roll No", "Name", "Marks"};

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        averageButton = new JButton("Show Average");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(averageButton);

        // Average label
        averageLabel = new JLabel("Class Average: --");
        averageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(averageLabel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void clearFields() {
        rollField.setText("");
        nameField.setText("");
        marksField.setText("");
        rollField.requestFocus();
    }
}

